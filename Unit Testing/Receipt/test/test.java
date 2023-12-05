import exceptions.IsClosedException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class test {
    Receipt receipt = new Receipt();
    @Test
    public void testAddLine() throws IsClosedException {
        receipt.addLine(BigDecimal.TEN, 2);
        assertEquals(BigDecimal.valueOf(20), receipt.getTotal());
    }
    @Test
    public void testAddTaxes() throws IsClosedException {
        receipt.addLine(BigDecimal.TEN, 2);
        receipt.addTaxes(BigDecimal.valueOf(0.1));
        assertEquals(BigDecimal.valueOf(22.0), receipt.getTotal());
        assertTrue(receipt.isReceiptClosed);
    }
    @Test
    public void testAddLineAfterClosure() throws IsClosedException {
        receipt.addLine(BigDecimal.TEN, 2);
        receipt.addTaxes(BigDecimal.valueOf(0.1));
        assertThrows(IsClosedException.class, () -> receipt.addLine(BigDecimal.TEN, 2));
    }
    @Test
    public void testAddTaxesAfterClosure() throws IsClosedException {
        receipt.addLine(BigDecimal.TEN, 2);
        receipt.addTaxes(BigDecimal.valueOf(0.1));
        assertThrows(IsClosedException.class, () -> receipt.addTaxes(BigDecimal.valueOf(0.1)));
    }
}
