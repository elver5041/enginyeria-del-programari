package evoting.biometricdataperipheral;

import exceptions.HumanBiometricScanningException;

import java.util.Random;

public class HumanBiometricImpl implements HumanBiometricScanner{
    private byte[] data;
    private final Random rngGen;
    public HumanBiometricImpl(){
        rngGen = new Random();
    }
    public HumanBiometricImpl(long seed){
        rngGen = new Random(seed);
    }
    public void setReturnData(byte[] data){
        this.data = data;
    }
    
    @Override
    public SingleBiometricData scanFaceBiometrics() throws HumanBiometricScanningException {
        if(rngGen.nextInt(100)==0) throw new HumanBiometricScanningException("xd error 1%");
        return new SingleBiometricData(data);
    }

    @Override
    public SingleBiometricData scanFingerprintBiometrics() throws HumanBiometricScanningException {
        if(rngGen.nextInt(100)==0) throw new HumanBiometricScanningException("xd error 1%");
        return new SingleBiometricData(data);
    }
}
