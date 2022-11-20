package crypto;

import java.util.Arrays;

public class Encrypter {

    private byte[][] keys;
    IV initializationVec;

    public Encrypter(String userKey) {
        keys = KeyScheduler.getKeys(userKey);
        initializationVec = new IV();
    }

    private byte[] generateInitializationVec() {

    }

    public void encrypt() {

    }
}
