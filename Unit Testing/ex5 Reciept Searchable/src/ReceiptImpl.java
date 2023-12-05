import exceptions.DoesNotExistException;
import exceptions.IsClosedException;

import java.math.BigDecimal;

public interface ReceiptImpl {
    void addLine(String productId, int numUnits) throws IsClosedException, DoesNotExistException;
    void addTaxes(BigDecimal percent) throws IsClosedException;
    BigDecimal getTotal();
}
