/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jbiscupski
 */
public class Conditionals {
    public static void main (String[] args) {
        String a = new String("Wow");
        String b = "Wow";
        String c = a;
        String d = c;

        boolean b1 = a.equals(b);
        boolean b2 = b == b;
        boolean b3 = c.equals(a);

        if (b1 && b2 && b3) {
            System.out.println("Success!");
        }
    }
}
