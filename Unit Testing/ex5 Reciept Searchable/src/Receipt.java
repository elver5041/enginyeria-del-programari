import exceptions.DoesNotExistException;
import exceptions.IsClosedException;
import java.math.BigDecimal;

public interface Receipt {
    public void addLine(String productID, int numUnits) throws IsClosedException, DoesNotExistException;
    public void addTaxes(BigDecimal percent) throws IsClosedException;
    public BigDecimal getTotal();
}
