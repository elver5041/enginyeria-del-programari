package evoting.biometricdataperipheral;

import data.Nif;
import data.Passport;
import exceptions.NotValidPassportException;
import exceptions.PassportBiometricReadingException;

import java.util.Random;

public class PassportBiometricImpl implements PassportBiometricScanner{
    private Passport passport;
    private final Random rngGen;
    public PassportBiometricImpl(){
        rngGen = new Random();
    }
    public void inputPassport(Passport pass){
        passport = pass;
    }
    public void setSeed(long seed){
        rngGen.setSeed(seed);
    }
    @Override
    public void validatePassport() throws NotValidPassportException {
        if(rngGen.nextInt(100)==0) throw new NotValidPassportException("xd error 1%");
    }

    @Override
    public Nif getNifWithOCR() {
        return passport.getNif();
    }

    @Override
    public BiometricData getPassportBiometricData() throws PassportBiometricReadingException {
        if(rngGen.nextInt(100)==0) throw new PassportBiometricReadingException("xd error 1%");
        return passport.getBioData();
    }
}
