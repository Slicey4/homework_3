package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving
 * history.
 */

@Entity
@Table(name = "SOLDITEM")
public class SoldItem implements Cloneable, DisplayableItem {

	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "SALE_ID", nullable = true)
	private HistoryItem historyItem;
	
	
	@Column(name = "stockitem_id")
	private StockItem stockItem;

	@Column(name = "name")
	private String name;

	@Column(name = "quantity")
	private Integer quantity;

	

	@Column(name = "itemprice")
	private double price;

	

	// @Id
	// Indices??

	public SoldItem(StockItem stockItem, int quantity) {
		this.id = stockItem.getId();
		this.stockItem = stockItem;
		this.name = stockItem.getName();
		this.price = stockItem.getPrice();
		this.quantity = quantity;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	//
	public void setnewQuantity(Integer a) {
		this.quantity = quantity - a;
	}

	public double getSum() {
		return price * ((double) quantity);
	}

	public StockItem getStockItem() {
		return stockItem;
	}

	public void setStockItem(StockItem stockItem) {
		this.stockItem = stockItem;
	}
	
	public HistoryItem getHistoryItem() {
		return historyItem;
	}

}
