package crypto;

public class Encrypter {

    private byte[][] keys;
    byte[] initializationVec;

    public Encrypter(String userKey) {
        keys = KeyScheduler.getKeys(userKey);
        initializationVec = generateInitializationVec();
    }

    private byte[] generateInitializationVec() {

    }

    public void encrypt() {

    }
}
