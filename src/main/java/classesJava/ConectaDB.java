/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesJava;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bartolomeu
 */
public class ConectaDB {
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost:3306/bdcarros";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String DRIVERDB = "com.mysql.cj.jdbc.Driver";
    
    public  ConectaDB(){
        this.conexao = getConnection();
    }


    public static Connection getConnection() {
        if (conexao == null) {
            try {
                Class.forName(DRIVERDB);
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
            } catch (SQLException ex) {
                Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conexao;
    }

    public static void fechaConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
                PreparedStatement preparedStatement = conexao.prepareStatement("Your SQL Query Here");
            } catch (SQLException ex) {
                Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
