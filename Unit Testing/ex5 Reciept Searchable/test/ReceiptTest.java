import exceptions.DoesNotExistException;
import exceptions.IsClosedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    Receipt receipt;
    @BeforeEach
    public void setup(){
        receipt = new Receipt();
        receipt.productsDB.addItem("patates",new BigDecimal("10"));
        receipt.productsDB.addItem("desodorant", new BigDecimal("5.5"));
    }
    @Test
    public void testAddLine() throws IsClosedException, DoesNotExistException {
        receipt.addLine("patates", 2);
        assertEquals(BigDecimal.valueOf(20), receipt.getTotal());
        receipt.addLine("desodorant", 4);
        assertEquals(BigDecimal.valueOf(42.0), receipt.getTotal());
    }
    @Test
    public void testAddTaxes() throws IsClosedException, DoesNotExistException {
        receipt.addLine("patates", 2);
        receipt.addTaxes(BigDecimal.valueOf(0.1));
        assertEquals(BigDecimal.valueOf(22.0), receipt.getTotal());
        assertTrue(receipt.isReceiptClosed);
    }
    @Test
    public void testAddLineAfterClosure() throws IsClosedException, DoesNotExistException {
        receipt.addLine("patates", 2);
        receipt.addTaxes(BigDecimal.valueOf(0.1));
        assertThrows(IsClosedException.class, () -> receipt.addLine("patates", 2));
    }
    @Test
    public void testAddTaxesAfterClosure() throws IsClosedException, DoesNotExistException {
        receipt.addLine("patates", 2);
        receipt.addTaxes(BigDecimal.valueOf(0.1));
        assertThrows(IsClosedException.class, () -> receipt.addTaxes(BigDecimal.valueOf(0.1)));
    }
    @Test
    public void testItemNotDB(){
        assertThrows(DoesNotExistException.class, () -> receipt.addLine("Moniato",3));
    }
}

