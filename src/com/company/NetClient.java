package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Manuel.Rixen on 16-Apr-16.
 */
public class NetClient {
    private final Receiver receiver;
    private final InetAddress ip;
    // TODO Close application when in read-routine

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private final int port;
    private Socket clientSocket;
    private boolean isRunning;
    private ServerSocket serverSocket;

    public NetClient(int port, InetAddress ip, Receiver receiver) {
        this.port = port;
        this.receiver = receiver;
        this.ip = ip;
    }

    public boolean connectWithClient() {
        try {
            isRunning = true;
            serverSocket = new ServerSocket(port,50,ip);

            System.out.println("Listening for client");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //clientSocket.setSoTimeout(500);
            return true;

        } catch (IOException e) {
            System.out.println("exception: " + e);
            return false;
        }
    }

    public void disConnectWithClient() {
        if (clientSocket != null) {
            if (clientSocket.isConnected()) {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                    /*clientSocket = null;
                    serverSocket = null;*/
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendDataWithString(String message) {
        if (message != null) {
            //out.println(message);
            out.write(message);
            out.flush();
        }
    }

    public String[] receiveDataFromClient() {
        String incomingMessage = "";
        String[] msgArray = new String[]{"", ""};
        boolean commandMessage = false;
        boolean normalMessage = false;
        boolean itsACommand = false;

        while (isRunning) {
            try {
                // Read and convert message
                try {
                    incomingMessage = String.valueOf(Character.toChars(in.read()));
                } catch (NumberFormatException e) {
                    incomingMessage = "";
                } catch (IllegalArgumentException e) {
                    incomingMessage = "";
                }

                if (incomingMessage.length() != 0) {
                    if (!incomingMessage.equals(":") && commandMessage) {
                        itsACommand = true;
                        msgArray[1] += incomingMessage;
                    }

                    if (!incomingMessage.equals(":") && !commandMessage) normalMessage = true;
                    // Check if msg should be read as command
                    if (incomingMessage.equals(":") && !commandMessage) {
                        commandMessage = true;
                    } else if (incomingMessage.equals(":") && commandMessage) {
                        if (itsACommand) normalMessage = false;
                        if (!itsACommand) normalMessage = true;
                        commandMessage = false;
                    }

                    // Read message as normal characters
                    if (!incomingMessage.equals(";") && !commandMessage && normalMessage) {
                        msgArray[0] += incomingMessage;
                        itsACommand = false;
                    } else if (incomingMessage.equals(";") && !commandMessage && normalMessage) {
                        normalMessage = false;
                        return msgArray;
                    }
                } else return new String[]{" ", " "};

            } catch (Exception e) {
                isRunning = false;
                receiver.stopRunRoutine();
            }
        }
        return new String[]{"", ""};
    }
}
