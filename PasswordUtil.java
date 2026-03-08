package lab3;

import java.security.SecureRandom;
import java.util.Base64;
import java.security.MessageDigest;

public class PasswordUtil {
    public static String generateSalt() {
        byte[] saltBytes = new byte[16];
        SecureRandom saltGenerator = new SecureRandom();
        saltGenerator.nextBytes(saltBytes);

        String salt = Base64.getEncoder().encodeToString(saltBytes);

        return salt;
    }

    public static String hashPassword(String password, String salt) {
        String saltedPassword = salt + password;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(saltedPassword.getBytes());

        String hash = Base64.getEncoder().encodeToString(hashBytes);

        return hash;
    }

    public static String verifyPassword(String password) {
        return true;
    }
}
