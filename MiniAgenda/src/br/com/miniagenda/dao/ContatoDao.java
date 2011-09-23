package br.com.miniagenda.dao;

import br.com.miniagenda.bean.Contato;
import br.com.miniagenda.conexao.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Curso Técnico em Informática
 * Data Access Object
 * @author Valdinei Reis
 */
public class ContatoDao {
    
    public void inserir(Contato contato) throws SQLException {
        
        Connection con = null;
        
        try {
            con = ConexaoMySQL.getConnection();
            
            String sql = "INSERT INTO contato(nome, telefone) VALUES ( ?, ? )";
            
            PreparedStatement pstmtInsert = con.prepareStatement( sql );
            pstmtInsert.setString(1, contato.getNome());
            pstmtInsert.setString(2, contato.getTelefone());
            
            pstmtInsert.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir !! " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public List<Contato> listar() throws SQLException {

        Connection con = null;
        
        List<Contato> listaDeContatos = new ArrayList<Contato>();
        
        try {
            con = ConexaoMySQL.getConnection();
            
            Statement stmtRecords = con.createStatement();
            
            ResultSet rsRecords = 
                    stmtRecords.executeQuery("SELECT * FROM contato");

            while (rsRecords.next()) {
                
                Contato contato = new Contato();
                contato.setId( rsRecords.getLong("id") );
                contato.setNome( rsRecords.getString("nome") );
                contato.setTelefone( rsRecords.getString("telefone") );
                
                listaDeContatos.add(contato);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao efetuar busca !! " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        
        return listaDeContatos;
    }
    
    public void atualizar(Contato contato) throws SQLException {
        
        Connection con = null;
        
        try {
            con = ConexaoMySQL.getConnection();
            
            String sql = "UPDATE contato SET nome = ?, telefone = ? WHERE id = ?";
            
            PreparedStatement pstmtInsert = con.prepareStatement( sql );
            pstmtInsert.setString(1, contato.getNome());
            pstmtInsert.setString(2, contato.getTelefone());
            pstmtInsert.setLong(3, contato.getId());
            pstmtInsert.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar !! " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void excluir(Long id) throws SQLException {
        
        Connection con = null;
        
        try {
            con = ConexaoMySQL.getConnection();
            
            String sql = "DELETE FROM contato WHERE id = ?";
            
            PreparedStatement pstmtInsert = con.prepareStatement( sql );
            pstmtInsert.setLong(1, id);
            pstmtInsert.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao excluir !! " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public Contato buscarPorId(Long id) throws SQLException {

        Connection con = null;
        
        Contato contato = null;
        
        try {
            con = ConexaoMySQL.getConnection();
            
            String sql = "SELECT * FROM contato WHERE id = ?";
            
            PreparedStatement stmtRecords = con.prepareStatement(sql);
            stmtRecords.setLong(1, id);
            
            ResultSet rsRecords = stmtRecords.executeQuery();

            if (rsRecords.next()) {
                
                contato = new Contato();
                contato.setId( rsRecords.getLong("id") );
                contato.setNome( rsRecords.getString("nome") );
                contato.setTelefone( rsRecords.getString("telefone") );
                
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao efetuar busca !! " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        
        return contato;
    }
    
}
