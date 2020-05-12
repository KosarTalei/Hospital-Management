package ir.ac.kntu.department;

import ir.ac.kntu.Patient;

public class ExtraOrders {

    private String customerId;
    private String dateTime;
    private Item item;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Patient patient) {
        this.customerId = patient.getId();
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
