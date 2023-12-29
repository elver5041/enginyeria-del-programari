package testData;

import data.Nif;
import data.Passport;
import evoting.biometricdataperipheral.BiometricData;
import evoting.biometricdataperipheral.SingleBiometricData;
import exceptions.NotValidNifException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testPassport {
    Passport passport1;
    Passport passport2;
    Passport passport3;
    Nif nif1;
    Nif nif2;
    BiometricData biodata1;
    BiometricData bioData2;

    @BeforeEach
    void passportCreation() throws NotValidNifException {
        nif1 = new Nif("12345678Z");
        nif2 = new Nif("65899632D");

        biodata1 = new BiometricData(new SingleBiometricData(new byte[]{0,1,0,1,0,1,0,1,0,1,0,1,0}), new SingleBiometricData(new byte[]{1,0,1,0,1,0,1,0,1,0,1,0}));
        bioData2 = new BiometricData(new SingleBiometricData(new byte[]{0,1,0,1,0,1,0,1,0,1,0,1}),  new SingleBiometricData(new byte[]{1,0,1,0,1,0,1,0,1,0,1}));

        passport1 = new Passport(nif1, biodata1);
        passport2 = new Passport(nif2, bioData2);
        passport3 = new Passport(nif1, biodata1);
    }
    @Test
    void testGetNif() {
        assertEquals(passport1.getNif(), nif1);
        assertEquals(passport2.getNif(), nif2);
    }
    @Test
    void testGetBioData() {
        assertEquals(passport1.getBioData(), biodata1);
        assertEquals(passport2.getBioData(), bioData2);
    }
    @Test
    void testEqualsSame() {
        assertTrue(passport1.equals(passport1));
        assertTrue(passport2.equals(passport2));
    }
    @Test
    void testEqualsNull(){
        assertFalse(passport1.equals(null));
        assertFalse(passport2.equals(null));
    }
    @Test
    void testEqualsPassport(){
        assertTrue(passport1.equals(passport3));
        assertTrue(passport3.equals(passport1));
    }
    @Test
    void testNotEqualsPassport(){
        assertFalse(passport1.equals(passport2));
        assertFalse(passport2.equals(passport3));
    }
    @Test
    void testHashPassport(){
        assertEquals(passport1.hashCode(),passport3.hashCode());
        assertNotEquals(passport2.hashCode(), passport3.hashCode());
    }
    @Test
    void testToStringPassport(){
        assertEquals(passport1.toString(), "Passport {" + "nif='" + nif1 + '\'' + "bioData='" + biodata1 + '\'' + '}');
        assertEquals(passport2.toString(), "Passport {" + "nif='" + nif2 + '\'' + "bioData='" + bioData2 + '\'' + '}');
    }
}
