import exceptions.DoesNotExistException;

import java.math.BigDecimal;
import java.util.HashMap;


public class ProductsDBImpl implements ProductsDB{
    HashMap<String,BigDecimal> DB = new HashMap<>();

    public ProductsDBImpl(){}
    public void addItem(String productID, BigDecimal PPU){
        DB.put(productID,PPU);
    }

    public BigDecimal getPrice(String productID) throws DoesNotExistException{
        BigDecimal fetched = DB.get(productID);
        if (fetched==null)
            throw new DoesNotExistException("item not in the DB");
        return fetched;
    }
}
