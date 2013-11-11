package ee.ut.math.tvt;

import org.junit.Before;
import org.junit.Test;

public class BillTest {

    // Bill row costs (reused)
    private static double cost1 = 452.12;
    private static double cost2 = 65.22;
    private static double cost3 = 102.0;
    private static double totalSum = 619.34;
    
    /**
     * Methods with @Before annotations will be invoked before each test is run.
     */
    @Before
    public void setUp() {
        
    }

    // TODO
    @Test
    public void testBillWithNoRows() {
    
    }


    @Test
    public void testBillWithOneRow() {
    	BillRow r =  new BillRow( item1, 1);
    	bill b= new Bill();
    	b.addbillrow(r);
    	assertEcuals(b.getTotalSum(), 3.5, 0,0001);
    	   	
    }

    @Test
    public void testBillWithManyRows() {
    	BillRow r1 =  new BillRow( item1, 1);
    	BillRow r2 =  new BillRow( item2, 1);
    	bill b= new Bill();
    	b.addbillrow(r1);
    	b.addbillrow(r2);
    	assertEcuals(b.getTotalSum(), 7, 0,0001);
    }
    
}
