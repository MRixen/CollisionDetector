package com.company;

import com.company.Receiver.TeleBot;
import com.company.Receiver.Wordpress;

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
        String htmlCode = "<!DOCTYPE html>\n" +
                "\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <title>Roboter Informations-System</title>\n" +
                "    <META HTTP-EQUIV=\"refresh\" CONTENT=\"2\"/>\n" +
                "    <style>\n" +
                "        header {\n" +
                "            background-color: #02489D;\n" +
                "            color: white;\n" +
                "            text-align: center;\n" +
                "            padding: 5px;\n" +
                "        }\n" +
                "\n" +
                "        nav {\n" +
                "            line-height: 30px;\n" +
                "            background-color: #eeeeee;\n" +
                "            height: 600px;           \n" +
                "            width: 300px;\n" +
                "            float: left;\n" +
                "            padding: 5px;\n" +
                "        }\n" +
                "\n" +
                "        section {           \n" +
                "            height: 600px;           \n" +
                "            width:600px;\n" +
                "            float: left;\n" +
                "            padding: 10px;\n" +
                "            background-color: #eeeeee;\n" +
                "        }\n" +
                "\n" +
                "        article {\n" +
                "            height: 600px;\n" +
                "            float: left;            \n" +
                "            width:600px;\n" +
                "            padding: 10px;\n" +
                "            background-color: #eeeeee;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        footer {\n" +
                "            background-color: #02489D;\n" +
                "            color: white;\n" +
                "            clear: both;\n" +
                "            text-align: center;\n" +
                "            padding: 5px;\n" +
                "        }\n" +
                "\n" +
                "        h1{\n" +
                "            font-size: xx-large;\n" +
                "            color: white;\n" +
                "        }\n" +
                "\n" +
                "        table, th, td {\n" +
                "        border: 2px solid black;\n" +
                "        border-collapse: collapse;\n" +
                "        }\n" +
                "\n" +
                "        th, td {\n" +
                "            padding: 10px;\n" +
                "        }\n" +
                "\n" +
                "        #table_nav_col_group{\n" +
                "            background-color: white;           \n" +
                "        }\n" +
                "\n" +
                "        #table_nav_col_1{\n" +
                "            width: 120px\n" +
                "        }\n" +
                "\n" +
                "        #table_nav_col_2{\n" +
                "            width: 80px\n" +
                "        }\n" +
                "\n" +
                "        \n" +
                "        #table_nav_col_articledata{\n" +
                "            width: 180px;\n" +
                "            height: 60px;            \n" +
                "        }\n" +
                "\n" +
                "        #table_nav_col_information{\n" +
                "            width: 120px;\n" +
                "            height: 60px;            \n" +
                "        }\n" +
                "        \n" +
                "        #table_nav_col_3{\n" +
                "            width: 80px\n" +
                "        }\n" +
                "\n" +
                "        #table_nav_cell_heavy{\n" +
                "            color: black;\n" +
                "            font-weight: bold;   \n" +
                "            font-size: large;   \n" +
                "            height: 40px;     \n" +
                "        }\n" +
                "\n" +
                "        #table_nav_cell_low{\n" +
                "            color: black; \n" +
                "            font-size: medium;   \n" +
                "            height: 60px;     \n" +
                "        }\n" +
                "\n" +
                "        #table_nav_cell_low_small{\n" +
                "            color: black; \n" +
                "            font-size: medium;   \n" +
                "            height: 30px;     \n" +
                "        }\n" +
                "\n" +
                "        caption{\n" +
                "            color: black;\n" +
                "            font-weight: bold;  \n" +
                "            font-size: small;  \n" +
                "            text-align: left\n" +
                "        }\n" +
                "\n" +
                "        #whole_document{\n" +
                "            margin: auto 10px;\n" +
                "            width: auto;\n" +
                "            height: 2000px;\n" +
                "            background-color: #eeeeee;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body id=\"whole_document\">\n" +
                "\n" +
                "    <header>\n" +
                "        <h1>Informations-System</h1>\n" +
                "    </header>\n" +
                "\n" +
                "    <nav>\n" +
                "        <table id=\"table_nav\">\n" +
                "            <caption>Aktuelle Daten</caption>\n" +
                "            <colgroup id=\"table_nav_col_group\">\n" +
                "                <col id=\"table_nav_col_1\">\n" +
                "                <col id=\"table_nav_col_2\">\n" +
                "            </colgroup>\n" +
                "            <tbody id=\"table_nav_row_group\">\n" +
                "                <tr id=\"table_nav_row_1\">\n" +
                "                    <td id=\"table_nav_cell_heavy\">Soll</td>\n" +
                "                    <td id=\"table_nav_cell_low_small\"> "+production_soll+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_2\">\n" +
                "                    <td id=\"table_nav_cell_heavy\">Ist</td>\n" +
                "                    <td id=\"table_nav_cell_low_small\"> "+production_ist+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_heavy\">Trend</td>\n" +
                "                    <td id=\"table_nav_cell_low_small\"> "+production_trend+" </td>\n" +
                "                </tr>\n" +
                "\n" +
                "            </tbody>\n" +
                "            </table>\n" +
                "        <p></p>\n" +
                "        <table id=\"table_nav\">\n" +
                "            <caption></caption>\n" +
                "            <colgroup id=\"table_nav_col_group\">\n" +
                "                <col id=\"table_nav_col_1\">\n" +
                "                <col id=\"table_nav_col_2\">\n" +
                "            </colgroup>\n" +
                "            <tbody id=\"table_nav_row_group\">\n" +
                "                <tr id=\"table_nav_row_4\">\n" +
                "                    <td id=\"table_nav_cell_heavy\">Zykluszeit</td>\n" +
                "                    <td id=\"table_nav_cell_low_small\"> "+cycleTime+" </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "</nav>\n" +
                "\n" +
                "    <section>\n" +
                "        <table id=\"table_nav\">\n" +
                "            <caption>Artikeldaten</caption>\n" +
                "            <colgroup id=\"table_nav_col_group\">\n" +
                "                <col id=\"table_nav_col_articledata\">\n" +
                "                <col id=\"table_nav_col_articledata\">\n" +
                "                <col id=\"table_nav_col_articledata\">\n" +
                "            </colgroup>\n" +
                "            <tbody id=\"table_nav_row_group\">\n" +
                "                <tr id=\"table_nav_row_1\">\n" +
                "                    <td id=\"table_nav_cell_heavy\">Name</td>\n" +
                "                    <td id=\"table_nav_cell_heavy\">Anzahl</td>\n" +
                "                    <td id=\"table_nav_cell_heavy\">Zykluszeit</td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_2\">\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[0][0]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[0][1]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[0][2]+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[1][0]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[1][1]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[1][2]+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[2][0]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[2][1]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[2][2]+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[3][0]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[3][1]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[3][2]+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[4][0]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[4][1]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+articleData[4][2]+" </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </section>\n" +
                "\n" +
                "    <article>\n" +
                "        <table id=\"table_nav\">\n" +
                "            <caption>Maschinendaten</caption>\n" +
                "            <colgroup id=\"table_nav_col_group\">\n" +
                "                <col id=\"table_nav_col_information\">\n" +
                "                <col id=\"table_nav_col_information\">\n" +
                "                <col id=\"table_nav_col_information\">\n" +
                "                <col id=\"table_nav_col_information\">\n" +
                "                <col id=\"table_nav_col_information\">\n" +
                "                <col id=\"table_nav_col_information\">\n" +
                "            </colgroup>\n" +
                "            <tbody id=\"table_nav_row_group\">\n" +
                "                <tr id=\"table_nav_row_1\">\n" +
                "                    <td id=\"table_nav_cell_heavy\">Name</td>\n" +
                "                    <td id=\"table_nav_cell_heavy\">Wert</td>\n" +
                "                    <td id=\"table_nav_cell_heavy\">Name</td>\n" +
                "                    <td id=\"table_nav_cell_heavy\">Wert</td>\n" +
                "                    <td id=\"table_nav_cell_heavy\">Name</td>\n" +
                "                    <td id=\"table_nav_cell_heavy\">Wert</td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_2\">\n" +
                "                    <td id=\"table_nav_cell_low\">Maschinen-Typ</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[0]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\">Projekt-Nr.</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[1]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\">Baujahr</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[2]+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_low\">Abnahme rbc</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[3]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\">Abnahme Scheideler</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[4]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\">Serien-Nr.</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[5]+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_low\">Software-Nr.</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[6]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\">Robotertyp</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[7]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\">Steuerungs-ID</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[8]+" </td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_low\">HMI Sprache</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[9]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\">Roboter Vmax [mm/s]</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[10]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"></td>\n" +
                "                    <td id=\"table_nav_cell_low\"></td>\n" +
                "                </tr>\n" +
                "                <tr id=\"table_nav_row_3\">\n" +
                "                    <td id=\"table_nav_cell_low\">Betriebsstunden</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[11]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\">Aktueller OV [%]</td>\n" +
                "                    <td id=\"table_nav_cell_low\"> "+machineData[12]+" </td>\n" +
                "                    <td id=\"table_nav_cell_low\"></td>\n" +
                "                    <td id=\"table_nav_cell_low\"></td>\n" +
                "                </tr>            \n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "     </article>\n" +
                "\n" +
                "    <footer>\n" +
                "        Copyright rbc Fördertechnik GmbH\n" +
                "    </footer>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        return htmlCode;
    }


}
