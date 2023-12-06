package evoting.biometricdataperipheral;

import data.Nif;
import exceptions.NotValidPassportException;
import exceptions.PassportBiometricReadingException;

public interface PassportBiometricScanner {
    void validatePassport() throws NotValidPassportException;
    Nif getNifWithOCR();
    BiometricData getPassportBiometricData() throws PassportBiometricReadingException;
}
