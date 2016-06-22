package com.company.Receiver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by manuel.rixen on 07.06.2016.
 */
public class DatabaseManagement {

    private Connection conn = null;
    private String dbUserName = "root"; // MySQL database username
    private String dbPassword = "rbc"; // MySQL database password
    private String dbUrl = "jdbc:mysql://localhost/bitnami_wordpress";

    public void updateProcessState(String fieldId, String fieldValue){
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String insertSql = "UPDATE production_actual SET " + fieldId + "='" + fieldValue + "' WHERE " + 1 + ";";
        try {
            int val = st.executeUpdate(insertSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("DatabaseManagement updated");
    }

    public void updateMachineData(String fieldId, String fieldValue){
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String insertSql = "UPDATE machine_data SET " + fieldId + "='" + fieldValue + "' WHERE " + 1 + ";";
        try {
            int val = st.executeUpdate(insertSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("DatabaseManagement updated");
    }

    public void connectToDb() {
        Class forNam = null;
        try {
            forNam = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            forNam.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DatabaseManagement connection established.");
    }

    public void disconnectFromDb(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println ("DatabaseManagement connection closed.");

        }
    }

}
