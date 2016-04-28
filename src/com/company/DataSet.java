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
    private String IMAGE_PATH_NAME = "C:/Users/Manuel.Rixen/IdeaProjects/TelegramBot/";
    private String[] IMAGE_NAMES = {"top", "left", "right", "front"};
    private final String IP_ADDRESS = "192.168.1.2";
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

    public String getHtmlCode(){
        String htmlCode = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td style=\"border-width:0px; border-color:Black ; border-style :groove ;\" width=\"650\">\n" +
                "\t\t\t<h1><strong>Robot Information System</strong></h1>\n" +
                "\t\t\t</td>\n" +
                "\t\t\t<td style=\"border: 0px groove black; text-align: center;\" width=\"200\"><input name=\"Update\" type=\"button\" value=\"Update\" /></td>\n" +
                "\t\t</tr>\n" +
                "\t</tbody>\n" +
                "</table>\n" +
                "\n" +
                "<div style=\"background:#eee;border:1px solid #ccc;padding:5px 10px;\">Production</div>\n" +
                "\n" +
                "<table>\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td style=\"border-width:0px; border-color:Black ; border-style :groove ;\" width=\"200\">\n" +
                "\t\t\t<p>&nbsp;</p>\n" +
                "\n" +
                "\t\t\t<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:100px;\">\n" +
                "\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>Soll</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>Ist</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>Trend</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t</table>\n" +
                "\n" +
                "\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t\t<td style=\"border-width:0px; border-color:Black ; border-style :groove ;\" width=\"600\">\n" +
                "\t\t\t<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px;\">\n" +
                "\t\t\t\t<thead>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<th scope=\"col\">Last produced</th>\n" +
                "\t\t\t\t\t\t<th scope=\"col\">Article</th>\n" +
                "\t\t\t\t\t\t<th scope=\"col\">Counter</th>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</thead>\n" +
                "\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t\t<td>x</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t</tbody>\n" +
                "</table>\n" +
                "\n" +
                "<div style=\"background:#eee;border:1px solid #ccc;padding:5px 10px;\">Environment</div>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<table align=\"left\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:250px;\">\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Machine type</th>\n" +
                "\t\t\t<td>xxxxxxxx</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Project No.</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Build year</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Pre. Accept.</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Fin. Accept.</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Serial No.</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Software serial</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Robot type</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Controller id</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">IP address</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">HMI language</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Rob speed [mm/s]</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Act. Override [%]</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Duty time [h]</th>\n" +
                "\t\t\t<td>&nbsp;</td>\n" +
                "\t\t</tr>\n" +
                "\t</tbody>\n" +
                "</table>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<div style=\"background:#eee;border:1px solid #ccc;padding:5px 10px;\">Settings</div>\n" +
                "\n" +
                "<p>Mobile number: <input name=\"mobileNumber\" type=\"tel\" /><input name=\"Add\" type=\"submit\" value=\"Add\" /></p>\n" +
                "</body>\n" +
                "</html>\n";

                return htmlCode;
    }
}
