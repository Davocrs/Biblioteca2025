/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 */

package davidceducastur.es.biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author alu03d
 */
public class Biblioteca2025 {

    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;
    private ArrayList<Prestamo> prestamosHist;

    public Biblioteca2025() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.prestamosHist = new ArrayList<>();
    }

    public static void main(String[] args) {
        Biblioteca2025 b = new Biblioteca2025();
        b.cargaDatos();
        b.menuPrincipal();
        b.fueraPlazo();
    }

    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Gestion de Libros");
            System.out.println("2. Gestion de Usuarios");
            System.out.println("3. Gestion de Prestamos");
            System.out.println("9. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    menuLibros();
                    break;
                case 2:
                    menuUsuarios();
                    break;
                case 3:
                    menuPrestamos();
                    break;
            }
        } while (opcion != 9);

        sc.close();
    }
    
    //<editor-fold defaultstate="collapsed" desc="MENUS PRINCIPALES">
    
    public void menuLibros() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU LIBROS ---");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Eliminar Libro");
            System.out.println("3. Modificar Libro");
            System.out.println("4. Lista de Libros");
            System.out.println("9. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    nuevoLibro();
                    break;
                case 2:
                    eliminarLibro();
                    break;
                case 3:
                    modificarLibro();
                    break;
                case 4:
                    listaLibro();
                    break;
            }
        } while (opcion != 9);
    }

    public void menuUsuarios() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU USUARIOS ---");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Eliminar Usuario");
            System.out.println("3. Modificar Usuario");
            System.out.println("4. Lista de Usuarios");
            System.out.println("9. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    nuevoUsuario();
                    break;
                case 2:
                    eliminarUsuario();
                    break;
                case 3:
                    modificarUsuario();
                    break;
                case 4:
                    listaUsuario();
                    break;
            }
        } while (opcion != 9);
    }

    public void menuPrestamos() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU PRESTAMOS ---");
            System.out.println("1. Agregar Prestamo");
            System.out.println("2. Devolver Prestamo");
            System.out.println("3. Prorrogas");
            System.out.println("4. Listado genreral de Prestamos");
            System.out.println("5. Listado prestamos usuario");
            System.out.println("6. Listado prestamos libros (usuarios que lo leen)");
            System.out.println("9. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    nuevoPretamo();
                    break;
                case 2:
                    devolucionPrest();
                    break; 
                case 3:
                    prorrogaPrest();
                    break;
                case 4:
                    listaPretamo();
                    break;
                case 5:
                    listaPrestamosUsu();
                    break;
                case 6:
                    listaPrestamosLibro();
                    break;
            }
        } while (opcion != 9);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="MENU LIBROS">
    
    private void nuevoLibro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el isbn del libro:");
        String isbn = sc.nextLine();
        System.out.println("Introduce el titulo del libro:");
        String titulo = sc.nextLine();
        System.out.println("Introduce el autor del libro:");
        String autor = sc.nextLine();
        System.out.println("Introduce el genero del libro:");
        String genero = sc.nextLine();
        System.out.println("Introduce el numero de ejemplares:");
        int ejemplares = sc.nextInt();
        libros.add(new Libro(isbn, titulo, autor, genero, ejemplares));
        System.out.println("Libro agregado correctamente.");
    }

    private void eliminarLibro() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el isbn del libro a eliminar:");
        String isbn = sc.nextLine();
        int pos = buscaIsbn(isbn);
        if (pos != -1) {
            libros.remove(pos);
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("El libro no existe.");
        }

    }

    private void modificarLibro() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el isbn del libro a modificar:");
        int indice = sc.nextInt();
        sc.nextLine(); 
        if (indice >= 0 && indice < libros.size()) {
            Libro libro = libros.get(indice);
            System.out.println("Introduce el nuevo título (actual: " + libro.getTitulo() + "):");
            libro.setTitulo(sc.nextLine());
            System.out.println("Introduce el nuevo autor (actual: " + libro.getAutor() + "):");
            libro.setAutor(sc.nextLine());
            System.out.println("Introduce el nuevo genero (actual: " + libro.getGenero() + "):");
            libro.setGenero(sc.nextLine());
            System.out.println("Libro modificado correctamente.");
        } else {
            System.out.println("Ísbn inválido.");
        }

    }

    private void listaLibro() {
        for (Libro l : libros) {
            System.out.println(l);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="MENU USUARIOS">
    
    private void nuevoUsuario() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del usuario:");
        String dni = sc.nextLine();
        System.out.println("Introduce el nombre del usuario:");
        String nombre = sc.nextLine();
        System.out.println("Introduce el email del usuario:");
        String email = sc.nextLine();
        System.out.println("Introduce el telefono del usuario:");
        String telefono = sc.nextLine();
        usuarios.add(new Usuario(dni, nombre, email, telefono));
        System.out.println("Usuario agregado correctamente.");

    }

    private void eliminarUsuario() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del usuario a eliminar:");
        String dni = sc.nextLine();
        int pos = buscaDni(dni);
        if (pos != -1) {
            usuarios.remove(pos);
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("El usuario no existe.");
        }

    }

    private void modificarUsuario() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del usuario a modificar:");
        String dni = sc.nextLine();
        int pos = buscaDni(dni);
        if (pos != -1) {
            Usuario usuario = usuarios.get(pos);
            System.out.println("Introduce el nuevo email (actual: " + usuario.getEmail() + "):");
            usuario.setEmail(sc.nextLine());
            System.out.println("Introduce el nuevo teléfono (actual: " + usuario.getTelefono() + "):");
            usuario.setTelefono(sc.nextLine());
            System.out.println("Usuario modificado correctamente.");
        } else {
            System.out.println("El usuario no existe.");
        }

    }

    private void listaUsuario() {
        
        for (Usuario u : usuarios) {
            System.out.println(u);
        }

    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MENU PRESTAMO">
        
    private void nuevoPretamo() {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del usuario:");
        String dni = sc.nextLine();
        int pos = buscaDni(dni);
        if (pos == -1) {
            System.out.println("El usuario no existe.");
            return;
        }
        System.out.println("Introduce el ISBN del libro:");
        String isbn = sc.nextLine();
        int posLibro = buscaIsbn(isbn);
        if (posLibro == -1 || libros.get(posLibro).getEjemplares() <= 0) {
            System.out.println("El libro no existe o no hay ejemplares disponibles.");
            return;
        }
        LocalDate hoy = LocalDate.now();
        prestamos.add(new Prestamo(libros.get(posLibro), usuarios.get(pos), hoy, hoy.plusDays(15)));
        libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares() - 1);
        System.out.println("Préstamo creado correctamente.");
        
    }
        
    
    public void devolucionPrest() {
    
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del usuario:");
        String dni = sc.nextLine();
        System.out.println("Introduce el ISBN del libro:");
        String isbn = sc.nextLine();
        int pos = buscaPrestamo(dni, isbn);
        if (pos == -1) {
            System.out.println("No se encontro el préstamo.");
            return;
        }
       
        Prestamo p = prestamos.get(pos);
        prestamosHist.add(prestamos.get(pos));
        prestamos.remove(pos);
        p.setFechaDev(LocalDate.now());
        p.getLibroPrest().setEjemplares(p.getLibroPrest().getEjemplares() + 1);
        System.out.println("Devolucion realizada correctamente.");

        
    }
    
    private void prorrogaPrest(){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del usuario:");
        String dni = sc.nextLine();
        System.out.println("Introduce el ISBN del libro:");
        String isbn = sc.nextLine();
        int pos = buscaPrestamo(dni, isbn);
        if (pos == -1) {
            System.out.println("No se encontro el prestamo.");
            return;
        }
        Prestamo p = prestamos.get(pos);
        LocalDate fechaPrest = LocalDate.now();
        LocalDate fechaDev = fechaPrest.plusDays(15);
        p.setFechaPrest(fechaPrest);
        p.setFechaDev(fechaDev);
        System.out.println("Prórroga realizada correctamente. Nueva fecha de inicio: " + fechaPrest + ", nueva fecha de devolución: " + fechaDev);


    }
    
    private void listaPrestamosLibro() {
        
        String isbn = solicitaIsbn();
        int pos = buscaDni(isbn);
        if (pos == -1){
            System.out.println("No tengo a ningun libro con ese ISBN");
        }else{
            System.out.println("Usuarios que lo estan leyendo");
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(isbn)){
                    System.out.println(p.getUsuarioPrest());
            }
            }
                
            System.out.println("Usuarios que lo han leido");
            for (Prestamo p : prestamosHist) {
                if (p.getLibroPrest().getIsbn().equals(isbn)){
                    System.out.println(p.getUsuarioPrest());
                }

            }      
        }
    }
    
    public void fueraPlazo() {
    System.out.println("Préstamos fuera de plazo:");
    for (Prestamo p : prestamos) {
        if (p.getFechaDev().isBefore(LocalDate.now())) {
            System.out.println("Usuario: " + p.getUsuarioPrest().getNombre() + " - Libro: " + p.getLibroPrest().getTitulo() + " - Fecha de devolución: " + p.getFechaDev());
        }
    }
    }

    
    private void listaPretamo() {
        System.out.println("Listado de prestamos activos");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
        
        System.out.println("\nListado de prestamos historicos");
        
        for (Prestamo p : prestamosHist) {
            System.out.println(p);
        }
        
        for (Prestamo p : prestamos) {
                if (p.getFechaDev().isBefore(LocalDate.now())) {
                    System.out.println("Libro fuera de plazo: ");
                }
                System.out.println(p);
            }
        }
    
    private void listaPrestamosUsu(){
        
        String dni = solicitaDni();
        int pos = buscaDni(dni);

        if (pos == -1) {
            System.out.println("No tengo a nadie con ese DNI");
        } else {
            System.out.println("Prestamos activos de: " + usuarios.get(pos).getNombre());
            for (Prestamo p : prestamos){
                if (p.getUsuarioPrest().getDni().equals(dni)) {
                    if (p.getFechaDev().isBefore(LocalDate.now())){
                        System.out.println("Libro fuera de plazo: ");
                    }
                    System.out.println(p);
                }
            }
            System.out.println("Prestamos ya devueltos por: " + usuarios.get(pos).getNombre());
            for (Prestamo p : prestamosHist) {
                if (p.getUsuarioPrest().getDni().equals(dni)) {
                    System.out.println(p);
                }
            }
        }      
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="METODOS AUXILIARES">
    
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
    
    public String solicitaDni(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Teclea el DNI del usuario");
        String dni = sc.nextLine();
        return dni;
    }
    
    public String solicitaIsbn(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Teclea el Isbn del libro");
        String isbn = sc.nextLine();
        return isbn;
    }
    
    //</editor-fold>

    
    
}
