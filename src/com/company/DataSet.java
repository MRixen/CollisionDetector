package com.company;

import com.company.Receiver.TeleBot;

/**
 * Created by Manuel.Rixen on 28.04.2016.
 */
public class DataSet {
    private final String TELEBOT_TOKEN = "183181959:AAH0AyU-k8AZNjXRxz-MM36rYOIdQ_iZO5E";
    public final String CMD_SEND_SMS = "/home/pi/Desktop/CMD_SEND_SMS.sh";
    public final String CMD_MODESWITCH = "/home/pi/Desktop/CMD_MODESWITCH.sh";
    public final String TELEGRAM_CHAT_MESSAGE = "Collision detected. Please look at the following pictures to decide remote restart via PickVision Stop / Start.";
    private String IMAGE_PATH_NAME = "C:/Users/Manuel.Rixen/IdeaProjects/CollisionDetector/";
    private String[] IMAGE_NAMES = {"top", "left", "right", "front"};
    private final String IP_ADDRESS = "192.168.1.55";
    private final int PORT = 4447;
    public TeleBot teleBot;
    private final int CHAT_ID = 45587174;
    private final String[] DIAGNOSE_CMD = {"cd", "l"};

    public String getCmdSendSms() {
        return CMD_SEND_SMS;
    }

    public String getCmdModeSwitch() {
        return CMD_MODESWITCH;
    }

    public String getTelegramChatMessage() {
        return TELEGRAM_CHAT_MESSAGE;
    }

    public TeleBot getTeleBot() {
        return new TeleBot(TELEBOT_TOKEN);
    }

    public String getIpAddress() {
        return IP_ADDRESS;
    }

    public int getPort() {
        return PORT;
    }

    public int getChatId() {
        return CHAT_ID;
    }

    // TODO Change this that it is easier to read (something like struct)
    public String[] getDiagnoseCmd() {
        return DIAGNOSE_CMD;
    }

    public String getImagePathName() {
        return IMAGE_PATH_NAME;
    }

    public String[] getImageNames() {
        return IMAGE_NAMES;
    }
}
