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

import static org.junit.jupiter.api.Assertions.assertThrows;

public class KioskErrorsDNITest {

    public static VotingKiosk votVell;
    public static ScrutinyImpl scrut;
    public static LocalServiceImpl serverLoc;

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
    public void OperateWithoutStart(){
        assertThrows(ProceduralException.class, () -> votVell.setDocument('z'));
    }

    @Test
    public void document0Sentit() {
        votVell.initVoting();
        assertThrows(ProceduralException.class, () -> votVell.setDocument('z'));
    }

    @Test
    public void accountNotExistent() throws ProceduralException {
        votVell.initVoting();
        votVell.setDocument('d');
        assertThrows(InvalidAccountException.class, () -> votVell.enterAccount("Manolo", new Password("contrassenya")));
        assertThrows(InvalidAccountException.class, () -> votVell.enterAccount("SeñorBolainas", new Password("socvisiblexd_")));
    }

    @Test
    public void ConnectionErr() throws ProceduralException, InvalidDNIDocumException {
        organism.setSeed(18L);
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.confirmIdentif('y');
        assertThrows(ConnectException.class, () -> votVell.enterNif(new Nif("39955425D")));
    }

    @Test
    public void notExistentDNI() throws ProceduralException, InvalidDNIDocumException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.confirmIdentif('y');
        assertThrows(NotEnabledException.class, () -> votVell.enterNif(new Nif("39955429J")));
    }

    @Test
    public void PersonalDeniesDNI() throws ProceduralException {
        votVell.initVoting();
        votVell.setDocument('d');
        assertThrows(InvalidDNIDocumException.class, () -> votVell.confirmIdentif('n'));
    }

    @Test
    public void notValidDNI() throws ProceduralException, InvalidDNIDocumException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.confirmIdentif('y');
        assertThrows(NotValidNifException.class, () -> votVell.enterNif(new Nif("39955429L")));
    }
    @Test
    public void noVotingOption() throws ProceduralException, InvalidDNIDocumException, InvalidAccountException, NotValidNifException, NotEnabledException, ConnectException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.enterAccount("Manolo", new Password("socvisiblexd_"));
        votVell.confirmIdentif('y');
        votVell.enterNif(new Nif("39955425D"));
        votVell.initOptionsNavigation();
        assertThrows(ProceduralException.class, () -> votVell.consultVotingOption(null));
    }

    @Test
    public void nonExistentVotingOption() throws ProceduralException, InvalidDNIDocumException, InvalidAccountException, NotValidNifException, NotEnabledException, ConnectException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.enterAccount("Manolo", new Password("socvisiblexd_"));
        votVell.confirmIdentif('y');
        votVell.enterNif(new Nif("39955425D"));
        votVell.initOptionsNavigation();
        assertThrows(ProceduralException.class, () -> votVell.consultVotingOption(new VotingOption("macarronsdelamama")));
    }

    @Test
    public void votingWithoutConsulting() throws ProceduralException, InvalidDNIDocumException, InvalidAccountException, NotValidNifException, NotEnabledException, ConnectException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.enterAccount("Manolo", new Password("socvisiblexd_"));
        votVell.confirmIdentif('y');
        votVell.enterNif(new Nif("39955425D"));
        votVell.initOptionsNavigation();
        assertThrows(ProceduralException.class, () -> votVell.vote());
    }

    @Test
    public void confirmWithoutVote() throws ProceduralException, InvalidDNIDocumException, InvalidAccountException, NotValidNifException, NotEnabledException, ConnectException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.enterAccount("Manolo", new Password("socvisiblexd_"));
        votVell.confirmIdentif('y');
        votVell.enterNif(new Nif("39955425D"));
        votVell.initOptionsNavigation();
        votVell.consultVotingOption(new VotingOption("VOX"));
        assertThrows(ProceduralException.class, () -> votVell.confirmVotingOption('y'));
    }

    @Test
    public void tryPassportOp() throws ProceduralException {
        votVell.initVoting();
        votVell.setDocument('d');
        assertThrows(ProceduralException.class, () -> votVell.grantExplicitConsent('y'));
    }

    @Test
    public void SPersonVoting2Times() throws ProceduralException, InvalidAccountException, InvalidDNIDocumException, NotValidNifException, NotEnabledException, ConnectException {
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

        votVell.initVoting();
        votVell.setDocument('d');
        votVell.enterAccount("Manolo", new Password("socvisiblexd_"));
        votVell.confirmIdentif('y');
        assertThrows(NotEnabledException.class, () -> votVell.enterNif(new Nif("39955425D")));
    }
}
