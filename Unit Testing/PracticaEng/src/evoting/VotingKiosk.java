package evoting;

import data.Nif;
import data.Passport;
import data.Password;
import data.VotingOption;
import evoting.biometricdataperipheral.BiometricData;
import evoting.biometricdataperipheral.HumanBiometricScanner;
import evoting.biometricdataperipheral.PassportBiometricScanner;
import evoting.biometricdataperipheral.SingleBiometricData;
import exceptions.*;
import jdk.jshell.spi.ExecutionControl;
import services.ElectoralOrganism;
import services.LocalService;
import services.Scrutiny;

import java.net.ConnectException;
import java.util.HashMap;

public class VotingKiosk {
    //TODO The class members
    private char doctype;
    private boolean voting;
    private boolean verified;
    private boolean isFingerScannerEnabled;
    private BiometricData bioData;
    private SingleBiometricData tempBio;
    private Passport passport;
    private Nif nif;
    private VotingOption votingOption;
    private VotingOption vO;
    private Scrutiny scrutiny;
    private LocalService localService;
    private ElectoralOrganism electoralOrganism;
    private PassportBiometricScanner passportBiometricScanner;
    private HumanBiometricScanner humanBiometricScanner;
    // The constructor/s
    public VotingKiosk(){
        voting = false;
        isFingerScannerEnabled = false;
    }
    // Input events
    public void initVoting () {
        voting = true;
        System.out.println("menu de document");
    }
    public void setDocument (char opt) {
        if (opt == 'd') {
            doctype = 'd';
            System.out.println("demani ajut de suport");
        } else if (opt == 'p') {
            doctype = 'p';
            System.out.println("consenteix a l'us de les dades biometriques?");
        }
    }
    public void enterAccount (String login, Password pssw) throws InvalidAccountException {
        localService.verifyAccount(login, pssw);
        System.out.println("admin autentificat correctament");
    }
    public void confirmIdentif (char conf) throws InvalidDNIDocumException {
        if (conf == 'n') {
            throw new InvalidDNIDocumException("bad identification");
        } else if (conf == 'y' || conf == 's') {
            System.out.println("introdueixi el nif");
        }
    }
    public void enterNif (Nif nif) throws NotEnabledException, ConnectException, ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(doctype!='d')
            throw new ProceduralException("nif no triat");
        electoralOrganism.canVote(nif);
        this.nif = nif;
        System.out.println("nif correcte");
    }
    public void initOptionsNavigation () {
        System.out.println("navegant");
    }
    public void consultVotingOption (VotingOption vopt) throws ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(votingOption == null)
            throw new ProceduralException("cap opcio seleccionada");
        votingOption = vopt;
        System.out.println("mostrant: "+vopt.getParty());
    }

    public void vote () throws ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(votingOption == null)
            throw new ProceduralException("cap opcio seleccionada");
        vO = new VotingOption(votingOption.getParty());
        System.out.println("segur que vol votar a "+vO.getParty());
    }

    public void confirmVotingOption (char conf) throws ConnectException, ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(votingOption == null)
            throw new ProceduralException("cap opcio seleccionada");

        if (conf == 'y' || conf == 's') {
            electoralOrganism.disableVoter(nif);
            scrutiny.scrutinize(vO);
            System.out.println("votat correctament");
        } else if(conf == 'n'){
            vO = null;
            System.out.println("trii un altre cop :(");
        }
    }

    // Internal operation, not required
    private void finalizeSession () {
        nif = null;
        votingOption = null;
        vO = null;
        voting = false;
        System.out.println("torni un altre cop");
    }

    private void setScrutiny(Scrutiny scrot){
        scrutiny = scrot;
    }
    private void setLocalService(LocalService loco){
        localService = loco;
    }
    private void setElectoralOrganism(ElectoralOrganism erect){
        electoralOrganism = erect;
    }
    private void setPassportBiometricScanner(PassportBiometricScanner pbs){
        passportBiometricScanner = pbs;
    }
    private void setHumanBiometricScanner(HumanBiometricScanner hbs){
        humanBiometricScanner = hbs;
    }
    private void enableFingerScanner(){
        isFingerScannerEnabled=true;
    }
    private void disableFingerScanner(){
        isFingerScannerEnabled=false;
    }
    private void recount(){
        voting = false;
        scrutiny.getScrutinyResults();
    }

    //auto verification
    private void verifyBiometricData(BiometricData humanBD, BiometricData passpBD) throws BiometricVerificationFailedException {
        if(!humanBD.equals(passpBD))
            throw new BiometricVerificationFailedException("dades no coincideixen");
        System.out.println("perfecte! ara a votar");
    }
    private void removeBiometricData(){
        bioData = null;
        passport = null;
    }

    public void grantExplicitConsent (char cons) throws ProceduralException {
        if (cons == 'n') {
            throw new ProceduralException("pos no votes");
        } else if (cons == 'y' || cons == 's') {
            System.out.println("introdueix passaport");
        }
    }
    public void readPassport() throws NotValidPassportException, PassportBiometricReadingException {
        passportBiometricScanner.validatePassport();
        passport = new Passport(
                passportBiometricScanner.getNifWithOCR(),
                passportBiometricScanner.getPassportBiometricData());
        System.out.println("lectura del passaport correcta, llegirem la cara ara");
    }
    public void readFaceBiometrics() throws HumanBiometricScanningException {
        tempBio = humanBiometricScanner.scanFaceBiometrics();
        System.out.println("lectura de la cara correcta, llegirem el dit ara");
    }
    public void readFingerprintBiometrics() throws NotEnabledException, HumanBiometricScanningException {
        if (!isFingerScannerEnabled)
            throw new NotEnabledException("fingerprint reader not enabled");
        bioData = new BiometricData(tempBio, humanBiometricScanner.scanFingerprintBiometrics());
        tempBio = null;
        System.out.println("lectura correcta, comprovant si les dades son correctes");
    }
}
