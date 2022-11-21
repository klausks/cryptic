package mode;

import crypto.Decrypter;
import crypto.Encrypter;
import crypto.IV;
import crypto.Operations;
import fs.BlockReader;
import fs.BlockWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class CBC {
    IV initializationVec;
    Encrypter encrypter;
    Decrypter decrypter;

    public CBC(String userKey) {
        initializationVec = new IV();
        encrypter = new Encrypter(userKey);
        decrypter = new Decrypter(userKey);
    }

    public void encryptFile() throws IOException {
        BlockReader fileReader = new BlockReader("C:\\Users\\klaus\\OneDrive\\Documentos\\Unisinos\\Teoria da Informacao\\cryptic\\test.txt");
        BlockWriter fileWriter = new BlockWriter("C:\\Users\\klaus\\OneDrive\\Documentos\\Unisinos\\Teoria da Informacao\\cryptic\\test.cryptic");
        byte[] c = initializationVec.getVector();
        byte[] block = fileReader.readBlock();
        do {
            block = Operations.xor(block, c);
            block = encrypter.encrypt(block);
            fileWriter.write(block);
            c = block;
            block = fileReader.readBlock();
        } while (block.length > 0);

        fileReader.close();
        fileWriter.close();
    }

    public void decryptFile() throws IOException {
        BlockReader fileReader = new BlockReader("C:\\Users\\klaus\\OneDrive\\Documentos\\Unisinos\\Teoria da Informacao\\cryptic\\test.cryptic");
        BlockWriter fileWriter = new BlockWriter("C:\\Users\\klaus\\OneDrive\\Documentos\\Unisinos\\Teoria da Informacao\\cryptic\\test2.txt");
        byte[] c = initializationVec.getVector();
        byte[] block = fileReader.readBlock();
        do {
            byte[] previousBlock = Arrays.copyOf(block, block.length);
            block = decrypter.decrypt(block);
            block = Operations.xor(block, c);
            fileWriter.write(block);
            block = fileReader.readBlock();
            c = previousBlock;
        } while (block.length > 0);

        fileReader.close();
        fileWriter.close();
    }
}
