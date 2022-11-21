package crypto;

import java.util.Arrays;

import static crypto.Constants.ROUNDS;

public class Encrypter {

    private byte[][] keys;

    public Encrypter(String userKey) {
        keys = KeyScheduler.getKeys(userKey);
    }

    public byte[] encrypt(byte[] message) {
        for (int i = 0; i < ROUNDS; i++) {
            message = doRound(message, i);
        }
        return message;
    }

    private byte[] doRound(byte[] message, int keyIndex) {
        byte[] encryptedMsg = Operations.xor(message, keys[keyIndex]);
        Operations.subBytes(encryptedMsg);
        Operations.swapBytes(encryptedMsg);
        Operations.rotateRight(encryptedMsg, keyIndex);
        return encryptedMsg;
    }
}
