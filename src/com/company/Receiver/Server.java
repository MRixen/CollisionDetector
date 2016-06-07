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
    private String[] tempMachineData;
    private String[][] tempArticleData;
    private Wordpress wordpress;

    public Server(DataSet dataSet, Wordpress wordpress){
        gripperLocation = new GripperLocation();
        this.wordpress = wordpress;
        this.dataSet = dataSet;
        this.teleBot = dataSet.getTeleBot();
        tempMachineData = new String[dataSet.get_MAX_MACHINE_DATA_CONTENT()];
        tempArticleData = new String[dataSet.get_MAX_ARTICLE_COUNTER()][dataSet.get_MAX_ARTICLE_COLUMN()];

        this.wordpress.connectToDb();

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
/*         enableDataConnection();
       try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("No waiting executed in Server-Thread. Exception thrown: " + e);
        }*/

        // Start server to connect to robot client and receive data
        startServer();
    }

    private void startServer() {
            // Start collector Thread to extract and interpret data from robot controller
            collector = new Collector(dataSet.getPort(), ip);
            receiverThread = new Thread(collector.collectorTask);
            receiverThread.start();

        // Add shutdown listener when closing application
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                wordpress.disconnectFromDb();
                collector.stopRunRoutine();
                receiverThread.interrupt();
            }
        });


            // Add listener to receive data
            collector.registerListener(new Collector.EventListener() {


                @Override
                public void onEvent(String data0, String data1) {

                    // Get message for collision detect only
                    if(data1.equals(dataSet.getDiagnoseCmd()[0])) {
                        // GET COLLISION DATA

                        //Send message via sms
/*                    try {
                        // Send sms via script-execution
                        Runtime.getRuntime().exec(dataSet.getCmdSendSms());
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Sending sms failed in Server-Thread. Exception thrown: " + e);
                    }
                        // Extract x, y, z from data0
                        String[] tempMessage = data0.split(":");
                        System.out.println("x: " + tempMessage[0]);
                        System.out.println("y: " + tempMessage[1]);
                        System.out.println("z: " + tempMessage[2]);

                        // Generate images to show gripper location
                        byte[][] images = gripperLocation.generateImages(dataSet, tempMessage);

                        // Send message and images to telegram
                        teleBot.sendMessageToChat(dataSet.getChatId(), dataSet.getTelegramChatMessage());
                        for (int i=0;i<=images.length-1;i++) teleBot.sendPhotoToChat(dataSet.getChatId(), images[i], dataSet.getImageNames()[i]);
*/
                    }
                    // Get message for logging only
                    else if(data1.equals(dataSet.getDiagnoseCmd()[1])){
                        //teleBot.sendMessageToChat(dataSet.getChatId(), "Logging executed with message content: " + data0);
                    }
                    else if(data1.equals(dataSet.getDiagnoseCmd()[2])){
                        // GET CYLE TIME
                        String[] tempMessage = data0.split(":");
                        wordpress.updateProcessState("Ist", tempMessage[0]);
                        // TODO: Make it able to choose (set soll value in .MOD file or in the browser)
                        wordpress.updateProcessState("Soll", tempMessage[1]);
                        System.out.println("tempMessage[1]: " + tempMessage[1]);
                        wordpress.updateProcessState("Trend", String.valueOf((int)((60/Float.parseFloat(tempMessage[3]))*60)));
                        wordpress.updateProcessState("Cycletime", tempMessage[3]);
//                        dataSet.setCycleTimeMean(tempMessage[4]);
                    }
                    else if(data1.equals(dataSet.getDiagnoseCmd()[3])){
                        // GET MACHINE DATA
                        String[] tempMessage = data0.split(":");
                        for (int i=0;i<=dataSet.get_MAX_MACHINE_DATA_CONTENT()-1;i++) tempMachineData[Integer.parseInt(tempMessage[0])] = tempMessage[1];
//                        dataSet.setMachineData(tempMachineData);
                    }
                else if(data1.equals(dataSet.getDiagnoseCmd()[4])){
                    // GET ARTICLE DATA
                    String[] tempMessage = data0.split(":");

//                    for (int i=0;i<=dataSet.get_MAX_ARTICLE_COLUMN()-1;i++) tempArticleData[0][i] = tempMessage[i];
//                    dataSet.setArticleData(tempArticleData);
                }
                }

                @Override
                public void onError() {

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
