package de.zonlykroks.survival.api;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.zonlykroks.survival.mysql.Connector;

public class CoinApi {

    public static int getCoins(String uuid) {
        try {
            PreparedStatement st = Connector.con.prepareStatement("SELECT coins from coinTable WHERE UUID = ?");
            st.setString(1, uuid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void setCoins(String uuid, int coins) {
        if (getCoins(uuid) == -1) {
            try {
                PreparedStatement st = Connector.con.prepareStatement("INSERT INTO coinTable (UUID, coins) VALUES (?,?)");
                st.setString(1, uuid);
                st.setInt(2, coins);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement st = Connector.con.prepareStatement("UPDATE coinTable SET coins = ? WHERE UUID = ?");
                st.setString(2, uuid);
                st.setInt(1, coins);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addCoins(String uuid, int coins) {
        setCoins(uuid, coins + getCoins(uuid));
    }

    public static void removeCoins(String uuid, int coins) {
        setCoins(uuid, getCoins(uuid) - coins);
    }

}
