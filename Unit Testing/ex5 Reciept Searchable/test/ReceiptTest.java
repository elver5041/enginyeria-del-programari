import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ReceiptTest {
    @BeforeEach
    public void setup(){
        ProductsDB DB = new ProductsDB();
        DB.addItem("patates",new BigDecimal("4"));
        DB.addItem("desodorant", new BigDecimal("5.5"));
        Receipt receipt = new Receipt();
    }

}
