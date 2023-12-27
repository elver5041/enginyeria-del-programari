package services;

import data.VotingOption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrutinyImpl implements Scrutiny{
    private final HashMap<VotingOption,Integer> DB;
    private int total = 0;
    public ScrutinyImpl(HashMap<VotingOption,Integer> DB){
        if(DB==null) throw new NullPointerException("db in scrutiny is null");
        this.DB = DB;
    }

    @Override
    public void initVoteCount(List<VotingOption> validParties) {
        for (VotingOption option : validParties) {
            DB.put(option, 0);
        }
        DB.put(new VotingOption(""), 0);
        DB.put(null, 0);
    }

    @Override
    public void scrutinize(VotingOption vopt) {
        int votes = DB.get(vopt);
        votes++;
        total++;
        DB.replace(vopt,votes);
    }

    @Override
    public int getVotesFor(VotingOption vopt) {
        return DB.get(vopt);
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public int getNulls() {
        return DB.get(null);
    }

    @Override
    public int getBlanks() {
        return DB.get(new VotingOption(""));
    }

    @Override
    public void getScrutinyResults() {
        StringBuilder str = new StringBuilder("Scrutiny Results {\n");
        for (Map.Entry<VotingOption,Integer> entry : DB.entrySet()){
            if (entry.getKey()==null){
                str.append("\tnull = ").append(entry.getValue()).append("\n");
            } else if (entry.getKey().equals(new VotingOption(""))){
                str.append("\tblanc = ").append(entry.getValue()).append("\n");
            } else {
                str.append("\t").append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
            }
        }
        str.append("}");
        System.out.println(str);
    }
}
