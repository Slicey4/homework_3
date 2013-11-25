package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StockItemTest {
	StockItem item1;
	
	@Before
	public void setUp() {
		item1=new StockItem((long) 1, "lays", 1.19, 5) ;
	}
	
	@Test
	public void	testEquals(){
		assertEquals(item1, item1.clone());
	}
	
	@Test
	public void testGetColumn(){
		assertEquals(5, item1.getColumn(3));
	}
}
