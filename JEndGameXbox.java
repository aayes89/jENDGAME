package jendgamexbox;

import java.util.Arrays;

/**
 *
 * @author Slam
 */
public class JEndGameXbox {

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
            // Lo que se espera que ocurra en la consola para lograr 
            // la ejecución arbitraria 
            System.out.println("Exception: " + e.getMessage());
        }

    }

}
