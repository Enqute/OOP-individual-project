import Utils.Decrypt;
import Utils.Encrypt;
import Utils.Generator;
import Utils.Program;
import Views.MainPage;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.*;

public class Main {
//    public static void main(String[] args) throws Exception {
////        Security.addProvider(new org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider());
//        String plainText = "This message will encrypted using AES";
//
//        AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("AES");
//        paramGen.init(128);
//        AlgorithmParameters params = paramGen.generateParameters();
//
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128);
//        SecretKey secretKey = (SecretKey) Cipher.getInstance("AES/CBC/PKC7Padding");
//
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey, params);
//        byte[] encryptedText = cipher.doFinal(plainText.getBytes());
//        cipher.init(Cipher.DECRYPT_MODE, secretKey, params);
//        byte[] decryptedText = cipher.doFinal(encryptedText);
//
//        System.out.println("Palin text: " + plainText);
//        System.out.println("Encrypted text: " + new String(encryptedText));
//        System.out.println("Decrypted text: " + new String(decryptedText));
//

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, URISyntaxException {

        SecretKey key = Generator.generateKey(128);
        String algorithm = "AES/CBC/PKCS5Padding";
//        AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance(algorithm.split("/")[0]);

        /*
        IvParameterSpec ivParameterSpec = Generator.generateIv();
        File inputFile = new File("res/hello.txt");
        File encryptedFile = new File("res/hello.encrypted");
        File decryptedFile = new File("res/hello.decrypted");
        Encrypt.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
        Decrypt.decryptFile(
                algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
        */
//        assertThat(inputFile).hasSameTextualContentAs(decryptedFile);

        Program page = new MainPage();
    }

}
