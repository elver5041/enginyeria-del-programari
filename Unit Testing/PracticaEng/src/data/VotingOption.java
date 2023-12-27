package data;

import java.util.Objects;

final public class VotingOption {
    private final String party;
    public VotingOption (String option) {
        this.party = option;
    }
    public String getParty () {
        return party;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingOption vO = (VotingOption) o;
        return Objects.equals(party, vO.party);
    }

    @Override
    public int hashCode () { return party.hashCode(); }

    @Override
    public String toString () {
        if (party == null)
            return "Vote option {" + "party='null" + '\'' + '}';
        if (party.equals(""))
            return "Vote option {" + "party='empty" + '\'' + '}';
        return "Vote option {" + "party='" + party + '\'' + '}';
    }
}
