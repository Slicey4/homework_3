package ee.ut.math.tvt;

public class SaleItem {

  private String itemName;
  
  private double currentPrice;
  
  public SaleItem(String name, double price) {
    this.itemName = name;
    this.currentPrice = price;
  }

  public String getName() {
    return itemName;
  }

  public void setName(String name) {
    this.itemName = name;
  }

  public double getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(double currentPrice) {
    this.currentPrice = currentPrice;
  }
  
}