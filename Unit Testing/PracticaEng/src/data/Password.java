package data;

final public class Password{
    private final String pass;
    public Password (String pass) {
        this.pass = pass;
    }
    public String getPassword () {
        return pass;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password pASS = (Password) o;
        return pass.equals(pASS.pass);
    }

    @Override
    public int hashCode () { return pass.hashCode(); }

    @Override
    public String toString () {
        return "Password {" + "pass='" + pass + '\'' + '}';
    }
}
