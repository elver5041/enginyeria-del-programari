package testData;

import data.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class testPassword {
    Password password1;
    Password password2;
    Password password3;
    Password password4;
    Password password5;

    @BeforeEach
    void createPwd() {
        password1 = new Password("Patata123");
        password2 = new Password("123Patata");
        password3 = new Password("Patata123");
        password4 = new Password("patata123");
        password5 = new Password("");
    }
    @Test
    void testPwdEqualsNull() {
        assertFalse(password1.equals(null));
        assertFalse(password2.equals(null));
        assertFalse(password5.equals(null));
    }
    @Test
    void testPwdEqualsSame() {
        assertTrue(password1.equals(password1));
        assertTrue(password2.equals(password2));
        assertTrue(password5.equals(password5));
    }
    @Test
    void testPwdEqualsDif() {
        assertTrue(password1.equals(password3));
        assertTrue(password3.equals(password1));
        assertFalse(password2.equals(password3));
        assertFalse(password3.equals(password4));
        assertFalse(password2.equals(password5));
    }
    @Test
    void testHashPassport(){
        assertEquals(password1.hashCode(),password3.hashCode());
        assertNotEquals(password3.hashCode(), password4.hashCode());
        assertEquals(password5.hashCode(), password5.hashCode());
    }
    @Test
    void testToStringPassport() throws NoSuchAlgorithmException {
        assertEquals("Password {" + "pass='" + Arrays.toString(MessageDigest.getInstance("SHA-256").digest(("evot"+"Patata123"+"z").getBytes(StandardCharsets.UTF_8))) + '\'' + '}' , password1.toString());
        assertEquals("Password {" + "pass='" + Arrays.toString(MessageDigest.getInstance("SHA-256").digest(("evot"+""+"z").getBytes(StandardCharsets.UTF_8))) + '\'' + '}' , password5.toString());
    }
}
