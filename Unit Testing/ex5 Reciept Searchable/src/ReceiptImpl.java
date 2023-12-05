import exceptions.DoesNotExistException;
import exceptions.IsClosedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReceiptImpl {
    private final ProductsDB productsDB = new ProductsDB();
    List<Line> receipt = new ArrayList<>();
    Boolean isReceiptClosed = false;
    BigDecimal taxes = BigDecimal.ZERO;

    public void addLine(BigDecimal pricePerUnit, int numUnits) throws IsClosedException {
        if(isReceiptClosed) throw new IsClosedException("receipt already closed");
        receipt.add(new Line(pricePerUnit,numUnits));
    }

    public void addTaxes(BigDecimal percent) throws IsClosedException {
        if(isReceiptClosed) throw new IsClosedException("receipt already closed");
        taxes = getTotal().multiply(percent);
        isReceiptClosed = true;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for(Line line : receipt){
            total.add(line.totalCost());
        }
        total.add(taxes);
        return total;
    }
}
