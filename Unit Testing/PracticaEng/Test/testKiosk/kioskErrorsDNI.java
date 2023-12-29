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

public class kioskErrorsDNI {

    public static VotingKiosk votVell;
    public static ScrutinyImpl scrut;
    public static LocalServiceImpl serverLoc;

    public static ElectoralOrganismImpl organism;

    @BeforeEach
    public void reset() throws NotValidNifException {
        votVell = new VotingKiosk();
        votVell.setScrutiny(scrut);
        votVell.setLocalService(serverLoc);
        votVell.setElectoralOrganism(organism);
        organism.setSeed(473473737l);
    }

    @BeforeAll
    public static void init() throws NotValidNifException {
        scrut = new ScrutinyImpl(new HashMap<VotingOption, Integer>());
        serverLoc = new LocalServiceImpl();
        serverLoc.addInfo("Manolo", new Password("socvisiblexd_"));
        HashMap<Nif, Boolean> votants = new HashMap<>();
        votants.put(new Nif("39955425D"), true);
        votants.put(new Nif("39955426X"), true);
        votants.put(new Nif("39955427B"), true);
        organism = new ElectoralOrganismImpl(votants);
        List<VotingOption> partits = new ArrayList<>();
        partits.add(new VotingOption("PNOA"));
        partits.add(new VotingOption("VOX"));
        partits.add(new VotingOption("PPRM"));
        partits.add(new VotingOption("PNF"));
        partits.add(new VotingOption("FE"));
        scrut.initVoteCount(partits);
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
        organism.setSeed(18l);
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
    public void notValidDNI() throws ProceduralException, InvalidDNIDocumException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.confirmIdentif('y');
        assertThrows(NotValidNifException.class, () -> votVell.enterNif(new Nif("39955429L")));
    }

}
