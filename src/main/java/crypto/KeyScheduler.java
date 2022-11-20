package crypto;

import java.util.Arrays;

public class KeyScheduler {

    private byte[][] keys = new byte[7][];

    public KeyScheduler(String inputKey) {
        byte[] key = inputKey.getBytes();
        key = expandKey(key);
        keys[0] = key;
        generateKeys(key);
    }

    private byte[] expandKey(byte[] originalKey) {
        assert originalKey.length == 4 : "Key must have 32 bits";
        byte[] expandedKey = Arrays.copyOf(originalKey, 6);
        expandedKey[4] = (byte) (expandedKey[0] ^ expandedKey[1]);
        expandedKey[5] = (byte) (expandedKey[2] ^ expandedKey[3]);
        return expandedKey;
    }

    private void generateKeys(byte[] originalKey) {
        byte[] previousKey = originalKey;
        // Generate 6 additional keys
        for (int i = 1; i <= 6; i++) {
            byte[] newKey = generateKey(previousKey);
            keys[i] = newKey;
            previousKey = newKey;
        }
    }

    private byte[] generateKey(byte[] inputKey) {
        byte[] newKey = new byte[6];
        newKey[0] = (byte) (inputKey[0] ^ inputKey[2]);
        newKey[1] = (byte) (inputKey[1] ^ inputKey[3]);
        newKey[2] = (byte) (inputKey[2] ^ inputKey[4]);
        newKey[3] = (byte) (inputKey[3] ^ inputKey[5]);

        newKey[4] = (byte) (newKey[0] ^ newKey[1]);
        newKey[5] = (byte) (newKey[2] ^ inputKey[3]);

        return newKey;
    }
}
