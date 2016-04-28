package com.company.Receiver;

import com.company.DataSet;
import com.company.Visualization.GripperLocation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Manuel.Rixen on 28.04.2016.
 */
public class Server implements Runnable {

    private final GripperLocation gripperLocation;
    private InetAddress ip;
    private Collector collector;
    private Thread receiverThread;
    private DataSet dataSet;
    private TeleBot teleBot;

    public Server(){
        gripperLocation = new GripperLocation();
        dataSet = new DataSet();
        this.teleBot = dataSet.getTeleBot();

        // Set ip address
        try {
            this.ip = InetAddress.getByName(dataSet.getIpAddress());
            // Start this thread to receive events from the Collector
            Thread thread = new Thread(this);
            thread.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("Server-Thread not started. Exception thrown: " + e);
        }
    }

    @Override
    public void run() {
        // Switch data stick to mobile data connection mode
        enableDataConnection();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("No waiting executed in Server-Thread. Exception thrown: " + e);
        }

        // Start server to connect to robot client and receive data
        startServer();
    }

    private void startServer() {
            // Start collector Thread to extract and interpret data from robot controller
            collector = new Collector(dataSet.getPort(), ip);
            receiverThread = new Thread(collector.collectorTask);
            receiverThread.start();

            // Add listener to receive data
            collector.registerListener(new Collector.EventListener() {


                @Override
                public void onEvent(String data0, String data1) {
                    //Send message via sms
                    try {
                        // Send sms via script-execution
                        Runtime.getRuntime().exec(dataSet.getCmdSendSms());
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Sending sms failed in Server-Thread. Exception thrown: " + e);
                    }

                    // Generate images to show gripper location
                    byte[][] images = gripperLocation.generateImages();

                    // Send message and images to telegram
                    teleBot.sendMessageToChat(dataSet.getChatId(), dataSet.getTelegramChatMessage());
                    for (int i=0;i<=images.length-1;i++) teleBot.sendPhotoToChat(dataSet.getChatId(), images[i]);
                }

                @Override
                public void onError() {

                }

            });

            // Add shutdown listener when closing application
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    collector.stopRunRoutine();
                    receiverThread.interrupt();
                }
            });
    }


    private void enableDataConnection() {
        try {
            Runtime.getRuntime().exec(dataSet.getCmdModeSwitch());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Enabling mode switch failed in Server-Thread. Exception thrown: " + e);
        }
    }
}
