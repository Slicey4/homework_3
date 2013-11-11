package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BillRowTest {

  private SaleItem item1;
  
  /**
   * Methods with @Before annotations will be invoked before each test is run.
   */
  @Before
  public void setUp() {
    item1 = new SaleItem("Lauaviin", 3.50); 
  }

  @Test
  public void testRowSumWithZeroQuantity() {
    BillRow r = new BillRow(item1,0);
    
    assertEquals(r.getRowPrice(), 0.0, 0.0001);
  }
  
  // TODO
  @Test
  public void testRowSumWithoutDiscount() {
	  Billrow r = new Billrow(item1, 1);
	    assertEquals(r.getRowPrice(), 3.5, 0.0001);
  }
  @Test
  public void testRowSumWithDiscount() {
	  Billrow r = new Billrow(item1, 1, 10);
	  
	  assertEquals(r.getRowPrice(), 3.15, 0.0001);
	  

  }
  	
  @Test(ecpected=IllegalArgumentException.class)
  public void testRowSumWithInvalidDiscount() {
	  Billrow r = new Billrow(item1, 3);
	  

  }
  
  @Test
  public void testRowSumWithNegativeQuantity() {
	  Billrow r = new Billrow(item1, 4);

  }

  @Test
  public void testRowSumWithNegativePrice() {
	  Billrow r = new Billrow(item1, 5);
	  

  }
  
}
