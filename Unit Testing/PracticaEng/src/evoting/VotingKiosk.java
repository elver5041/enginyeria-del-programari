package evoting;

import data.Nif;
import data.Password;
import data.VotingOption;
import evoting.biometricdataperipheral.BiometricData;
import exceptions.*;
import jdk.jshell.spi.ExecutionControl;

import java.net.ConnectException;

public class VotingKiosk {
    //TODO The class members
    // The constructor/s
    // Input events
    public void initVoting () {throw new ExecutionControl.NotImplementedException("");}
    public void setDocument (char opt) {throw new ExecutionControl.NotImplementedException("");}
    public void enterAccount (String login, Password pssw) throws InvalidAccountException {throw new ExecutionControl.NotImplementedException("");}
    public void confirmIdentif (char conf) throws InvalidDNIDocumException {throw new ExecutionControl.NotImplementedException("");}
    public void enterNif (Nif nif) throws NotEnabledException, ConnectException {throw new ExecutionControl.NotImplementedException("");}
    public void initOptionsNavigation () {throw new ExecutionControl.NotImplementedException("");}
    public void consultVotingOption (VotingOption vopt) {throw new ExecutionControl.NotImplementedException("");}
    public void vote () {throw new ExecutionControl.NotImplementedException("");}

    public void confirmVotingOption (char conf) throws ConnectException {throw new ExecutionControl.NotImplementedException("");}
    // Internal operation, not required
    private void finalizeSession () {throw new ExecutionControl.NotImplementedException("");}
    // TODO Setter methods for injecting dependences and additional methods


    private void verifyBiometricData(BiometricData humanBD, BiometricData passpBD) throws BiometricVerificationFailedException {throw new ExecutionControl.NotImplementedException("");}
    private void removeBiometricData(){throw new ExecutionControl.NotImplementedException("");}

    public void grantExplicitConsent (char cons) {throw new ExecutionControl.NotImplementedException("");}
    public void readPassport() throws NotValidPassportException, PassportBiometricReadingException {throw new ExecutionControl.NotImplementedException("");}
    public void readFaceBiometrics() throws HumanBiometricScanningException {throw new ExecutionControl.NotImplementedException("");}
    public void readFingerprintBiometrics() throws NotEnabledException, HumanBiometricScanningException, BiometricVerificationFailedException, ConnectException {throw new ExecutionControl.NotImplementedException("");}
}

