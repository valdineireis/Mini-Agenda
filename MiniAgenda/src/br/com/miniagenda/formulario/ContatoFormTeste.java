package br.com.miniagenda.formulario;

import br.com.miniagenda.bean.Contato;
import br.com.miniagenda.dao.ContatoDao;
import java.util.List;

/**
 * Curso Técnico em Informática
 * Classe que vai interagir com o usuario
 * @author Valdinei Reis
 */
public class ContatoFormTeste {
    
    public static void inserir(String nome, String telefone) {
        ContatoDao db = new ContatoDao();
        
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setTelefone(telefone);
        
        try {
            
            db.inserir(contato);
            
        } catch (Exception ex) {
            System.out.println("Erro. " + ex.getMessage());
        }
    }
    
    public static void listar() {
        ContatoDao db = new ContatoDao();
        
        try {
            
            List<Contato> contatos = db.listar();
            
            for (Contato contato : contatos) {
                System.out.println("id: " + contato.getId());
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Telefone: " + contato.getTelefone());
                System.out.println(".....................................");
            }
            
        } catch (Exception ex) {
            System.out.println("Erro. " + ex.getMessage());
        }
    }
    
    public static void atualizar(Long id, String novoNome, String NovoTelefone) {
        ContatoDao db = new ContatoDao();
        
        Contato contato = new Contato();
        contato.setId(id);
        contato.setNome(novoNome);
        contato.setTelefone(NovoTelefone);
        
        try {
            
            db.atualizar(contato);
            
        } catch (Exception ex) {
            System.out.println("Erro. " + ex.getMessage());
        }
    }
    
    public static void excluir(Long id) {
        ContatoDao db = new ContatoDao();
        
        try {
            
            db.excluir(id);
            
        } catch (Exception ex) {
            System.out.println("Erro. " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        
        inserir("Contato Teste", "28 4444-6666");
//        atualizar(4L, "Contato Atualizado 04", "28 2223-4445");
//        excluir(5L);
        listar();
        
    }
    
}
