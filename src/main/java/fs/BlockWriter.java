package fs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BlockWriter {
    public static int BLOCK_SIZE = 6;
    private FileOutputStream targetFile;

    public BlockWriter(String filePath) throws FileNotFoundException {
        this.targetFile = new FileOutputStream(filePath);
    }

    public void write(byte[] bytes) throws IOException {
        assert bytes.length == BLOCK_SIZE;
        targetFile.write(bytes);
    }

    public void close() throws IOException {
        targetFile.close();
    }
}
