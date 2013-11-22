package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class PurchaseInfoTableModelTest {

	PurchaseInfoTableModel purchaseInfoTableModel;
    SoldItem item1, item2, item3, item4;

    @Before
    public void setUp() {
   	 purchaseInfoTableModel = new PurchaseInfoTableModel();
            item1 = new SoldItem(new StockItem((long) 6 , "beer", 3.40, 50), 3);
            item2 = new SoldItem(new StockItem((long) 7, "vesi", 1.50, 10),10);
            item3 = new SoldItem(new StockItem((long) 7, "siider", 2.00,10), 1);
    }

    @Test
    public void testAddSoldItem() {
   	 purchaseInfoTableModel.addItem(item1);
            SoldItem mItem = purchaseInfoTableModel.getTableRows().get(0);
            
            assertEquals(item1.getStockItem().getId(), mItem.getStockItem().getId());
            assertEquals(item1.getStockItem().getName(), mItem.getStockItem().getName());
            assertEquals(item1.getStockItem().getDescription(), mItem.getStockItem().getDescription());
            assertEquals(item1.getStockItem().getPrice(), mItem.getStockItem().getPrice(), 0.0001);
            assertEquals(item1.getStockItem().getQuantity(), mItem.getStockItem().getQuantity());
            assertEquals(item1.getQuantity(), mItem.getQuantity());
    }

    @Test
    public void testGetSumWithNoItems() {
            assertEquals(0.00, purchaseInfoTableModel.getSum(), 0.0001);
    }

    @Test
    public void testGetSumWithOneItem() {
   	 	purchaseInfoTableModel.addItem(item1);
            assertEquals(10.50, purchaseInfoTableModel.getSum(), 0.0001);
    }

    @Test
    public void testGetSumWithMultipleItems() {
   	 	purchaseInfoTableModel.addItem(item1);
   	 	purchaseInfoTableModel.addItem(item2);
   	 	purchaseInfoTableModel.addItem(item3);
        assertEquals(21.2, purchaseInfoTableModel.getSum(), 0.0001);
    }

}
