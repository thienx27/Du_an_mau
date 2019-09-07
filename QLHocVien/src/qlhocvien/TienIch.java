/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhocvien;

import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class TienIch {

    public static void setSiz(Component cpn, int x, int y) {
        Dimension di = new Dimension(x, y);
        cpn.setMaximumSize(di);
        cpn.setMinimumSize(di);
        cpn.setPreferredSize(di);
    }

    public static Connection Connections(String databasename) {
        String username = "sa";
        String password = "123";
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=quanlyhocvien";
        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }
}
