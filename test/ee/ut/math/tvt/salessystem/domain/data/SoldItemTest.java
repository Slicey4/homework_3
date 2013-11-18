package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SoldItemTest {
	SoldItem item1 , item2, item3;
	StockItem stockitem1, stockitem2, stockitem3 ;
	
	@Before
	public void setUp() {
		
		stockitem1= new StockItem((long) 4, "beer", 2.60, 5);
		stockitem2= new StockItem((long) 5, "sider", 2.50, 7);
		stockitem3= new StockItem((long) 6, "cheese", 3.49, 4);
		
		item1= new SoldItem(stockitem1, 2);
		item2= new SoldItem(stockitem2, 5);
		item3= new SoldItem(stockitem2, 0);
	}
	

	@Test
	public void testGetSum(){
		
		assertEquals("sum 2.60 and 2.50 should be 17.7",17.7, item1.getSum()+item2.getSum(), 0.0001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity(){
		
		assertEquals("sum with zero qunatity", 0.0, item3.getSum(), 0.0001);
	}
}
