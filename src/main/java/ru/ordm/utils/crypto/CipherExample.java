package ru.ordm.utils.crypto;

import org.springframework.security.crypto.codec.Hex;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CipherExample {

    private static final byte[] keyBytes =
            {
                    (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef
            };

    public static void main(String[] args) throws Exception {


        System.out.println(getPswd("SKL","SKL"));
     /*  try {


            String key = "0123456789ABCDEF"; // needs to be at least 8 characters for DES

            FileInputStream fis = new FileInputStream("original.txt");
            FileOutputStream fos = new FileOutputStream("encrypted.txt");
            encrypt(key, fis, fos);

            FileInputStream fis2 = new FileInputStream("encrypted.txt");
            FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
            decrypt(key, fis2, fos2);
        } catch (Throwable e) {
            e.printStackTrace();
        }*/
    }

    public static String getPswd(String username,String pswd) throws Exception {
        byte[] input = (username+pswd).toUpperCase().getBytes("utf-16be");;

        for (byte s:input) System.out.print(s);
        System.out.println();
        input = Arrays.copyOf(input, ((input.length + 7) / 8) * 8);

        for (byte s:input) System.out.print(s);
      //  System.out.println(input[2]);
        System.out.println();
        return generate(input,null);
    }


    //static Cipher des_cipher = null;
    static SecretKey  key = null;

    private static Cipher des_cipher = null;

    public static String generate(byte[] input, byte[] salt) throws Exception {




        if (des_cipher == null) {


            try {
                des_cipher = Cipher.getInstance("DES/CBC/NoPadding");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.exit(0);
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
                System.exit(0);
            }
            key = new SecretKeySpec(keyBytes, "DES");
        }



         final IvParameterSpec ips = new IvParameterSpec(new byte[8]);

        des_cipher.init(Cipher.ENCRYPT_MODE, key, ips);
        byte[] encryptedBytes = des_cipher.doFinal(input);

        //5.Encrypt the plaintext string again with DES-CBC, but using the last block of the output
        //of the previous step (ignoring parity bits) as the encryption key.
        // Don't need to set parity - done behind the scenes by the JCE
        Key encryptedKey = new SecretKeySpec(encryptedBytes, encryptedBytes.length - 8, 8, "DES");
        des_cipher.init(Cipher.ENCRYPT_MODE, encryptedKey, ips);
        byte[] encryptedPw = des_cipher.doFinal(input);

        String hex = new String(Hex.encode(encryptedPw));

        hex = hex.substring(hex.length() - 16);

        return hex.toUpperCase();

    }



}