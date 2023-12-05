package services;

public interface Scrutiny {
    void initVoteCount (List<VotingOption> validParties);
    void scrutinize (VotingOption vopt);
    int getVotesFor (VotingOption vopt);
    int getTotal ();
    int getNulls ();
    int getBlanks ();
    void getScrutinyResults ();
}
