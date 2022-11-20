package crypto;

import java.nio.charset.StandardCharsets;

public class Decrypter {

    private byte[][] keys;
    private IV initializationVec;
    public Decrypter(String userKey, IV initializationVec) {
        this.keys = KeyScheduler.reverseOrder(KeyScheduler.getKeys(userKey));
        this.initializationVec = initializationVec;
    }
}
