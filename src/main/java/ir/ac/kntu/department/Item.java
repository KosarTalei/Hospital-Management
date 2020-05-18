package ir.ac.kntu.department;

import ir.ac.kntu.helper.Date;

public class Item {

    public enum Option{
        FAIL,SEE_ROOM,SEE_CHECKUP,HEALTH,ADD,REMOVE,EXIT,UNDEFINED
    }

    enum Items{
        tv(15),refrigerator(10),airConditioner(5);
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

    public Item(){

    }

    public Item(String itemId){
        this.itemId = itemId;
        this.itemName = findName();
        setHealthy(true);
        findName();
    }

    public String findName(){
        if(itemId.startsWith("001")){
            return "refrigerator";
        }else if(itemId.startsWith("002")){
            return "tv";
        }else if(itemId.startsWith("003")){
            return "airConditioner";
        }
        return "no items";
    }

    public double getPrice(double pay) {
        Items item = Item.Items.valueOf(itemName);
        this.price = item.payment(pay);
        return pay;
    }

    public Item findItem(String id, Room room) {
        for (Item item : room.getItems()) {
            if (item.getItemId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item, Room room) {
        if (!room.getItems().contains(item)) {
            room.getItems().add(item);
            System.out.println("Item added successfully!");
        }else {
            System.out.println("Item already exist!");
        }
    }

    public void removeItem(Item item, Room room) {
        if (room.getItems().contains(item)) {
            room.getItems().remove(item);
            System.out.println("item removed successfully!");
            return;
        }
        System.out.println("item never exist!");
    }

    public void setCheckUp(Date checkUp) {
        this.checkUp = checkUp;
    }

    public Date getCheckUp() {
        return checkUp;
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

    @Override
    public String toString() {
        return
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", isHealthy=" + isHealthy +
                ", checkUp=" + checkUp;
    }
}