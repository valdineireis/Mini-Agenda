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
        
    private Connection con = null;
    private PreparedStatement ps = null;
    private Statement stm = null;
    private ResultSet rs = null;
    
    public void inserir(Contato contato) throws SQLException {
        
        try {
            con = ConexaoMySQL.getConnection();
            
            String sql = "INSERT INTO contato(nome, telefone) VALUES ( ?, ? )";
            
            ps = con.prepareStatement( sql );
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir !! " + ex.getMessage());
        } finally {
            fecharConexoes();
        }
    }
    
    public List<Contato> listar(String nome) throws SQLException {
        
        List<Contato> listaDeContatos = new ArrayList<Contato>();
        
        try {
            con = ConexaoMySQL.getConnection();
            
            stm = con.createStatement();
            
            String sql = "SELECT * FROM contato WHERE nome LIKE ?";
            
            ps = con.prepareStatement( sql );
            ps.setString(1, "%" + nome + "%");
            
            rs = ps.executeQuery();

            while (rs.next()) {
                
                Contato contato = new Contato();
                contato.setId( rs.getLong("id") );
                contato.setNome( rs.getString("nome") );
                contato.setTelefone( rs.getString("telefone") );
                
                listaDeContatos.add(contato);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao efetuar busca !! " + ex.getMessage());
        } finally {
            fecharConexoes();
        }
        
        return listaDeContatos;
    }
    
    public void atualizar(Contato contato) throws SQLException {
        
        try {
            con = ConexaoMySQL.getConnection();
            
            String sql = "UPDATE contato SET nome = ?, telefone = ? WHERE id = ?";
            
            ps = con.prepareStatement( sql );
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setLong(3, contato.getId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar !! " + ex.getMessage());
        } finally {
            fecharConexoes();
        }
    }
    
    public void excluir(Long id) throws SQLException {
        
        try {
            con = ConexaoMySQL.getConnection();
            
            String sql = "DELETE FROM contato WHERE id = ?";
            
            ps = con.prepareStatement( sql );
            ps.setLong(1, id);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao excluir !! " + ex.getMessage());
        } finally {
            fecharConexoes();
        }
    }
    
    public Contato buscarPorId(Long id) throws SQLException {
        
        Contato contato = null;
        
        try {
            con = ConexaoMySQL.getConnection();
            
            String sql = "SELECT * FROM contato WHERE id = ?";
            
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            
            rs = ps.executeQuery();

            if (rs.next()) {
                
                contato = new Contato();
                contato.setId( rs.getLong("id") );
                contato.setNome( rs.getString("nome") );
                contato.setTelefone( rs.getString("telefone") );
                
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro no SQL !! " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao efetuar busca !! " + ex.getMessage());
        } finally {
            fecharConexoes();
        }
        
        return contato;
    }
    
    /**
     * Método responsavel por fechar as Connection, PreparedStatement, 
     * Statement e ResultSet
     * @throws SQLException 
     */
    private void fecharConexoes() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
    
}
