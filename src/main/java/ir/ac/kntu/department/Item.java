package ir.ac.kntu.department;

import ir.ac.kntu.helper.Date;

public class Item {

    enum Items{
        hasTV(15),hasRefrigerator(10),hasAirConditioner(5);
        private int percent;
        Items(int percent){
            this.percent = percent;
        }

        public int getPercent() {
            return percent;
        }

        private double payment(double pay){
            return pay + ((pay * this.getPercent()) / 100);
        }
    }

    private String itemId;
    private String itemName;
    private double price;
    private boolean isHealthy;
    private Date checkUp;

    public Item(String itemId,String itemName){
        this.itemId = itemId;
        this.itemName = itemName;
        setHealthy(true);
    }

    public double getPrice(double pay) {
        Items item = Item.Items.valueOf(itemName);
        this.price = item.payment(pay);
        return pay;
    }

    public void setCheckUp(Date checkUp) {
        this.checkUp = checkUp;
    }

    public void setHealthy(boolean healthy) {
        this.isHealthy = healthy;
    }
    public boolean getHealthy() {
        return isHealthy;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }
}
