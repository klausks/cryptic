package crypto;

import fs.BlockReader;
import fs.BlockWriter;
import jdk.dynalink.Operation;

import java.io.IOException;
import java.util.Arrays;

import static crypto.Constants.ROUNDS;

public class Encrypter {

    private byte[][] keys;

    public Encrypter(String userKey) {
        keys = KeyScheduler.getKeys(userKey);
    }

    public byte[] encrypt(byte[] message) throws IOException {
        for (int i = 0; i < ROUNDS; i++) {
            message = doRound(message, i);
        }
        return message;
    }

    private void doRound(byte[] message, int keyIndex) {
        Operations.xor(message, keys[keyIndex]);
        Operations.subBytes(message);
        Operations.swapBytes(message);
        Operations.rotateRight(message, 1);
    }
}
