package services;

public interface LocalService {
    void verifyAccount (String login, Password pssw)
            throws InvalidAccountException;
}
