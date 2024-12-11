/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package davidceducastur.es.biblioteca;

import java.util.ArrayList;

/**
 *
 * @author alu03d
 */
public class Biblioteca2025 {

    private  ArrayList<Libro> libros;
    private  ArrayList<Usuario> usuarios;
    private  ArrayList<Prestamo> prestamos;
    
    public Biblioteca2025() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public static void main(String[] args) {
        Biblioteca2025 b = new Biblioteca2025();
        b.cargaDatos();
        b.menu();
    }

    public void cargaDatos() {
        
    }

    public void menu() {
        
    }
        
}
