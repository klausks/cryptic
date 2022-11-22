package crypto;

import java.util.Arrays;

import static crypto.Constants.ROUNDS;

public class Decrypter {

    private byte[][] keys;

    public Decrypter(String userKey) {
        this.keys = KeyScheduler.getKeys(userKey);
    }

    public byte[] decrypt(byte[] message) {
        for (int i = 0; i < ROUNDS; i++) {
            message = doRound(message, ROUNDS - 1 - i);
        }
        return message;
    }

    private byte[] doRound(byte[] message, int keyIndex) {
        byte[] encryptedMsg = Arrays.copyOf(message, message.length);
        Operations.rotateLeft(encryptedMsg, 1);
        Operations.swapBytes(encryptedMsg);
        Operations.invSubBytes(encryptedMsg);
        return Operations.xor(encryptedMsg, keys[keyIndex]);
    }
}
