package testKiosk;
import data.Nif;
import data.Password;
import data.VotingOption;
import evoting.VotingKiosk;
import exceptions.InvalidAccountException;
import exceptions.NotEnabledException;
import exceptions.NotValidNifException;
import exceptions.ProceduralException;
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

public class testKiosk {

    public static VotingKiosk votVell;
    public static ScrutinyImpl scrut;
    public static LocalServiceImpl serverLoc;

    public static ElectoralOrganismImpl organism;

    @BeforeAll
    public static void init() throws NotValidNifException {
        scrut = new ScrutinyImpl(new HashMap<VotingOption, Integer>());
        serverLoc = new LocalServiceImpl();
        serverLoc.addInfo("Manolo", new Password("socvisiblexd_"));
        HashMap<Nif, Boolean> votants = new HashMap<>();
        votants.put(new Nif("39955425D"), true);
        organism = new ElectoralOrganismImpl(votants, 473473737l);

        List<VotingOption> partits = new ArrayList<>();
        partits.add(new VotingOption("PNOA"));
        partits.add(new VotingOption("VOX"));
        partits.add(new VotingOption("PPRM"));
        partits.add(new VotingOption("PNF"));
        partits.add(new VotingOption("FE"));

        scrut.initVoteCount(partits);
        votVell = new VotingKiosk();
        votVell.setScrutiny(scrut);
        votVell.setLocalService(serverLoc);
        votVell.setElectoralOrganism(organism);
    }

    @Test
    public void registreDNI() throws InvalidAccountException, NotValidNifException, NotEnabledException, ClassCastException, ProceduralException, ConnectException {
        votVell.initVoting();
        votVell.setDocument('d');
        votVell.enterAccount("Manolo", new Password("socvisiblexd_"));
        votVell.enterNif(new Nif("39955425D"));
    }

}
