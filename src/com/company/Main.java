package com.company;

import com.mashape.unirest.http.exceptions.UnirestException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    public static void main(String[] args) {
        BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bi.createGraphics();
            graphics.setColor(Color.BLACK);
            graphics.drawLine(50, 50, 50, 100);

        try {
            ImageIO.write(bi, "PNG", new File("C:/Users/Manuel.Rixen/IdeaProjects/TelegramBot/Capture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //TeleBot teleBot = new TeleBot();
       // teleBot.sendPhotoToChat(45587174, "C:/Users/Manuel.Rixen/IdeaProjects/TelegramBot/Capture.jpg");

        //telegramBot = new TelegramBotOld();

        //enableDataConnection();

        //startServer(4447, "192.168.1.55");
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
