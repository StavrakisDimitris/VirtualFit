package main.java;

import main.java.Main_Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnection {
    
    public static Connection getConnection(){
        
          Connection con = null;
          String myDriver ="com.mysql.jdbc.Driver";
          String myurl ="jdbc:mysql://localhost:3306/gym?zeroDateTimeBehavior=convertToNull";
        try {
               Class.forName(myDriver);
               con = DriverManager.getConnection(myurl, "root" , "" );
        } catch (ClassNotFoundException ex) {
               Logger.getLogger(Main_Class.class.getName()).log(Level.SEVERE, null, ex);     
           } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     return con;
    }
}
