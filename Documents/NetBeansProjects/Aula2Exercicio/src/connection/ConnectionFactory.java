/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Pedro
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/Aula1-Exercicio2";
    private static final String USER = "postgres";
    private static final String SENHA = "mlovatto";
    
    
    public static Connection getConnection(){
        
        try {
            
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, SENHA);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
                
    } 
    
    public static void closeConnection(Connection con){
        
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro!"+ex);
            }
        }
        
        
    }
    
    public static void CloseConnection(Connection con, PreparedStatement prep){
       
        if(prep != null){
            try {
                prep.close();
            } catch (SQLException ex) {
               System.out.println("Ocorreu um erro!"+ex);
            }
        }
        closeConnection(con);
        
        
    }
    
  
    public static void CloseConnection (Connection con, PreparedStatement prep, ResultSet rs){
        
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                 System.out.println("Ocorreu um erro!"+ex);
            }
        }
        
        CloseConnection(con, prep);
        
    } 
       
        
    }

    
    
    
    
    
    

