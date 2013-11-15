package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import ee.ut.math.tvt.salessystem.ui.tabs.HistoryTab;

// Implements fake DisplayItem
	@Entity
	@Table(name="HistoryItem")
	public class HistoryItem implements Cloneable, DisplayableItem {
    
	@Column(name="sale_date")
	private String date;
	
	@Column(name="sale_time")
	private String time;
    
    @Column (name="price_total")
    private double price;
    
    @OneToMany(mappedBy ="history")
    private  Set<SoldItem>  goods;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    static long counter = 0;
    
    public HistoryItem() {
            goods = new HashSet<SoldItem>();
            price = 0;
    }
    
    public HistoryItem(List<SoldItem> goods) {
    	this.goods = new HashSet<SoldItem>();
        this.goods.addAll(goods);
        id = counter++;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        this.time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        price = 0.0;
    	
    	for(int i = 0; i < goods.size(); i++) {
    		price += goods.get(i).getPrice();
    	}
    	this.id = 0; 	
    	
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<SoldItem> getGoods() {
		return goods;
	}

	public void setGoods(Set<SoldItem> goods) {
		this.goods = goods;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setId(long itemId){
		this.id = itemId;
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
