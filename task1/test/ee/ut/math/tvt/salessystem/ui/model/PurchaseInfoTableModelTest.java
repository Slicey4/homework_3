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
		item1 = new SoldItem(new StockItem((long) 1, "beer", 3.40, 50), 3);
		item2 = new SoldItem(new StockItem((long) 2, "vesi", 1.50, 10), 10);
		item3 = new SoldItem(new StockItem((long) 3, "siider", 2.00, 10), 1);
	}

	@Test
	public void testAddSoldItem() {
		setUp();
//		purchaseInfoTableModel = new PurchaseInfoTableModel();
		purchaseInfoTableModel.addItem(item1);
		item4 = purchaseInfoTableModel.getTableRows().get(0);

		assertEquals(item1.getStockItem().getId(), item4.getStockItem().getId());
		assertEquals(item1.getStockItem().getName(), item4.getStockItem()
				.getName());
		assertEquals(item1.getStockItem().getDescription(), item4
				.getStockItem().getDescription());
		assertEquals(item1.getStockItem().getPrice(), item4.getStockItem()
				.getPrice(), 0.0001);
		assertEquals(item1.getStockItem().getQuantity(), item4.getStockItem()
				.getQuantity());
		assertEquals(item1.getQuantity(), item4.getQuantity());
	}

	@Test
	public void testGetSumWithNoItems() {
		setUp();
//		purchaseInfoTableModel = new PurchaseInfoTableModel();
		assertEquals(0.00, purchaseInfoTableModel.getSum(), 0.0001);
	}

	@Test
	public void testGetSumWithOneItem() {
		setUp();
//		purchaseInfoTableModel = new PurchaseInfoTableModel();
		purchaseInfoTableModel.addItem(item1);
		assertEquals(10.2, purchaseInfoTableModel.getSum(), 0.0001);
	}

	@Test
	public void testGetSumWithMultipleItems() {
		setUp();
//		purchaseInfoTableModel = new PurchaseInfoTableModel();
		purchaseInfoTableModel.addItem(item1);
		purchaseInfoTableModel.addItem(item2);
		purchaseInfoTableModel.addItem(item3);
		assertEquals(27.2, purchaseInfoTableModel.getSum(), 0.0001);
	}

}
