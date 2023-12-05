import exceptions.DoesNotExistException;
import exceptions.IsClosedException;

import java.math.BigDecimal;

public class ReceiptImpl implements Receipt{
    private final ProductsDB productsDB = new ProductsDB();
    @Override
    public void addLine(String productID, int numUnits) throws IsClosedException, DoesNotExistException {
    }

    @Override
    public void addTaxes(BigDecimal percent) throws IsClosedException {
    }

    @Override
    public BigDecimal getTotal() {
        return null;
    }
}
