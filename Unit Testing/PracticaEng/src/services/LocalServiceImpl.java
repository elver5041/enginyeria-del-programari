package services;

import data.Password;
import exceptions.InvalidAccountException;

import java.util.HashMap;

public class LocalServiceImpl implements LocalService{
    HashMap<String,Password> logins;
    public LocalServiceImpl(){
        logins = new HashMap<>();
    }
    public void addInfo(String login, Password pass){
        logins.put(login,pass);
    }
    @Override
    public void verifyAccount(String login, Password pssw) throws InvalidAccountException {
        if (!pssw.equals(logins.get(login))) throw new InvalidAccountException("dades incorrectes");
    }
}
