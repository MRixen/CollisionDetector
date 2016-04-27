package com.company;

import com.mashape.unirest.http.exceptions.UnirestException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    // Data for telegram
    private static TelegramBotOld telegramBot;

    //Data for sms
    private static Receiver receiver;
    private static Thread receiverThread;
    private static String cmd_sendSms = "/home/pi/Desktop/cmd_sendSms.sh";
    private static String cmd_modeswitch = "/home/pi/Desktop/cmd_modeswitch.sh";
    private static TeleBot teleBot;
    private static String pathName = "C:/Users/Manuel.Rixen/IdeaProjects/TelegramBot/";
    private static String[] imageNames = {"top", "left", "right", "front"};

    public static void main(String[] args) {
        teleBot = new TeleBot();
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Main();
            }
        });





        //telegramBot = new TelegramBotOld();

        //enableDataConnection();

        //startServer(4447, "192.168.1.55");
    }

    public Main() {
        JFrame frame = new JFrame("Test Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(530, 460));
        for (int i=0;i<=1;i++) {

            frame.getContentPane().removeAll();
            frame.add(new ImagePanel(pathName+imageNames[i]+".PNG", imageNames[i]));
            frame.setVisible(true);

            // Get image from JFrame
            Container content = frame.getContentPane();
            BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            content.printAll(g2d);
            g2d.dispose();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                // Send image to telegram
                //ImageIO.write(img, "png", new File("C:/Users/Manuel.Rixen/IdeaProjects/TelegramBot/topMod.PNG"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", baos);
                byte[] bytes = baos.toByteArray();
                teleBot.sendPhotoToChat(45587174, bytes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void startServer(int port, String address) {
        try {
            InetAddress ip = InetAddress.getByName(address);

            // Start receiverThread
            receiver = new Receiver(port, ip);
            receiverThread = new Thread(receiver.receiverTask);
            receiverThread.start();

            // Add listener to receive data
            receiver.registerListener(new Receiver.EventListener() {
                @Override
                public void onEvent(String data0, String data1) {
                    //Send message via sms
                    try {
                        // Send sms via script-execution
                        Runtime.getRuntime().exec(cmd_sendSms);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Send message to telegram
                    try {
                        telegramBot.sendMessageToChat();
                    } catch (UnirestException e) {
                        System.out.println("Exception: " + e);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError() {

                }

            });

            // Add shutdown listener when closing application
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    receiver.stopRunRoutine();
                    receiverThread.interrupt();
                }
            });
        } catch (UnknownHostException e) {
            System.out.println("Exception in main(): " + e);
        }
    }

    private static void enableDataConnection() {
        try {
            Runtime.getRuntime().exec(cmd_modeswitch);
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
