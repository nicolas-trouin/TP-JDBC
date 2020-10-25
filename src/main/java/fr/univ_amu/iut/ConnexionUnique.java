package fr.univ_amu.iut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionUnique {
    private static final String CONNECT_URL = "jdbc:mysql://mysql-nicolas-trouin.alwaysdata.net/nicolas-trouin_td_jdbc";
    private static final String LOGIN = "148091_jdbc";
    private static final String PASSWORD = "wololo";

    private static ConnexionUnique instance;
    private Connection connection;

    private ConnexionUnique(){
        System.out.println("Connexion");
        try {
            Connection conn = DriverManager.getConnection(CONNECT_URL, LOGIN, PASSWORD);
            System.out.println("Connecte\n");
            connection = conn;
        } catch (SQLException e) {
            e.printStackTrace();// Arggg!!!
            System.out.println(e.getMessage() + "\n");
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static ConnexionUnique getInstance(){
        if(null == instance)
            instance = new ConnexionUnique();
        return instance;
    }
}
