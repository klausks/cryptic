package crypto;

import java.util.Arrays;
import java.util.BitSet;

public class Operations {
    public static void swapBytes(byte[] input) {
        swapByte(input, 0, 3);
        swapByte(input, 1, 4);
        swapByte(input, 2, 5);
    }

    public static void rotateRight(byte[] input, int n) {
        while (n >= 0) {
            byte b6 = input[5];
            for (int i = input.length - 1; i > 0; i--) {
                input[i] = input[i - 1];
            }
            input[0] = b6;
            n--;
        }
    }

    public static void rotateLeft(byte[] input, int n) {
        while (n >= 0) {
            byte b1 = input[0];
            for (int i = 0; i < input.length - 1; i++) {
                input[i] = input[i + 1];
            }
            input[5] = b1;
            n--;
        }
    }

    public static void subBytes(byte[] input) {
        SBox.substitute(input);
    }

    public static void invSubBytes(byte[] input) {
        SBox.invSubstitute(input);
    }

    private static void swapByte(byte[] input, int i1, int i2) {
        byte tmp;
        tmp = input[i1];
        input[i1] = input[i2];
        input[i2] = tmp;
    }
}
