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
    private final String SERVER_IP_ADDRESS = "127.0.0.1";
    private final int PORT = 4447;
    public TeleBot teleBot;
    private final int CHAT_ID = 45587174;
    private final String[] DIAGNOSE_CMD = {"cd", "l", "c", "i", "a"};
    private int MAX_MACHINE_DATA_CONTENT = 14;
    private String[] machineData;
    private String[][] articleData;
    private int MAX_ARTICLE_COLUMN = 3;
    private int MAX_ARTICLE_COUNTER = 5;

    // TODO Configure System-Update button

    public int get_MAX_ARTICLE_COUNTER() {
        return MAX_ARTICLE_COUNTER;
    }

    public int get_MAX_ARTICLE_COLUMN() {
        return MAX_ARTICLE_COLUMN;
    }

    public DataSet(){
        machineData = new String[MAX_MACHINE_DATA_CONTENT];
        articleData = new String[MAX_ARTICLE_COUNTER][MAX_ARTICLE_COLUMN];

        // TODO Read last saved article data from file and put it into the array after the first one
        for (int i=0;i<=MAX_MACHINE_DATA_CONTENT-1;i++) machineData[i] = null;
        for (int i=0;i<=MAX_ARTICLE_COUNTER-1;i++){
            for (int j=0;j<=MAX_ARTICLE_COLUMN-1;j++){
                articleData[i][j] = null;
            }
        }
    }

    public void setMachineData(String[] machineData) {
        this.machineData = machineData;
    }

    public String[] getMachineData() {
        return machineData;
    }

    // DATA FOR WEB SERVER
    private final int REFRESH_TIME = 2;

    public int get_MAX_MACHINE_DATA_CONTENT() {
        return MAX_MACHINE_DATA_CONTENT;
    }

    private String production_soll, production_ist, production_trend, cycleTime, cycleTimeMean;

    public void setCycleTime(String cycleTime) {
        this.cycleTime = cycleTime;
    }

    public void setCycleTimeMean(String cycleTimeMean) {
        this.cycleTimeMean = cycleTimeMean;
    }

    public String getCycleTime() {
        return cycleTime;
    }

    public String getCycleTimeMean() {
        return cycleTimeMean;
    }

    public void setProduction_soll(String production_soll) {
        this.production_soll = production_soll;
    }

    public void setProduction_ist(String production_ist) {
        this.production_ist = production_ist;
    }

    public void setProduction_trend(String cycleTime) {
        this.production_trend = String.valueOf((int)((60/Float.parseFloat(cycleTime))*60));
    }

    public String getProduction_soll() {
        return production_soll;
    }

    public String getProduction_ist() {
        return production_ist;
    }

    public String getProduction_trend() {
        return production_trend;
    }

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
        return SERVER_IP_ADDRESS;
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

    public String getHtmlCodeConfig(){
        String htmlCode = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"background:#eee;border:0px solid #ccc;padding:5px 10px;\">Settings</div>\n" +
                "\n" +
                "<table>\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +

                "\t\t\t<td style=\"border-width:0px; border-color:Black ; border-style :groove ;\" width=\"200\">\n" +
                "<table>\n" +
                "\t<tbody>\n" +

                "<td>" +
                "Add mobile number <form action='?' method='get'>\n <input name='username' size='20' type='text' /></form>\n" +
                "</td>" +

                "<tr>" +
                "\t\t\t<td>Set production soll value <form action='?' method='get'>\n <input name='sollvalue' size='20' type='text' /></form>\n" +
                "</tr>" +

                "\t</tbody>\n" +
                "</table>\n" +
                "\t\t\t</td>\n" +

                "\t\t\t<td style=\"border-width:0px; border-color:Black ; border-style :groove ;\" width=\"200\">\n" +
                "<table>\n" +
                "\t<tbody>\n" +
                "<td>" +
                "<form action='?' method='post'>\n  <input name='systemupdate' type='button' value='System Update' /></form>\n" +
                "</td>" +
                "\t</tbody>\n" +
                "</table>\n" +

                "\t\t\t</td>\n" +
                "\t</tbody>\n" +
                "</table>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "</body>\n" +
                "</html>\n";

        return htmlCode;
    }

    public String[][] getArticleData() {
        return articleData;
    }

    public void setArticleData(String[][] articleData) {
        this.articleData = articleData;
    }

    public String getHtmlCodeData(){
        String htmlCode = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "<META HTTP-EQUIV=\"refresh\" CONTENT=\"" + REFRESH_TIME + "\">" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td style=\"border-width:0px; border-color:Black ; border-style :groove ;\" width=\"650\">\n" +
                "\t\t\t<h4 style=\"background: silver\">Robot Information System</strong></h4>\n" +
                "\t\t\t</td>\n" +
                "\t</tbody>\n" +
                "</table>\n" +
                "\n" +
                "<div style=\"background:#eee;border:0px solid #ccc;padding:5px 10px;\">Production</div>\n" +
                "\n" +
                "<table>\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td style=\"border-width:0px; border-color:Black ; border-style :groove ;\" width=\"200\">\n" +
                "\t\t\t<p>&nbsp;</p>\n" +
                "\n" +
                "\t\t\t<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:150px;\">\n" +
                "\t\t\t<caption>Actual data</caption>" +
                "\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>Soll</td>\n" +
                "\t\t\t\t\t\t<td>"+ production_soll +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>Ist</td>\n" +
                "\t\t\t\t\t\t<td>" + production_ist + "</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>Trend</td>\n" +
                "\t\t\t\t\t\t<td>" + production_trend + "</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t</table>\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "\t\t\t<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:150px;\">\n" +
                "\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>Cycle time</td>\n" +
                "\t\t\t\t\t\t<td>"+ cycleTime +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t</table>\n" +
                "\n" +
                "\t\t\t<p>&nbsp;</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t\t<td style=\"border-width:0px; border-color:Black ; border-style :groove ;\" width=\"600\">\n" +
                "\t\t\t<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px;\">\n" +
                "\t\t\t<caption>Produced article list</caption>" +
                "\t\t\t\t<thead>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<th scope=\"col\">Name</th>\n" +
                "\t\t\t\t\t\t<th scope=\"col\">Amount</th>\n" +
                "\t\t\t\t\t\t<th scope=\"col\">Cycletime average</th>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t</thead>\n" +
                "\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t<tr>\n" +

                "\t\t\t\t\t\t<td>"+ articleData[0][0] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[0][1] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[0][2] +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[1][0] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[1][1] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[1][2] +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[2][0] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[2][1] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[2][2] +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[3][0] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[3][1] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[3][2] +"</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[4][0] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[4][1] +"</td>\n" +
                "\t\t\t\t\t\t<td>"+ articleData[4][2] +"</td>\n" +
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
                "<table align=\"left\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:450px;\">\n" +
                "\t\t\t<caption>Machine data</caption>" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Machine name</th>\n" +
                "\t\t\t<td>" + machineData[0] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Project No.</th>\n" +
                "\t\t\t<td>" + machineData[1] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Build year</th>\n" +
                "\t\t\t<td>" + machineData[2] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Pre. Accept.</th>\n" +
                "\t\t\t<td>" + machineData[3] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Fin. Accept.</th>\n" +
                "\t\t\t<td>" + machineData[4] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Serial No.</th>\n" +
                "\t\t\t<td>" + machineData[5] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Software serial</th>\n" +
                "\t\t\t<td>" + machineData[6] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Robot type</th>\n" +
                "\t\t\t<td>" + machineData[7] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Controller id</th>\n" +
                "\t\t\t<td>" + machineData[8] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">IP address</th>\n" +
                "\t\t\t<td>" + machineData[9] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">HMI language</th>\n" +
                "\t\t\t<td>" + machineData[10] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Rob speed [mm/s]</th>\n" +
                "\t\t\t<td>" + machineData[11] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Act. Override [%]</th>\n" +
                "\t\t\t<td>" + machineData[12] + "</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th scope=\"row\" style=\"text-align: left;\">Duty time [h]</th>\n" +
                "\t\t\t<td>" + machineData[13] + "</td>\n" +
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
                "\n";

        return htmlCode;
    }


}
