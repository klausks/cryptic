package fs;

import java.io.*;

public class BlockReader {

    public static int BLOCK_SIZE = 6;
    private FileInputStream sourceFile;

    public BlockReader(String filePath) throws FileNotFoundException {
        this.sourceFile = new FileInputStream(filePath);
    }

    public byte[] readBlock() throws IOException {
        return sourceFile.readNBytes(BLOCK_SIZE);
    }

    public void close() throws IOException {
        sourceFile.close();
    }
}
