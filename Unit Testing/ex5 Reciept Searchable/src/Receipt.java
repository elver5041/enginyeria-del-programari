import exceptions.DoesNotExistException;
import exceptions.IsClosedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    final ProductsDB productsDB = new ProductsDB();
    List<Line> receipt = new ArrayList<>();
    Boolean isReceiptClosed = false;
    BigDecimal taxes = BigDecimal.ZERO;

    public void addLine(String productId, int numUnits) throws IsClosedException, DoesNotExistException {
        if(isReceiptClosed)
            throw new IsClosedException("receipt already closed");
        BigDecimal pricePerUnit = productsDB.getPrice(productId);
        receipt.add(new Line(pricePerUnit,numUnits));
    }

    public void addTaxes(BigDecimal percent) throws IsClosedException {
        if(isReceiptClosed)
            throw new IsClosedException("receipt already closed");
        BigDecimal total = getTotal();
        taxes = total.multiply(percent);
        isReceiptClosed = true;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for(Line line : receipt)
            total = total.add(line.getTotal());
        return total.add(taxes);
    }
}
