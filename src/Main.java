import javax.crypto.Cipher;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class Main {

//    public static String sha256(final String base) {
//        try{
//            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            final byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
//            final StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                final String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1)
//                    hexString.append('0');
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch(Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        String text = "Hello there";
////        MessageDigest digest = MessageDigest.getInstance("SHA-256");
////        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
////        System.out.println(Arrays.toString(hash));
//        String hash = sha256(text);
//        System.out.println(hash);
//    }

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Main() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            final String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }

    private static String readFile(String filepath) throws Exception {
        File file = new File(filepath);
        Scanner scan = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        InputStream stream = new FileInputStream(file);
        byte[] b = new byte[(int)file.length()];
        stream.read(b);
//        return byteToHex(b);
//        System.out.println(new String(b, StandardCharsets.UTF_8));
        return new String(b, StandardCharsets.UTF_8);
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        Files.writeString(Path.of(f.getAbsolutePath()), new String(key, StandardCharsets.UTF_8));
    }

    public static PublicKey getPublicKeyFromFile(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKeyFromFile(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public static void main(String[] args) throws Exception {
        Main keyPairGenerator = new Main();
        keyPairGenerator.writeToFile("RSA/publicKey.key", keyPairGenerator.getPublicKey().getEncoded());
        keyPairGenerator.writeToFile("RSA/privateKey.key", keyPairGenerator.getPrivateKey().getEncoded());

//        System.out.println(keyPairGenerator.getPrivateKey().);

        File pf = new File("RSA/publicKey.key");
        File pr = new File("RSA/privateKey.key");

        PrivateKey prk = getPrivateKeyFromFile(readFile(pr.getAbsolutePath()));
        PublicKey puk = getPublicKeyFromFile(readFile(pf.getAbsolutePath()));

        System.out.println(Base64.getEncoder().encodeToString(puk.getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(prk.getEncoded()));
    }



}