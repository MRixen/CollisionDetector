package com.company;

import com.company.Receiver.Server;

public class Main {

    public static void main(String[] args) {

        // Start server to receive event messages from robot controller and send messages via sms and telegram
        new Server();

    }
}
