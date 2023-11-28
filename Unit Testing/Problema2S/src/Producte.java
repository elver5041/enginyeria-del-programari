import java.util.List;

public class Producte {
    private String Nom;
    private preuTreatment preu;

    public Producte (String Nom){
        this.Nom = Nom;
    }

    public String getNom(){return this.Nom;}

    /**
    Si en un futur volem utilitzar el preu cridarem als mètodes
     de preuTreatment
    */
}



class ivaPreu {
    preuTreatment preu;
    public double calcularIva() {
        return preu.getPreu() * 1.21;
    }
    /**
     guardarIva()...
     ...
     */
}

class preuTreatment {

    private double Preu;
    public preuTreatment(double Preu) {this.Preu = Preu;}

    public double getPreu(){return this.Preu;}
    public void setPreu(double Preu) {this.Preu = Preu;}

}

