/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package Controller.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SessionId {

    // TODO: Convert to bean
    public String newSessionId(String unencryptedPassword) {
        String salt = "@#$!%^&*(*)1234567890\";ABCDEFGHIJKLMNOP"; //salt to add complexity to password
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update((unencryptedPassword + salt).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return (new BigInteger(messageDigest.digest())).toString(16);

    }

}
