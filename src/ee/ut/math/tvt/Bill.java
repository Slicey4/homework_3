package ee.ut.math.tvt;

import java.util.ArrayList;
import java.util.List;

/**
 * A bill item contains a list of items.
 *  -- new items can be added to this bill with method <code>addBillItem</code>.
 *  -- the total sum of all items of the bill can be calculated by <code>getTotalSum</code>.
 */
public class Bill {

	private List<BillRow> rows;
	
	private String name;
	
	public Bill() {
		rows = new ArrayList<BillRow>();
	}
	
	public void addBillRow(BillRow br) {
		rows.add(br);
	}
	
	public double getTotalSum() {
	    double price = 0.0;
	    for (BillRow row : rows) {
	        price = price + row.getRowPrice();
	    }
	    return price;
	}
	
}
