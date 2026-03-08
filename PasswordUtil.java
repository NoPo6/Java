package lab3;

import lab3.User;
import lab3.UserStorage;
import java.security.SecureRandom;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    public static String generateSalt() {
        byte[] saltBytes = new byte[16];
        SecureRandom saltGenerator = new SecureRandom();
        saltGenerator.nextBytes(saltBytes);

        String salt = Base64.getEncoder().encodeToString(saltBytes);

        return salt;
    }

    public static String hashPassword(String password, String salt){
        try {
            String saltedPassword = salt + password;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(saltedPassword.getBytes());

            String hash = Base64.getEncoder().encodeToString(hashBytes);

            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка хеширования", e);
        }
    }

    public static boolean verifyPassword(String login, String password) {
        User user = UserStorage.users.get(login);
        String salt = user.getSalt();
        String cur_hash = hashPassword(password, salt);
        String real_hash = user.getHash();
        return real_hash.equals(cur_hash);
    }
}
