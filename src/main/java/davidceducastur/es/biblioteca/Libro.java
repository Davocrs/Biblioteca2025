/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 */
package davidceducastur.es.biblioteca;

import java.io.Serializable;

/**
 * Clase Libros para la Biblioteca2025
 * @author alu03d
 */
public class Libro implements Serializable{
    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private int ejemplares;

    public Libro(String isbn, String titulo, String autor, String genero, int ejemplares) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.ejemplares = ejemplares;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    @Override
    public String toString() {
        return isbn + "-" + titulo + "-" + autor + "-" + genero + "-" + ejemplares;
    }
}
