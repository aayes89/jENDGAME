/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jendgamexbox;

import java.util.Arrays;

/**
 *
 * @author localadmin
 */
public class JEndGameXbox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Exploit exp = new Exploit();
        // Ejemplo de uso
        int width = 128;
        int height = 128;
        int depth = 4;
        byte[] data = new byte[width * height * depth]; // Example data
        int descriptor = 8;
        boolean rle = true;

        byte[] compressedData = exp.rleCompress(data, width);
        System.out.println(Arrays.toString(compressedData));

        // Example usage
        byte[] tgaData = exp.makeTGA(width, height, depth, data, descriptor, rle);
        System.out.println(Arrays.toString(tgaData));

        try {
            byte[] unswizzledData = exp.sutil.unswizzle32(data, width, height);
            System.out.println(new String(exp.sutil.unswizzle32(data, width, height)));
            exp.makeHelper(true, false);
        } catch (Exception e) {
            System.out.println(": " + e.getMessage());
        }

    }

}
