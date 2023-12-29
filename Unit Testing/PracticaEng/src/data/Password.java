package data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

final public class Password{
    private byte[] pass;
    public Password (String pass) {
        try {
            this.pass = MessageDigest.getInstance("SHA-256").digest(pass.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {e.printStackTrace();}
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password pASS = (Password) o;
        return Arrays.equals(pass, pASS.pass);
    }

    @Override
    public int hashCode () { return Arrays.hashCode(pass); }

    @Override
    public String toString () {
        return "Password {" + "pass='" + Arrays.toString(pass) + '\'' + '}';
    }
}
