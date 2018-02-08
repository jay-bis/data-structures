/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jbiscupski
 */
public class Arrays {
    public static void main (String[] args) {
        int[] numbers = {1, 2, 3};
        int length = numbers[2];
        char[] chars = new char[length];
        chars[numbers.length - 1] = 'y';
        System.out.println("Done!"); 
    }
}
