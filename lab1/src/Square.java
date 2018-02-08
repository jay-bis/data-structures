/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack Biscupski
 */
public class Square {
    int sideLength;
    
    public Square(int s) {
        sideLength = s;
    }
    
    public int getSideLength() {
        return sideLength;
    }
    
    public static void main(String[] args) {
        Square s1 = new Square(5);
        System.out.println("The side length is " + s1.getSideLength());
    }
}
