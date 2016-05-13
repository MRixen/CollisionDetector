package com.company;

import com.company.Receiver.Server;
import com.company.Webserver.WebServerConfig;
import com.company.Webserver.WebServerData;

import java.io.IOException;

public class Main {

    private static DataSet dataSet;

    public static void main(String[] args) {

        dataSet = new DataSet();
        // Start server to receive event messages from robot controller and send messages via sms and telegram
        new Server(dataSet);

        // TEST FOR WEBSERVER
        Thread webServerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new WebServerData(dataSet);
                    new WebServerConfig(dataSet);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Starting web server failed with exception: " + e);
                }
            }
        });
        webServerThread.start();

        // TEST FOR GRIPPER LOCATION
/*        GripperLocation gripperLocation = new GripperLocation();
        DataSet dataSet = new DataSet();
        String[] tempMessage = {"174.07", "-446.683", "442.215"};
        gripperLocation.generateImages(dataSet, tempMessage);*/

    }
}
