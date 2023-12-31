package data;

import exceptions.NotValidNifException;

final public class Nif{
    private static final char[] lookup = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
    private final String nif;

    public Nif (String nif) throws NotValidNifException {
        if (nif==null)
            throw new NullPointerException("nif null");
        if (nif.length()!=9)
            throw new NotValidNifException("llargada incorrecta");
        String str = nif.substring(0,8);
        char lletra = nif.charAt(8);
        int num;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e){
            throw new NotValidNifException("8 primers caracters no son nombres");
        }
        if (lookup[num%23]!=Character.toUpperCase(lletra))
            throw new NotValidNifException("nif invalid");
        this.nif = nif;
    }
    public String getNif () {
        return nif;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif nIF = (Nif) o;
        return nif.equals(nIF.nif);
    }

    @Override
    public int hashCode () { return nif.hashCode(); }

    @Override
    public String toString () {
        return "Nif {" + "nif='" + nif + '\'' + '}';
    }
}
