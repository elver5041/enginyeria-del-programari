import java.math.BigDecimal;

public class Pair {
    BigDecimal price;
    int units;
    public Pair(BigDecimal pricePerUnit, int numUnits) {
        this.price = pricePerUnit;
        this.units = numUnits;
    }
    public BigDecimal getPrice(){ return this.price;}
    public int getUnits() {return this.units;}
}
