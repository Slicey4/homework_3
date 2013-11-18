package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	StockItem item1, item2, item3, item4;
	StockTableModel warehouseTableModel;
	@Before
	public void setUp() {
		item1= new StockItem((long) 6, "kokteil", 4.50, 8);
		item2= new StockItem((long) 7, "toffee siider", 3.80, 5);
		item3= new StockItem((long) 8, "viinamarjad juustuga", 2.34, 3);
		item4= new StockItem((long) 9, "snacks", 5.60, 10);
		warehouseTableModel = new StockTableModel();

	}
	
	@Test
	public void testValidateNameUniqueness(){
		assertEquals(0, warehouseTableModel.getColumn(item1, 1));
	}
	
	@Test
	public void testHasEnoughInStock(){
		assertEquals("Yes", warehouseTableModel.hasEnough(item4));
	}
	
	@Test
	public void testGetItemByIdWhenItemExists(){
		assertEquals(item1, warehouseTableModel.getItemById(6));
	}
	
	@Test
	public void testGetItemByIdWhenThrowsException(){
		assertEquals(item1, warehouseTableModel.getItemById(100));
		
	}

}
