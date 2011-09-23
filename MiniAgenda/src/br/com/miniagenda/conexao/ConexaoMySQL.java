package br.com.miniagenda.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Curso Técnico em Informática
 * Cria a conexao com o banco de dados MySQL
 * @author Valdinei Reis
 */
public class ConexaoMySQL {
    
    private static Connection con;

    public static Connection getConnection() throws Exception {

        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/miniagenda";
                String user = "root";
                String password = "";
                
                con = DriverManager.getConnection(url, user, password);
            }
            return con;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }
    
}
