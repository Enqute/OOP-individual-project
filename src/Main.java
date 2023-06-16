import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

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
//    }

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
    }

//    public static IvParameterSpec generateIv() {
//        byte[] iv = new byte[16];
//        new SecureRandom().nextBytes(iv);
//        return new IvParameterSpec(iv);
//    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, URISyntaxException {

        SecretKey key = Main.generateKey(128);
        String algorithm = "AES/CBC/PKCS5Padding";
        AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance(algorithm.split("/")[0]);

//        IvParameterSpec ivParameterSpec = Main.generateIv();
        File inputFile = new File("src/hello.txt");
        File encryptedFile = new File("hello.encrypted");
        File decryptedFile = new File("hello.decrypted");
        Encrypt.encryptFile(algorithm, key, paramGen, inputFile, encryptedFile);
        Decrypt.decryptFile(
                algorithm, key, paramGen, encryptedFile, decryptedFile);
//        assertThat(inputFile).hasSameTextualContentAs(decryptedFile);
    }

}
