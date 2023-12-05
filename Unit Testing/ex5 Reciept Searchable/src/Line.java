import java.math.BigDecimal;

public class Line {
    public final BigDecimal pricePerUnit;
    public final int number;

    public Line(BigDecimal pricePerUnit, int number){
        this.number = number;
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getTotal(){
        return pricePerUnit.multiply(new BigDecimal(number));
    }
}
