package evoting.biometricdataperipheral;

public interface HumanBiometricScanner {
    SingleBiometricData scanFaceBiometrics ()
            throws HumanBiometricScanningException;
    SingleBiometricData scanFingerprintBiometrics ()
            throws HumanBiometricScanningException;
}
