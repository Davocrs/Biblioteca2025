/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidceducastur.es.biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alu03d
 */
public class CrespoDavidBiblioteca {
    
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;
    private ArrayList<Prestamo> prestamosHist;

    public CrespoDavidBiblioteca() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.prestamosHist = new ArrayList<>();
    }

    public static void main(String[] args) {
        CrespoDavidBiblioteca b = new CrespoDavidBiblioteca();
        b.cargaDatos();
        b.menuPrincipal();
    }
    
    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. DEVOLUCION PRESTAMO");
            System.out.println("2. lIBROS PRESATADOS POR USUARIO");
            System.out.println("3. LISTADOS");
            System.out.println("9. SALIR");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    devPrestamo();
                    break;
                case 2:

                    break;
                case 3:
                    listados();
                    break;
            }
        } while (opcion != 9);

        sc.close();
    }
    
    
    // EJERCICIO 1 //
    public void devPrestamo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el dni del usuario:");
        String dni = sc.nextLine();
        System.out.println("Introduce el isbn del libro:");
        String isbn = sc.nextLine();
        int pos = buscaPrestamo(dni, isbn);
        if (pos == -1) {
            System.out.println("No se encontro el prestamo.");
            return;
        }
       
        Prestamo p = prestamos.get(pos);
        prestamosHist.add(prestamos.get(pos));
        prestamos.remove(pos);
        p.setFechaDev(LocalDate.now());
        p.getLibroPrest().setEjemplares(p.getLibroPrest().getEjemplares() + 1);
        System.out.println("Devolucion realizada correctamente");
        
    }
       
    // EJERCICIO 2 //
    public ArrayList<Libro> prestamosUsu(String dni, int año) {
        ArrayList<Libro> librosUsu = new ArrayList<>();
        for (Prestamo p : prestamosHist) {
            if (p.getUsuarioPrest().getDni().equals(dni) && p.getFechaPrest().getYear() == año) {
                librosUsu.add(p.getLibroPrest());
            }
        }
        return librosUsu;
    }

    

    // EJERCICIO 3 //
    public void listados(){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Prestamos que tienen que ser devueltos hoy:");
        LocalDate hoy = LocalDate.now();
        for (Prestamo p : prestamos) {
            if (p.getFechaDev().isEqual(hoy)) {
                System.out.println(p);
            }
        }
        
        System.out.println("");
        
        System.out.println("Introduce el género:");
        String genero = sc.nextLine();
        for (Libro l : libros) {
            if (l.getGenero().equalsIgnoreCase(genero)) {
                System.out.println(l);
            }
        }

        
        System.out.println("");
        
        System.out.println("Usuarios que nunca han realizado un prestamo:");
        for (Usuario u : usuarios) {
            boolean encontrado = false;
            for (Prestamo p : prestamos) {
                if (p.getUsuarioPrest().getDni().equals(u.getDni())) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                for (Prestamo p : prestamosHist) {
                    if (p.getUsuarioPrest().getDni().equals(u.getDni())) {
                        encontrado = true;
                        break;
                    }
                }
            }
            if (!encontrado) {
                System.out.println(u);
            }
        }

    }
    
    public int buscaDni(String dni){
        int pos=-1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getDni().equals(dni)){
                pos=i;
                break;
            } 
        }
        return pos;
    }
    
    public int buscaIsbn(String isbn){
        int pos=-1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (libros.get(i).getIsbn().equals(isbn)){
                pos=i;
                break;
            } 
        }
        return pos;
    }
    
    public int buscaPrestamo(String dni, String isbn){
        
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getUsuarioPrest().getDni().equals(dni) && 
                prestamos.get(i).getLibroPrest().getIsbn().equals(isbn)) {
                return i;
            }
        }
        return -1;
    }
    
    public void cargaDatos() {
        libros.add(new Libro("1-11","El Hobbit","JRR Tolkien","Aventuras",3)); 
        libros.add(new Libro("1-22","El Silmarillon","JRR Tolkien","Aventuras",3)); 
        libros.add(new Libro("1-33","El Medico","N. Gordon","Aventuras",4)); 
        libros.add(new Libro("1-44","Chaman","N. Gordon","Aventuras",3)); 
        libros.add(new Libro("1-55","Momo","M. Ende","Aventuras",2)); 
        libros.add(new Libro("1-66","Paraiso inhabitado","A.M.Matute","Aventuras",2)); 
        libros.add(new Libro("1-77","Olvidado Rey Gudu","A.M.Matute","Aventuras",2)); 
        libros.add(new Libro("1-88","El ultimo barco","D.Villar","Novela Negra",3)); 
        libros.add(new Libro("1-99","Ojos de agua","D.Villar","Novela Negra",2)); 

        usuarios.add(new Usuario("11","Ana","ana@email.com","621111111")); 
        usuarios.add(new Usuario("22","David","david@email.com","622222222")); 
        usuarios.add(new Usuario("33","Bea","bea@email.com","623333333")); 
        usuarios.add(new Usuario("44","Lucas","lucas@email.com","624444444")); 
        usuarios.add(new Usuario("55","Carlota","carlota@email.com","625555555")); 
        usuarios.add(new Usuario("66","Juan","juan@email.com","626666666"));
        
        LocalDate hoy= LocalDate.now();
        prestamos.add(new Prestamo(libros.get(2),usuarios.get(0), hoy,hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(8),usuarios.get(2), hoy,hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5),usuarios.get(4), hoy,hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5),usuarios.get(0), hoy,hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(6),usuarios.get(2), hoy,hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(2),usuarios.get(1), hoy,hoy.plusDays(15)));
    
    }
    
}
