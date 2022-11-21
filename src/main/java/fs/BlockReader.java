package fs;

import java.io.*;
import java.util.Arrays;

public class BlockReader {

    public static int BLOCK_SIZE = 6;
    private FileInputStream srcFileStream;
    private String srcFileName;
    private String srcFolder;

    public BlockReader(String filePath) throws FileNotFoundException {
        this.srcFileName = new File(filePath).getName();
        this.srcFolder = new File(filePath).getParentFile().getPath();
        this.srcFileStream = new FileInputStream(filePath);
    }

    public byte[] readBlock() throws IOException {
        byte[] block = srcFileStream.readNBytes(BLOCK_SIZE);
        if (block.length < BLOCK_SIZE && block.length != 0) {
            byte[] paddedBlock = Arrays.copyOf(block, BLOCK_SIZE);
            for (int i = block.length; i < BLOCK_SIZE; i++) {
                paddedBlock[i] = 0x00;
            }
            block = paddedBlock;
        }
        return block;
    }

    public void close() throws IOException {
        srcFileStream.close();
    }

    public String getSrcFileName() {
        return this.srcFileName;
    }

    public String getSrcFolder() {
        return this.srcFolder;
    }
}
