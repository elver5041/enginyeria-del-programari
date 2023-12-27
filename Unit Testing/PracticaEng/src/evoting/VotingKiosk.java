package evoting;

import data.Nif;
import data.Password;
import data.VotingOption;
import evoting.biometricdataperipheral.BiometricData;
import exceptions.*;
import jdk.jshell.spi.ExecutionControl;
import services.ElectoralOrganism;
import services.LocalService;
import services.Scrutiny;

import java.net.ConnectException;

public class VotingKiosk {
    //TODO The class members
    private Nif nif;
    private VotingOption votingOption;
    private Scrutiny scrutiny;
    private LocalService localService;
    private ElectoralOrganism electoralOrganism;
    // The constructor/s
    public VotingKiosk(){}
    // Input events
    private void setScrutiny(Scrutiny scrot){
        scrutiny = scrot;
    }
    private void setLocalService(LocalService loco){
        localService = loco;
    }
    private void setElectoralOrganism(ElectoralOrganism erect){
        electoralOrganism = erect;
    }
    public void initVoting () {
        //TODO
    }
    public void setDocument (char opt) {
        //TODO
        if (opt == 'd') {

        } else if (opt == 'p') {

        } else {

        }
    }
    public void enterAccount (String login, Password pssw) throws InvalidAccountException {
        localService.verifyAccount(login, pssw);
    }
    public void confirmIdentif (char conf) throws InvalidDNIDocumException {
        if (conf == 'n') {
            throw new InvalidDNIDocumException("bad identification");
        } else if (conf == 'y' || conf == 's') {
            //TODO
        }
    }
    public void enterNif (Nif nif) throws NotEnabledException, ConnectException {
        electoralOrganism.canVote(nif);
        this.nif = nif;
    }
    public void initOptionsNavigation () {
        //TODO: "full interficie"
    }
    public void consultVotingOption (VotingOption vopt) {
        votingOption = vopt;
        //TODO: "mostrar opcio"
    }
    public void vote () {
        //TODO:what
    }

    public void confirmVotingOption (char conf) throws ConnectException {
        if (conf == 'y' || conf == 's') {
            scrutiny.scrutinize(votingOption);
            electoralOrganism.disableVoter(nif);
        }
        //TODO: done?
    }

    // Internal operation, not required
    private void finalizeSession () {
        nif = null;
        votingOption = null;
    }
    // TODO Setter methods for injecting dependences and additional methods


    private void verifyBiometricData(BiometricData humanBD, BiometricData passpBD) throws BiometricVerificationFailedException {throw new ExecutionControl.NotImplementedException("");}
    private void removeBiometricData(){throw new ExecutionControl.NotImplementedException("");}

    public void grantExplicitConsent (char cons) {throw new ExecutionControl.NotImplementedException("");}
    public void readPassport() throws NotValidPassportException, PassportBiometricReadingException {throw new ExecutionControl.NotImplementedException("");}
    public void readFaceBiometrics() throws HumanBiometricScanningException {throw new ExecutionControl.NotImplementedException("");}
    public void readFingerprintBiometrics() throws NotEnabledException, HumanBiometricScanningException, BiometricVerificationFailedException, ConnectException {throw new ExecutionControl.NotImplementedException("");}
}

