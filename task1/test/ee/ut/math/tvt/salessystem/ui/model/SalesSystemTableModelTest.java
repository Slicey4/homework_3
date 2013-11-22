package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SalesSystemTableModelTest {
	
	List<StockItem> lst;
    SalesSystemTableModel<StockItem> table;
    StockItem stockItem1;
    
	 @Before
     public void setUp(){
		 lst = new ArrayList<StockItem>();
             table = new StockTableModel();
             stockItem1 = new StockItem((long) 1, "siider", 2.00, 10);
             lst.add(stockItem1);
     }

     @Test
     public void testPopulateWithData() {
             table.populateWithData(lst);
             StockItem stockItem2 = table.getTableRows().get(0);
             assertEquals(stockItem1.getId(), stockItem2.getId());
             assertEquals(stockItem1.getName(), stockItem2.getName());
             assertEquals(stockItem1.getDescription(), stockItem2.getDescription());
             assertEquals(stockItem1.getPrice(), stockItem2.getPrice(), 0.0001);
             assertEquals(stockItem1.getQuantity(), stockItem2.getQuantity());
     }


	

}
