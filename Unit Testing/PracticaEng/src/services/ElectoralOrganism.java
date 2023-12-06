package services;

import data.Nif;
import exceptions.NotEnabledException;

import java.net.ConnectException;

public interface ElectoralOrganism {
    void canVote(Nif nif) throws NotEnabledException, ConnectException;
    void disableVoter(Nif nif) throws ConnectException;
}
