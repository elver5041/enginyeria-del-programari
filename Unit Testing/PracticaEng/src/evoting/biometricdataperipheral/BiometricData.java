package evoting.biometricdataperipheral;

final public class BiometricData {
    private final SingleBiometricData faceBiometric;
    private final SingleBiometricData fingerBiometric;
    public BiometricData(SingleBiometricData faceBiometric, SingleBiometricData fingerBiometric) {
        this.faceBiometric = faceBiometric;
        this.fingerBiometric = fingerBiometric;
    }
    public SingleBiometricData getFaceBiometric () {
        return faceBiometric;
    }
    public SingleBiometricData getFingerBiometric () {
        return fingerBiometric;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiometricData vO = (BiometricData) o;
        return faceBiometric.equals(vO.faceBiometric) && fingerBiometric.equals(vO.fingerBiometric);
    }

    @Override
    public int hashCode () { return faceBiometric.hashCode() + fingerBiometric.hashCode(); }

    @Override
    public String toString () {
        return "Biometrics {" + "face='" + faceBiometric + '\'' + " finger='" + fingerBiometric + '\'' + '}';
    }
}
