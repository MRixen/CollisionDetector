package com.company;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by Manuel.Rixen on 16-Apr-16.
 */
public class Receiver {

    private int port;
    private String data[] = new String[]{"", ""};
    private ArrayList<EventListener> listeners = new ArrayList<>();
    private NetClient nc;
    private boolean isRunning;

    public Receiver(int port, InetAddress ip) {
        if (nc == null) nc = new NetClient(port, ip, this);
    }

    Runnable receiverTask = () -> {
        while(true) {
            isRunning = true;
            if (nc.connectWithClient()) {
                System.out.println("Receive data from client");
                while (isRunning) {
                    data = nc.receiveDataFromClient();

                    if ((data[0] != null) && (data[1] != null)) {
                        //nc.sendDataWithString("1");
                        try {
                            if ((data[0].length() > 0) && (data[1].length() > 0)) {
                                for (EventListener eventListener : listeners)
                                    eventListener.onEvent(data[0], data[1]);
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Exception:receiverTask " + String.valueOf(e));
                        }
                    } else {
                        // Get the same message again
                        //nc.sendDataWithString("0");
                    }
                }
                nc.disConnectWithClient();
            } else {
                //System.out.println("client isn't connected");
            }
        }
    };

    public void stopRunRoutine() {
        isRunning = false;
    }

    public void registerListener(EventListener listener) {
        this.listeners.add(listener);
    }

    public interface EventListener {
        void onEvent(String data1, String data2);

        void onError();
    }
}
