package data;

import evoting.biometricdataperipheral.BiometricData;

public final class Passport {
    private final Nif nif;
    private final BiometricData bioData;
    public Passport (Nif nif, BiometricData bioData) {
        this.nif = nif;
        this.bioData = bioData;
    }
    public Nif getNif () {
        return nif;
    }
    public BiometricData getBioData () {
        return bioData;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport pASSport = (Passport) o;
        return nif.equals(pASSport.nif) && bioData.equals(pASSport.bioData);
    }

    @Override
    public int hashCode () { return nif.hashCode() + bioData.hashCode(); }

    @Override
    public String toString () {
        return "Passport {" + "nif='" + nif + '\'' + "bioData='" + bioData + '\'' + '}';
    }
}
