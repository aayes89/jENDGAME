
package jendgamexbox;

/**
 *
 * @author Slam
 */
public class SwizzleUtils {

    private int[] generateSwizzleMasks(int width, int height) {
        if (width <= 0 || (width & (width - 1)) != 0) {
            throw new IllegalArgumentException("Width must be a power of 2");
        }
        if (height <= 0 || (height & (height - 1)) != 0) {
            throw new IllegalArgumentException("Height must be a power of 2");
        }

        int x = 0, y = 0;
        int bit = 1, maskBit = 1;
        boolean done = false;

        while (!done) {
            done = true;
            if (bit < width) {
                x |= maskBit;
                maskBit <<= 1;
                done = false;
            }
            if (bit < height) {
                y |= maskBit;
                maskBit <<= 1;
                done = false;
            }
            bit <<= 1;
        }

        return new int[]{x, y};
    }

    private int fillSwizzlePattern(int pattern, int value) {
        int result = 0;
        int bit = 1;

        while (value != 0) {
            if ((pattern & bit) != 0) {
                result |= (value & 1) != 0 ? bit : 0;
                value >>= 1;
            }
            bit <<= 1;
        }

        return result;
    }

    public byte[] unswizzle32(byte[] data, int width, int height) {
        int[] masks = generateSwizzleMasks(width, height);
        int maskX = masks[0];
        int maskY = masks[1];

        byte[] dstBuf = new byte[data.length];

        for (int y = 0; y < height; y++) {
            int srcYOffset = fillSwizzlePattern(maskY, y) * 4;
            int dstYOffset = width * y * 4;

            for (int x = 0; x < width; x++) {
                int srcOffset = srcYOffset + fillSwizzlePattern(maskX, x) * 4;
                int dstOffset = dstYOffset + x * 4;
                System.arraycopy(data, srcOffset, dstBuf, dstOffset, 4);
            }
        }

        return dstBuf;
    }

}
