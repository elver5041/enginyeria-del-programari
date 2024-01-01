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
import services.ElectoralOrganism;
import services.LocalService;
import services.Scrutiny;

import java.net.ConnectException;

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
        verified = false;
    }
    // Input events
    public void initVoting () {
        voting = true;
        System.out.println("menu de document");
    }

    public void setDocument (char opt) throws ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if (opt == 'd') {
            doctype = 'd';
            System.out.println("demani ajut de suport");
        } else if (opt == 'p') {
            doctype = 'p';
            System.out.println("consenteix a l'us de les dades biometriques?");
        } else {
            throw new ProceduralException("Opció no valida");
        }
    }

    public void enterAccount (String login, Password pssw) throws InvalidAccountException {
        localService.verifyAccount(login, pssw);
        System.out.println("admin autentificat correctament");
    }

    public void confirmIdentif (char conf) throws InvalidDNIDocumException, ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if (conf == 'n') {
            throw new InvalidDNIDocumException("bad identification");
        } else if (conf == 'y' || conf == 's') {
            System.out.println("introdueixi el nif");
            verified = true;
        }
    }

    public void enterNif (Nif nif) throws NotEnabledException, ConnectException, ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(doctype!='d')
            throw new ProceduralException("nif no triat");
        if(!verified)
            throw new ProceduralException("l'assistent ha de verificar el dni primer");
        electoralOrganism.canVote(nif);
        this.nif = nif;
        System.out.println("nif correcte");
    }

    public void initOptionsNavigation () throws ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        System.out.println("navegant");
    }

    public void consultVotingOption (VotingOption vopt) throws ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(vopt == null)
            throw new ProceduralException("cap opcio seleccionada");
        if(!scrutiny.isInPool(vopt))
            throw new ProceduralException("i aquest partit ("+vopt.getParty()+"), d'on ha sortit?");
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
        if(vO == null)
            throw new ProceduralException("cap opcio seleccionada");

        if (conf == 'y' || conf == 's') {
            System.out.println("vot en proces...");
            scrutiny.scrutinize(vO);
            electoralOrganism.disableVoter(nif);
            finalizeSession();
            System.out.println("votat correctament");
        } else if(conf == 'n'){
            vO = null;
            System.out.println("retornant al menu de navegacio");
        }
    }

    // Internal operation, not required
    private void finalizeSession () {
        nif = null;
        votingOption = null;
        vO = null;
        voting = false;
        verified = false;
        System.out.println("Benvingut a la terminal");
    }

    public void setScrutiny(Scrutiny scrot){
        scrutiny = scrot;
    }
    public void setLocalService(LocalService loco){
        localService = loco;
    }
    public void setElectoralOrganism(ElectoralOrganism erect){
        electoralOrganism = erect;
    }
    public void setPassportBiometricScanner(PassportBiometricScanner pbs){
        passportBiometricScanner = pbs;
    }
    public void setHumanBiometricScanner(HumanBiometricScanner hbs){
        humanBiometricScanner = hbs;
    }
    public void enableFingerScanner(){
        isFingerScannerEnabled=true;
    }
    public void disableFingerScanner(){
        isFingerScannerEnabled=false;
    }
    public void recount(){
        scrutiny.getScrutinyResults();
    }

    //auto verification
    private void verifyBiometricData(BiometricData humanBD, BiometricData passpBD) throws BiometricVerificationFailedException, ProceduralException, NotEnabledException, ConnectException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(doctype!='p')
            throw new ProceduralException("passport no triat");
        if(!humanBD.equals(passpBD))
            throw new BiometricVerificationFailedException("dades no coincideixen");
        System.out.println("perfecte! ara a votar");
    }

    private void removeBiometricData(){
        bioData = null;
        passport = null;
    }

    public void grantExplicitConsent (char cons) throws ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(doctype!='p')
            throw new ProceduralException("passport no triat");
        if (cons == 'n')
            throw new ProceduralException("pos no votes");
        if (cons == 'y' || cons == 's')
            System.out.println("introdueix passaport");
    }

    public void readPassport() throws NotValidPassportException, PassportBiometricReadingException, ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(doctype!='p')
            throw new ProceduralException("passport no triat");
        passportBiometricScanner.validatePassport();
        System.out.println("passaport valid");
        BiometricData temp = passportBiometricScanner.getPassportBiometricData();
        System.out.println("dades biometriques obrtingudes");
        passport = new Passport(passportBiometricScanner.getNifWithOCR(), temp);
    }

    public void readFaceBiometrics() throws HumanBiometricScanningException, ProceduralException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(doctype!='p')
            throw new ProceduralException("passport no triat");
        tempBio = humanBiometricScanner.scanFaceBiometrics();
        System.out.println("lectura de la cara correcta, llegirem el dit ara");
    }

    public void readFingerprintBiometrics() throws NotEnabledException, HumanBiometricScanningException, ProceduralException, BiometricVerificationFailedException, ConnectException {
        if(!voting)
            throw new ProceduralException("sessio de vot no iniciada");
        if(doctype!='p')
            throw new ProceduralException("passport no triat");
        if (!isFingerScannerEnabled)
            throw new NotEnabledException("fingerprint reader not enabled");
        bioData = new BiometricData(tempBio, humanBiometricScanner.scanFingerprintBiometrics());
        tempBio = null;
        verifyBiometricData(bioData, passport.getBioData());
        nif = passport.getNif();
        removeBiometricData();
        electoralOrganism.canVote(nif);
        System.out.println("identificació correcta, ja pot votar");
    }
}
