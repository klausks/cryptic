package crypto;

public class IV {
    private byte[] vector;
    public IV() {
        long randomLong = (long) (Math.random() * (Long.MAX_VALUE));
        vector = new byte[] {
                (byte) (randomLong & 0xff),
                (byte) ((randomLong >>> 8) & 0xff),
                (byte) ((randomLong >>> 16) & 0xff),
                (byte) ((randomLong >>> 24) & 0xff),
                (byte) ((randomLong >>> 32) & 0xff),
                (byte) ((randomLong >>> 40) & 0xff)
        };
    }

    public byte[] getVector() {
        return this.vector;
    }
}
