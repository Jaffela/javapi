package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private String url="jdbc:mysql://localhost:3306/pethome";
    private String login="root";
    private String pwd="";
    private Connection conn ;
    private static DataSource instance;

    public DataSource(){
        try {
            conn= DriverManager.getConnection(url,login,pwd);
            System.out.println("Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getInstance(){
        if(instance==null){
            instance=new DataSource();
        }
        return instance;

    }

    public Connection getConn() {
        return conn;
    }
}
