import mode.CBC;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Cryptic {
    public static void main(String[] args) throws IOException {
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira a operação desejada:");
        System.out.println("1. Encriptar");
        System.out.println("2. Desencriptar");
        int op = sc.nextInt();
        sc.nextLine();

        System.out.println("Insira o filepath do arquivo fonte:");
        String srcFileName = sc.nextLine();
        System.out.println("Insira o nome do arquivo destino:");
        String tgtFileName = sc.nextLine();

         */
        CBC cbcOperation = new CBC("ABCD");
        cbcOperation.encryptFile();
        cbcOperation.decryptFile();
    }
}
