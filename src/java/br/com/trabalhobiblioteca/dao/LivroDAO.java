
package br.com.trabalhobiblioteca.dao;

import br.com.trabalhobiblioteca.model.Livro;
import br.com.trabalhobiblioteca.utils.SingleConnection;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroDAO implements GenericDAO{
    
    private Connection conexao;

    public LivroDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

   @Override
    public Boolean cadastrar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Boolean inserir(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Boolean alterar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Boolean excluir(int numero) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object carregar(int numero) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public List<Object> listar() {
      List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from livros order by id";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
             while (rs.next()) {                
               Livro oLivro = new Livro();
                oLivro.setId(rs.getInt("id"));
                oLivro.setNomeLivro(rs.getString("nomeLivro"));
                oLivro.setIsbn(rs.getString("isbn"));
                oLivro.setAutor(rs.getString("autor"));
                oLivro.setDataPublicacao(rs.getDate("dataPublicacao"));
                oLivro.setValorLivro(rs.getDouble("valorLivro"));
                resultado.add( oLivro);
            }
             System.out.println("TOTAL LIVROS: " + resultado.size());
        }catch (SQLException ex) {
            System.out.println("Erro ao listar livros: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;          
    }
}
    