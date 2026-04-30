package br.com.trabalhobiblioteca.model;

import java.util.Date;
import java.util.Objects;

public class Livro {
    private int id;
    private String nomeLivro;
    private String isbn;
    private String autor;
    private Date dataPublicacao;
    private double valorLivro;

    public Livro() {
        this.id = 0;
        this.nomeLivro = "";
        this.isbn = "";
        this.autor = "";
        this.dataPublicacao = null;
        this.valorLivro = 0;
    }

    public Livro(int id, String nomeLivro, String isbn, String autor, Date dataPublicacao, double valorLivro) {
        this.id = id;
        this.nomeLivro = nomeLivro;
        this.isbn = isbn;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.valorLivro = valorLivro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public double getValorLivro() {
        return valorLivro;
    }

    public void setValorLivro(double valorLivro) {
        this.valorLivro = valorLivro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.isbn, other.isbn);
    }

   
   
    
    
}


