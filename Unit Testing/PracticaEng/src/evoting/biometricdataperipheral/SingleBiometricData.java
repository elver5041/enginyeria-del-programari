package evoting.biometricdataperipheral;

import java.util.Arrays;

final public class SingleBiometricData {
    private final byte[] data;
    public SingleBiometricData (byte[] option) {
        this.data = option;
    }
    public byte[] getBiometrics () {
        return data;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleBiometricData vO = (SingleBiometricData) o;
        return Arrays.equals(data, vO.data);
    }

    @Override
    public int hashCode () { return Arrays.hashCode(data); }

    @Override
    public String toString () {
        return "SingleBiometric {" + "bio='" + Arrays.toString(data) + '\'' + '}';
    }
}
