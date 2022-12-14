package crypto;

import java.util.Arrays;

import static crypto.Constants.BLOCK_SIZE;
import static crypto.Constants.ROUNDS;

public class KeyScheduler {
    public static byte[][] getKeys (String originalKey) {
        byte[] firstKey = originalKey.getBytes();
        firstKey = extendKey(firstKey);
        return generateKeys(firstKey);
    }

    private static byte[] extendKey(byte[] originalKey) {
        assert originalKey.length == 4 : "Key must have 32 bits";
        byte[] expandedKey = Arrays.copyOf(originalKey, 6);
        expandedKey[4] = (byte) (expandedKey[0] ^ expandedKey[1]);
        expandedKey[5] = (byte) (expandedKey[2] ^ expandedKey[3]);
        return expandedKey;
    }

    private static byte[][] generateKeys(byte[] originalKey) {
        byte[][] keys = new byte[ROUNDS][];
        keys[0] = originalKey;
        byte[] previousKey = originalKey;
        for (int i = 1; i < ROUNDS; i++) {
            byte[] newKey = generateKey(previousKey);
            keys[i] = newKey;
            previousKey = newKey;
        }
        return keys;
    }

    private static byte[] generateKey(byte[] inputKey) {
        byte[] newKey = new byte[BLOCK_SIZE];
        newKey[0] = (byte) (inputKey[0] ^ inputKey[2]);
        newKey[1] = (byte) (inputKey[1] ^ inputKey[3]);
        newKey[2] = (byte) (inputKey[2] ^ inputKey[4]);
        newKey[3] = (byte) (inputKey[3] ^ inputKey[5]);

        newKey[4] = (byte) (newKey[0] ^ newKey[1]);
        newKey[5] = (byte) (newKey[2] ^ inputKey[3]);

        return newKey;
    }
}
