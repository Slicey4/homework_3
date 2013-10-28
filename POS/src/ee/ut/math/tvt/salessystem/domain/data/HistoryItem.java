package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

// Implements fake DisplayItem
public class HistoryItem implements Cloneable, DisplayableItem {
    private Date date;
    private long time;
    private double price;
    private List<SoldItem> goods;
    
    public HistoryItem(List<SoldItem> goods) {
		Calendar a = new GregorianCalendar();
    	this.date = a.getTime();
    	this.time = a.getTimeInMillis();
    	price = 0.0;
    	
    	for(int i = 0; i < goods.size(); i++) {
    		price += goods.get(i).getPrice();
    	}
    	
    	this.goods = goods;
    }

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		// return null;
		return this.time;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		// return null;
		return date.toString();
	}

	@Override
	public int getQuantity() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Date getDate() {
		return date;
	}
	
	public long getTime() {
		return time;
	}
	
	public double getPrice() {
		return price;
	}
}
