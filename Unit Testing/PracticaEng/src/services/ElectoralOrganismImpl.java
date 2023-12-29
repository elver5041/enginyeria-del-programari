package services;

import data.Nif;
import exceptions.NotEnabledException;

import java.net.ConnectException;
import java.util.*;

public class ElectoralOrganismImpl implements ElectoralOrganism{
    private final Random randGen;
    private final Map<Nif,Boolean> DB;
    public ElectoralOrganismImpl(){
        DB = new HashMap<>();
        randGen = new Random();
    }
    public ElectoralOrganismImpl(HashMap<Nif,Boolean> dnis){
        DB = dnis;
        randGen = new Random();
    }
    public ElectoralOrganismImpl(HashMap<Nif,Boolean> dnis, long seed){
        DB = dnis;
        randGen = new Random(seed);
    }
    void setSeed(long seed){
        randGen.setSeed(seed);
    }
    @Override
    public void canVote(Nif nif) throws NotEnabledException, ConnectException {
        if(randGen.nextInt(100)==0) throw new ConnectException("error de connexió del 1%");
        if(DB.get(nif)==null) throw new NotEnabledException("dni no trobat??");
        if (!DB.get(nif)){
            throw new NotEnabledException("no pot votar perque ja ha votat");
        }
    }

    @Override
    public void disableVoter(Nif nif) throws ConnectException {
        if(randGen.nextInt(100)==0) throw new ConnectException("error de connexió del 1%");
        DB.put(nif, false);
    }
}
