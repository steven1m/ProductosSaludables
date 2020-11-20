/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.sql.*;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author Universidad
 */


public class Conexion {
    
    private static Connection con = null;
    
    public static Connection getConnection(){
        
        try{
            ResourceBundle rb = ResourceBundle.getBundle("servicios.jdbc");
            String driver = rb.getString("driver");
            String url = rb.getString("url");
            String pass = rb.getString("pass");
            String user = rb.getString("user");
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return  con;
    }
}
