package br.com.miniagenda.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Curso Técnico em Informática
 * Cria a conexao com o banco de dados MySQL
 * @author Valdinei Reis
 */
public class ConexaoMySQL {
    
    // variavel que armazena a conexao. E só pode ser acessada através
    // do método getConnection
    private static Connection con;

    public static Connection getConnection() throws Exception {

        try {
            // é criada uma nova conexao se ela estiver nula ou fechada
            if (con == null || con.isClosed()) {
                
                // Esta linha invoca o driver mysql
                Class.forName("com.mysql.jdbc.Driver");
                
                // Localização do banco de dados
                String url = "jdbc:mysql://localhost:3306/miniagenda";
                String user = "root"; // Usuário com permissão de acesso ao banco de dados
                String password = ""; // Senha do usuário
                
                // Nesta linha conecta-se ao banco de dados
                con = DriverManager.getConnection(url, user, password);
            }
            return con;
        } catch (Exception ex) {
            // se houve algum erro, uma exceção é gerada para informar o erro
            throw new Exception(ex.getMessage());
        }

    }
    
}
