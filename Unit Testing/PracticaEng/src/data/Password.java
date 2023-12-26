package data;

final public class Password{
    private final String pass;
    public Password (String option) {
        this.pass = option;
    }
    public String getPassword () {
        return pass;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password vO = (Password) o;
        return pass.equals(vO.pass);
    }

    @Override
    public int hashCode () { return pass.hashCode(); }

    @Override
    public String toString () {
        return "Password {" + "pass='" + pass + '\'' + '}';
    }
}
