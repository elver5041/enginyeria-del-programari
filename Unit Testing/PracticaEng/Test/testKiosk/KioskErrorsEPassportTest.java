package testKiosk;
import data.Nif;
import data.Passport;
import data.Password;
import data.VotingOption;
import evoting.VotingKiosk;
import evoting.biometricdataperipheral.BiometricData;
import evoting.biometricdataperipheral.HumanBiometricImpl;
import evoting.biometricdataperipheral.PassportBiometricImpl;
import evoting.biometricdataperipheral.SingleBiometricData;
import exceptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganismImpl;
import services.LocalServiceImpl;
import services.ScrutinyImpl;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class KioskErrorsEPassportTest {

    public static VotingKiosk votVell;
    public static ScrutinyImpl scrut;
    public static LocalServiceImpl serverLoc;
    public static HumanBiometricImpl HBI;
    public static PassportBiometricImpl PBI;
    public static ElectoralOrganismImpl organism;

    @BeforeEach
    public void reset() throws NotValidNifException {
        votVell = new VotingKiosk();
        votVell.setScrutiny(scrut);
        votVell.setLocalService(serverLoc);
        HashMap<Nif, Boolean> votants = new HashMap<>();
        votants.put(new Nif("39955425D"), true);
        organism = new ElectoralOrganismImpl(votants, 473473737L);
        votVell.setElectoralOrganism(organism);
        HBI = new HumanBiometricImpl(473473737L);
        PBI = new PassportBiometricImpl(473473737L);
        votVell.setHumanBiometricScanner(HBI);
        votVell.setPassportBiometricScanner(PBI);
    }

    @BeforeAll
    public static void init() throws NotValidNifException {
        scrut = new ScrutinyImpl(new HashMap<>());
        serverLoc = new LocalServiceImpl();
        serverLoc.addInfo("Manolo", new Password("socvisiblexd_"));
        List<VotingOption> partits = new ArrayList<>();
        partits.add(new VotingOption("PNOA"));
        partits.add(new VotingOption("VOX"));
        partits.add(new VotingOption("PPRM"));
        partits.add(new VotingOption("PNF"));
        partits.add(new VotingOption("FE"));
        scrut.initVoteCount(partits);
    }

    @Test
    public void TryNifOperation () throws ProceduralException {
        votVell.initVoting();
        votVell.setDocument('p');
        assertThrows(ProceduralException.class, ()-> votVell.enterNif(new Nif("39955425D")));
    }

    @Test
    public void ErrorValidatingPassport() throws ProceduralException, NotValidNifException {
        votVell.initVoting();
        votVell.setDocument('p');
        votVell.grantExplicitConsent('y');
        PBI.setSeed(18L);
        PBI.inputPassport(new Passport(new Nif("39955425D"), new BiometricData(new SingleBiometricData(new byte[]{0}), new SingleBiometricData(new byte[]{1}))));
        assertThrows(NotValidPassportException.class, () -> votVell.readPassport());
    }

    @Test
    public void ErrorReadingPassport() throws ProceduralException, NotValidNifException {
        votVell.initVoting();
        votVell.setDocument('p');
        votVell.grantExplicitConsent('y');
        PBI.setSeed(13L); //El segon cop aquesta semilla falla
        PBI.inputPassport(new Passport(new Nif("39955425D"), new BiometricData(new SingleBiometricData(new byte[]{0}), new SingleBiometricData(new byte[]{1}))));
        assertThrows(PassportBiometricReadingException.class, () -> votVell.readPassport());
    }

    @Test
    public void ErrorLectureFaceHBiometrics() throws ProceduralException, NotValidNifException, PassportBiometricReadingException, NotValidPassportException {
        votVell.initVoting();
        votVell.setDocument('p');
        votVell.grantExplicitConsent('y');
        PBI.inputPassport(new Passport(new Nif("39955425D"), new BiometricData(new SingleBiometricData(new byte[]{0}), new SingleBiometricData(new byte[]{1}))));
        votVell.readPassport();
        HBI.setReturnData(new byte[]{0});
        HBI.setSeed(18L);
        assertThrows(HumanBiometricScanningException.class, () -> votVell.readFaceBiometrics());
    }

    @Test
    public void ErrorLectureFingerHBiometrics() throws ProceduralException, NotValidNifException, PassportBiometricReadingException, NotValidPassportException, BiometricVerificationFailedException, HumanBiometricScanningException, NotEnabledException, ConnectException {
        votVell.initVoting();
        votVell.setDocument('p');
        votVell.grantExplicitConsent('y');
        PBI.inputPassport(new Passport(new Nif("39955425D"), new BiometricData(new SingleBiometricData(new byte[]{0}), new SingleBiometricData(new byte[]{1}))));
        votVell.readPassport();
        HBI.setReturnData(new byte[]{0});
        votVell.readFaceBiometrics();
        votVell.enableFingerScanner();
        HBI.setReturnData(new byte[]{1});
        HBI.setSeed(18L);
        assertThrows(HumanBiometricScanningException.class, () -> votVell.readFingerprintBiometrics());
    }

    @Test
    public void FaceNotCoincident() throws ProceduralException, NotValidNifException, PassportBiometricReadingException, NotValidPassportException {
        votVell.initVoting();
        votVell.setDocument('p');
        votVell.grantExplicitConsent('y');
        PBI.inputPassport(new Passport(new Nif("39955425D"), new BiometricData(new SingleBiometricData(new byte[]{0}), new SingleBiometricData(new byte[]{1}))));
        votVell.readPassport();
        HBI.setReturnData(new byte[]{15});
        assertThrows(BiometricVerificationFailedException.class, () -> votVell.readFaceBiometrics());
    }

    @Test
    public void FingerNotCoincident() throws ProceduralException, NotValidNifException, PassportBiometricReadingException, NotValidPassportException, BiometricVerificationFailedException, HumanBiometricScanningException, NotEnabledException, ConnectException {
        votVell.initVoting();
        votVell.setDocument('p');
        votVell.grantExplicitConsent('y');
        PBI.inputPassport(new Passport(new Nif("39955425D"), new BiometricData(new SingleBiometricData(new byte[]{0}), new SingleBiometricData(new byte[]{1}))));
        votVell.readPassport();
        HBI.setReturnData(new byte[]{0});
        votVell.readFaceBiometrics();
        votVell.enableFingerScanner();
        HBI.setReturnData(new byte[]{100});
        assertThrows(BiometricVerificationFailedException.class, () -> votVell.readFingerprintBiometrics());
    }

}
