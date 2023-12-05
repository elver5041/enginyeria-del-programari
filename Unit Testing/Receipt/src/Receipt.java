import exceptions.IsClosedException;

import java.math.BigDecimal;
import java.util.*;

public class Receipt {
    BigDecimal total = BigDecimal.ZERO;
    Boolean isReceiptClosed = false;
    List<Pair> receipt = new ArrayList<>();
    public void addLine(BigDecimal pricePerUnit, int numUnits) throws IsClosedException {
        if(isReceiptClosed) throw new IsClosedException("Receipt Closed");
        total=total.add(pricePerUnit.multiply(BigDecimal.valueOf(numUnits)));
        receipt.add(new Pair(pricePerUnit, numUnits));
    }
    public void addTaxes(BigDecimal percent) throws IsClosedException {
        if(isReceiptClosed) throw new IsClosedException("Receipt Closed");
        total = total.add(total.multiply(percent));
        isReceiptClosed = true;
    }
    public BigDecimal getTotal() {
        return total;
    }
}

