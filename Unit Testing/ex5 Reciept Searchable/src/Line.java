import java.math.BigDecimal;

public record Line(BigDecimal pricePerUnit, int number) {
    public BigDecimal getTotal() {
        return pricePerUnit.multiply(new BigDecimal(number));
    }
}