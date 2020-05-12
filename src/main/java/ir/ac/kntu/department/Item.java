package ir.ac.kntu.department;

import ir.ac.kntu.Date;

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

    private int itemId;
    private String itemName;
    private double price;
    private boolean isHealthy;
    private Date checkUp;

    public Item(int itemId,String itemName){
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public double getPrice(double pay) {
        Items item = Item.Items.valueOf(itemName);
        this.price = item.payment(pay);
        return pay;
    }
    public int getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }
}
