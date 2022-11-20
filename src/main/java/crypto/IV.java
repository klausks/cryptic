package crypto;

public class IV {
    private byte[] vector;
    public IV() {
        Double rand = Math.random();
        long longRand = rand.longValue() % 64;
        vector = new byte[] {
                (byte) (longRand & 0xff),
                (byte) ((longRand >>> 8) & 0xff),
                (byte) ((longRand >>> 16) & 0xff),
                (byte) ((longRand >>> 24) & 0xff),
                (byte) ((longRand >>> 32) & 0xff),
                (byte) ((longRand >>> 40) & 0xff)
        };
    }

    public byte[] getVector() {
        return this.vector;
    }
}
