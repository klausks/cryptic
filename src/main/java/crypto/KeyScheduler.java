package crypto;

import java.util.Arrays;

public class KeyScheduler {

    private byte[][] keys = new byte[7][];

    public KeyScheduler(String inputKey) {
        byte[] key = inputKey.getBytes();
        key = expandKey(key);
    }

    private byte[] expandKey(byte[] inputKey) {
        assert inputKey.length == 4 : "Key must have 32 bits";
        byte[] expandedKey = Arrays.copyOf(inputKey, 6);
        expandedKey[4] = (byte) (expandedKey[0] ^ expandedKey[1]);
        expandedKey[5] = (byte) (expandedKey[2] ^ expandedKey[3]);
        return expandedKey;
    }
}
