package com.company;

import com.company.Receiver.Server;
import com.company.Visualization.GripperLocation;
import com.company.Webserver.WebServer;
import com.jogamp.common.util.RunnableTask;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Start server to receive event messages from robot controller and send messages via sms and telegram
        //new Server();



        // TEST FOR WEBSERVER
        Thread webServerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new WebServer(new DataSet());
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
