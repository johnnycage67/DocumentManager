package ru.ordm.utils.crypto;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DesEncoder implements PasswordEncoder {

    private final Log logger = LogFactory.getLog(getClass());

    public DesEncoder() {

    }

    @Override
    //Username+password
    public String encode(CharSequence rawPassword) {
        try {
            return DesCrypt.hashpw(rawPassword.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            logger.warn("Empty encoded password");
            return false;
        }
       /*
        if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            logger.warn("Encoded password does not look like BCrypt");
            return false;
        }

        */
        try {
            return DesCrypt.checkpw(rawPassword.toString(),encodedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
