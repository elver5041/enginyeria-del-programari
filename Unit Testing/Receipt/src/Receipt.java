import java.math.BigDecimal;
import java.util.*;

public class Receipt {
    BigDecimal total = BigDecimal.ZERO;
    Boolean isRecieptClosed = false;
    List receipt = new ArrayList<>();
    public void addLine(BigDecimal pricePerUnit, int numUnits) throws IsClosedException {
        if(isRecieptClosed) throw new IsClosedException("Reciept Closed");
        total=total.add(pricePerUnit.multiply(BigDecimal.valueOf(numUnits)));
        receipt.add(new Pair(pricePerUnit, numUnits));
    }
    public void addTaxes(BigDecimal percent) throws IsClosedException {
        if(isRecieptClosed) throw new IsClosedException("Reciept Closed");
        total = total.add(total.multiply(percent));
        isRecieptClosed = true;
    }
    public BigDecimal getTotal() {
        return total;
    }
}

