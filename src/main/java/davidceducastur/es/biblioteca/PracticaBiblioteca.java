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
public class PracticaBiblioteca {
    
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;
    private ArrayList<Prestamo> prestamosHist;
    
    public PracticaBiblioteca() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.prestamosHist = new ArrayList<>();
    }

    public static void main(String[] args) {
        PracticaBiblioteca b = new PracticaBiblioteca();
        b.cargaDatos();
        b.menuPrincipal();
    }
    
    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Listado de libros sin prestamos");
            System.out.println("2. Listado de usuarios con prestamos");
            System.out.println("3. Listado prestamos de un usuario en un año");
            System.out.println("4. Listado prestamos por año contabilizados");
            System.out.println("9. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    librosSinPrestamos();
                    break;
                case 2:
                    usuariosConPrestamos();
                    break;
                case 3:
                    prestamosUsuarioEnAño();  
                    break;
                case 4:
                    prestamosPorAño();
                    break;
            }

        } while (opcion != 9);

        sc.close();
    }
    
    public void librosSinPrestamos(){
        
        System.out.println("Libros actualmente no prestados:");
        for (Libro l : libros) {
            boolean prestadoAhora = false;

            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(l.getIsbn())) {
                    prestadoAhora = true;
                    break;
                }
            }

            if (!prestadoAhora) {
                System.out.println(l);
            }
        }

        System.out.println("\nLibros nunca prestados:");
        for (Libro l : libros) {
            boolean prestado = false;

            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(l.getIsbn())) {
                    prestado = true;
                    break;
                }
            }
            if (!prestado) {
                for (Prestamo p : prestamosHist) {
                    if (p.getLibroPrest().getIsbn().equals(l.getIsbn())) {
                        prestado = true;
                        break;
                    }
                }
            }

            if (!prestado) {
                System.out.println(l);
            }
        }
    }

    public void usuariosConPrestamos() {
        for (Usuario u : usuarios) {
            boolean tienePrestamo = false;

            for (Prestamo p : prestamos) {
                if (p.getUsuarioPrest().getDni().equals(u.getDni())) {
                    tienePrestamo = true;
                    break;
                }
            }

            if (tienePrestamo) {
                System.out.println(u);
            }
        }
    }
    
    public void prestamosUsuarioEnAño() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el DNI del usuario: ");
        String dni = sc.nextLine();

        int pos = buscaDni(dni);
        if (pos == -1) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.print("Introduce el año (ej. 2023): ");
        int año = sc.nextInt();

        for (Prestamo p : prestamosHist) {
            if (p.getUsuarioPrest().getDni().equals(dni) && p.getFechaPrest().getYear() == año) {
                System.out.println(p.getLibroPrest());
            }
        }
    }
    
    public void prestamosPorAño() {
        
        int contador = 0;
                
        System.out.println("Prestamos año: 2023");
        
        for (Prestamo p : prestamosHist) {
            if (p.getFechaPrest().getYear() == 2023){
                System.out.println(p.getLibroPrest());
            }
            contador++;
        }
        
        System.out.println("El total de prestamos en 2023 es: " + contador);
        
        System.out.println("");
        
        System.out.println("Prestamos año: 2024");
        
        for (Prestamo p : prestamosHist) {
            if (p.getFechaPrest().getYear() == 2024){
                System.out.println(p.getLibroPrest());
            }
            contador++;
        }
        
        System.out.println("El total de prestamos en 2024 es: " + contador);
        
        System.out.println("");
        
        System.out.println("Prestamos año: 2025");
        
        for (Prestamo p : prestamosHist) {
            if (p.getFechaPrest().getYear() == 2025){
                System.out.println(p.getLibroPrest());
            }
            contador++;
        }
        
        System.out.println("El total de prestamos en 2025 es: " + contador);
        
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
    
    public void cargaDatos(){
        
        libros.add(new Libro("1-00","El Hobbit","JRR Tolkien","Aventuras",3)); 
        libros.add(new Libro("1-11","El Silmarillon","JRR Tolkien","Aventuras",3)); 
        libros.add(new Libro("1-22","El Medico","N. Gordon","Aventuras",4)); 
        libros.add(new Libro("1-33","Chaman","N. Gordon","Aventuras",3)); 
        libros.add(new Libro("1-44","Momo","M. Ende","Aventuras",2)); 
        libros.add(new Libro("1-55","Paraiso inhabitado","A.M.Matute","Aventuras",2)); 
        libros.add(new Libro("1-66","Olvidado Rey Gudu","A.M.Matute","Aventuras",2)); 
        libros.add(new Libro("1-77","El ultimo barco","D.Villar","Novela Negra",3)); 
        libros.add(new Libro("1-88","Ojos de agua","D.Villar","Novela Negra",9)); 

        usuarios.add(new Usuario("00","Ana","ana@email.com","621111111")); 
        usuarios.add(new Usuario("11","David","david@email.com","622222222")); 
        usuarios.add(new Usuario("22","Bea","bea@email.com","623333333")); 
        usuarios.add(new Usuario("33","Lucas","lucas@email.com","624444444")); 
        usuarios.add(new Usuario("44","Carlota","carlota@email.com","625555555")); 
        usuarios.add(new Usuario("55","Juan","juan@email.com","626666666"));
        
        LocalDate hoy= LocalDate.now();
        prestamos.add(new Prestamo(libros.get(0),usuarios.get(0), hoy.minusDays(20),hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(0),usuarios.get(0), hoy,hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5),usuarios.get(0), hoy,hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5),usuarios.get(0), hoy.minusDays(20),hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(1),usuarios.get(4), hoy.minusDays(20),hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(2),usuarios.get(4), hoy.minusDays(20),hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(3),usuarios.get(4), hoy.minusDays(20),hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(1),usuarios.get(4), hoy,hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(8),usuarios.get(1), hoy,hoy.plusDays(15)));
        
        prestamosHist.add(new Prestamo(libros.get(0),usuarios.get(0),LocalDate.parse("2023-01-01"),LocalDate.parse("2023-01-20")));//OUT
        prestamosHist.add(new Prestamo(libros.get(2),usuarios.get(0),LocalDate.parse("2023-02-02"),LocalDate.parse("2023-02-11")));
        prestamosHist.add(new Prestamo(libros.get(7),usuarios.get(4),LocalDate.parse("2023-10-10"),LocalDate.parse("2023-10-20")));
        prestamosHist.add(new Prestamo(libros.get(5),usuarios.get(4),LocalDate.parse("2023-11-11"),LocalDate.parse("2023-11-30")));//OUT
        prestamosHist.add(new Prestamo(libros.get(1),usuarios.get(1),LocalDate.parse("2023-12-12"),LocalDate.parse("2023-12-28")));//OUT
        prestamosHist.add(new Prestamo(libros.get(7),usuarios.get(2),LocalDate.parse("2024-08-08"),LocalDate.parse("2024-08-18")));
        prestamosHist.add(new Prestamo(libros.get(6),usuarios.get(3),LocalDate.parse("2024-09-09"),LocalDate.parse("2024-09-19")));
        prestamosHist.add(new Prestamo(libros.get(0),usuarios.get(0),LocalDate.parse("2024-10-10"),LocalDate.parse("2024-10-30")));//OUT
        prestamosHist.add(new Prestamo(libros.get(2),usuarios.get(0),LocalDate.parse("2024-11-11"),LocalDate.parse("2024-11-21")));
        prestamosHist.add(new Prestamo(libros.get(7),usuarios.get(4),LocalDate.parse("2024-12-12"),LocalDate.parse("2024-12-28")));//OUT
        prestamosHist.add(new Prestamo(libros.get(5),usuarios.get(4),LocalDate.parse("2025-01-01"),LocalDate.parse("2025-01-11")));
        prestamosHist.add(new Prestamo(libros.get(1),usuarios.get(1),LocalDate.parse("2025-01-01"),LocalDate.parse("2025-01-15")));
    }
    
}
