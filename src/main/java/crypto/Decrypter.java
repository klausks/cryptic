package crypto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static crypto.Constants.ROUNDS;

public class Decrypter {

    private byte[][] keys;
    private IV initializationVec;
    public Decrypter(String userKey, IV initializationVec) {
        this.keys = KeyScheduler.reverseOrder(KeyScheduler.getKeys(userKey));
        this.initializationVec = initializationVec;
    }

    public void decrypt(byte[] message) throws IOException {
        for (int i = ROUNDS - 1; i >= 0; i--) {
            message = doRound(message, i);
        }
    }

    private byte[] doRound(byte[] message, int keyIndex) {
        Operations.rotateRight(message, 1);
        Operations.swapBytes(message);
        Operations.subBytes(message);
        return Operations.xor(message, keys[keyIndex]);
    }
}
