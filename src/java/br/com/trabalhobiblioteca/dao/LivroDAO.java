package br.com.trabalhobiblioteca.dao;

import br.com.trabalhobiblioteca.model.Livro;
import br.com.trabalhobiblioteca.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements GenericDAO {

    private Connection conexao;

    public LivroDAO() throws Exception {
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Livro oLivro = (Livro) objeto;

        if (oLivro.getId() == 0) {
            return this.inserir(oLivro);
        } else {
            return this.alterar(oLivro);
        }
    }

    @Override
    public Boolean inserir(Object objeto) {
        Livro oLivro = (Livro) objeto;

        String sql = "INSERT INTO livros "
                + "(nomeLivro, isbn, autor, dataPublicacao, valorLivro) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, oLivro.getNomeLivro());
            stmt.setString(2, oLivro.getIsbn());
            stmt.setString(3, oLivro.getAutor());
            stmt.setDate(4, new java.sql.Date(oLivro.getDataPublicacao().getTime()));
            stmt.setDouble(5, oLivro.getValorLivro());

            stmt.executeUpdate();
            conexao.commit();

            stmt.close();

            return true;

        } catch (Exception e) {
            try {
                conexao.rollback();
            } catch (Exception ex) {
                System.out.println("Erro no rollback inserir: " + ex.getMessage());
            }

            System.out.println("Erro ao inserir: " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Livro oLivro = (Livro) objeto;

        String sql = "UPDATE livros SET "
                + "nomeLivro = ?, "
                + "isbn = ?, "
                + "autor = ?, "
                + "dataPublicacao = ?, "
                + "valorLivro = ? "
                + "WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, oLivro.getNomeLivro());
            stmt.setString(2, oLivro.getIsbn());
            stmt.setString(3, oLivro.getAutor());
            stmt.setDate(4, new java.sql.Date(oLivro.getDataPublicacao().getTime()));
            stmt.setDouble(5, oLivro.getValorLivro());
            stmt.setInt(6, oLivro.getId());

            stmt.executeUpdate();
            conexao.commit();

            stmt.close();

            return true;

        } catch (Exception e) {
            try {
                conexao.rollback();
            } catch (Exception ex) {
                System.out.println("Erro no rollback alterar: " + ex.getMessage());
            }

            System.out.println("Erro ao alterar: " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean excluir(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            conexao.commit();

            stmt.close();

            return true;

        } catch (Exception e) {
            try {
                conexao.rollback();
            } catch (Exception ex) {
                System.out.println("Erro no rollback excluir: " + ex.getMessage());
            }

            System.out.println("Erro ao excluir: " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Object carregar(int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Livro oLivro = new Livro();

                oLivro.setId(rs.getInt("id"));
                oLivro.setNomeLivro(rs.getString("nomeLivro"));
                oLivro.setIsbn(rs.getString("isbn"));
                oLivro.setAutor(rs.getString("autor"));
                oLivro.setDataPublicacao(rs.getDate("dataPublicacao"));
                oLivro.setValorLivro(rs.getDouble("valorLivro"));

                rs.close();
                stmt.close();

                return oLivro;
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Erro ao carregar livro: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();

        String sql = "SELECT * FROM livros ORDER BY id";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro oLivro = new Livro();

                oLivro.setId(rs.getInt("id"));
                oLivro.setNomeLivro(rs.getString("nomeLivro"));
                oLivro.setIsbn(rs.getString("isbn"));
                oLivro.setAutor(rs.getString("autor"));
                oLivro.setDataPublicacao(rs.getDate("dataPublicacao"));
                oLivro.setValorLivro(rs.getDouble("valorLivro"));

                resultado.add(oLivro);
            }

            rs.close();
            stmt.close();

            System.out.println("TOTAL LIVROS: " + resultado.size());

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
            e.printStackTrace();
        }

        return resultado;
    }
}
    