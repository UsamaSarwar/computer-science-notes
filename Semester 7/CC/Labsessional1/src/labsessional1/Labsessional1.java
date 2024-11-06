/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labsessional1;
import java.util.regex.*;
import java.util.Scanner;
/**
 *
 * @author Muhammad Tussadq
 */
public class Labsessional1 {
public static Scanner scan=new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Initialize any veriable: ");
        String s=scan.nextLine();
        System.out.println(Pattern.matches("[a-z]{0,9}", s));
        // TODO code application logic here
    }
    
}
