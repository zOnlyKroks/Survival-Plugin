package de.zonlykroks.survival.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    public static Connection con;

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/coins?autoReconnect=true", "root", "");
                System.out.println("MySQL verbunden");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("MySQL getrennt");
        }
    }

    public static boolean isConnected() {
        return (con != null);
    }

    public static void createTable() {
        try {
            con.prepareStatement("CREATE TABLE IF NOT EXISTS coinTable (UUID VARCHAR(100), coins INT(16))").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
