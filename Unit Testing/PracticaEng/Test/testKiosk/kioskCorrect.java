package testKiosk;
import data.Nif;
import data.Password;
import data.VotingOption;
import evoting.VotingKiosk;
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

public class kioskCorrect {

    public static VotingKiosk votVell;
    public static ScrutinyImpl scrut;
    public static LocalServiceImpl serverLoc;

    public static ElectoralOrganismImpl organism;

    @BeforeEach
    public void reset() throws NotValidNifException {
        HashMap<Nif, Boolean> votants = new HashMap<>();
        votants.put(new Nif("39955425D"), true); //pels testos incorrectes -> treure això
        organism = new ElectoralOrganismImpl(votants, 473473737l);
        votVell = new VotingKiosk();
        votVell.setScrutiny(scrut);
        votVell.setLocalService(serverLoc);
        votVell.setElectoralOrganism(organism);
    }

    @BeforeAll
    public static void init() throws NotValidNifException {
        scrut = new ScrutinyImpl(new HashMap<VotingOption, Integer>());
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
    public void registreDNI() throws InvalidAccountException, NotValidNifException, NotEnabledException, ClassCastException, ProceduralException, ConnectException, InvalidDNIDocumException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.enterAccount("Manolo", new Password("socvisiblexd_"));
        votVell.confirmIdentif('y');
        votVell.enterNif(new Nif("39955425D"));
        votVell.initOptionsNavigation();
        votVell.consultVotingOption(new VotingOption("PNOA"));
        votVell.vote();
        votVell.confirmVotingOption('n');
        votVell.consultVotingOption(new VotingOption("VOX"));
        votVell.vote();
        votVell.confirmVotingOption('y');
    }

    @Test
    public void passaportE() throws InvalidAccountException, NotValidNifException, NotEnabledException, ClassCastException, ProceduralException, ConnectException, PassportBiometricReadingException, NotValidPassportException {
        votVell.initVoting();
        votVell.setDocument('p');
        votVell.grantExplicitConsent('y');
        votVell.readPassport();
        //aquí falta

        votVell.initOptionsNavigation();
        votVell.consultVotingOption(new VotingOption("PNOA"));
        votVell.vote();
        votVell.confirmVotingOption('n');
        votVell.consultVotingOption(new VotingOption("VOX"));
        votVell.vote();
        votVell.confirmVotingOption('y');
    }
}
