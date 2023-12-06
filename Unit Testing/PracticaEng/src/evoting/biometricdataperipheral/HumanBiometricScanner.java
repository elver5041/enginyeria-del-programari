package evoting.biometricdataperipheral;

import exceptions.HumanBiometricScanningException;

public interface HumanBiometricScanner {
    SingleBiometricData scanFaceBiometrics ()
            throws HumanBiometricScanningException;
    SingleBiometricData scanFingerprintBiometrics ()
            throws HumanBiometricScanningException;
}
