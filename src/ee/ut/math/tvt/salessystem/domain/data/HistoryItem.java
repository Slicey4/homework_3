package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import ee.ut.math.tvt.salessystem.ui.tabs.HistoryTab;

// Implements fake DisplayItem
public class HistoryItem implements Cloneable, DisplayableItem {
    
	@Column(name="date&time")
	private Date date;
    private long time;
    @Column (name="price")
    private double price;
    
    @OneToMany(mappedBy ="SoldItem")
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<SoldItem> getGoods() {
		return goods;
	}

	public void setGoods(List<SoldItem> goods) {
		this.goods = goods;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
