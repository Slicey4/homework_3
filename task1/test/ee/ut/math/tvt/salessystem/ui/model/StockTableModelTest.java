package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	StockItem item1, item2, item3, item4;
	StockTableModel warehouseTableModel, table, stockTable;
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
		StockTableModel warehouseTableModel = new StockTableModel();
		
		warehouseTableModel.addItem(item1);
		warehouseTableModel.addItem(item2);
		warehouseTableModel.addItem(item1);
     
		assertEquals(2, warehouseTableModel.getRowCount());
	}

	
	@Test
	public void testHasEnoughInStock(){
		warehouseTableModel.addItem(item4);
		warehouseTableModel.addItem(item3);
		assertEquals("Yes", warehouseTableModel.hasEnough(item4));
		assertEquals("No", warehouseTableModel.hasEnough(item3));
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() throws NullPointerException{
		table = new StockTableModel();
        table.addItem(item1);
        assertEquals(item1, table.getItemById(6));
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() throws NoSuchElementException{
		stockTable = new StockTableModel();
		assertEquals(item1, stockTable.getItemById(100));
		
	}

}
