/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davidceducastur.es.biblioteca;

/**
 *
 * @author alu03d
 */
public class MetodosAux {
    
        public static boolean esInt (String s){
        
        int n;
        try {
            n=Integer.parseInt(s);
            return true;        
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public static boolean esDouble (String s) {
        double n;
        try {
            n = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        } 
    }
    
}
