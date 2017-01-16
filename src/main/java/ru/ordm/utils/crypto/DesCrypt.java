package ru.ordm.utils.crypto;


import org.springframework.security.crypto.codec.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DesCrypt {

    private static final byte[] keyBytes =
            {
                    (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef
            };

    private static SecretKey key = null;

    private static Cipher des_cipher = null;

    public static String hashpw(String pswd) throws Exception {

        byte[] input = (pswd).toUpperCase().getBytes("utf-16be");;

        input = Arrays.copyOf(input, ((input.length + 7) / 8) * 8);

        return generate(input,null);
    }

    private static String generate(byte[] input, byte[] salt) throws Exception {

        if (des_cipher == null) {
            try {
                des_cipher = Cipher.getInstance("DES/CBC/NoPadding");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
               // System.exit(0);
                return null;
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
                // System.exit(0);
                return null;
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


    public static boolean checkpw(String plaintext, String hashed) throws Exception {
        return equalsNoEarlyReturn(hashed,hashpw(plaintext));
    }

    static boolean equalsNoEarlyReturn(String a, String b) {
        char[] caa = a.toCharArray();
        char[] cab = b.toCharArray();

        if (caa.length != cab.length) {
            return false;
        }

        byte ret = 0;
        for (int i = 0; i < caa.length; i++) {
            ret |= caa[i] ^ cab[i];
        }
        return ret == 0;
    }
}
