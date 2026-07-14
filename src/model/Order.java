package src.model;

import java.util.List;
import java.util.ArrayList;

public class Order {
    // Mandatory
    private final int id;
    private final Customer customer;
    private List<OrderItem> items;

    // Optional
    private final String deliveryNote;
    private final String discountCode;

    private Order(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.items = new ArrayList<>(builder.items);
        this.deliveryNote = builder.deliveryNote;
        this.discountCode = builder.discountCode;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getSubTotalPrice();
        }
        if (discountCode != null && !discountCode.isBlank()) {
            total *= 0.90;
        }
        return totalPrice;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        // Mandatory
        private final int id;
        private Customer customer;
        private static idCounter = 0;

        // Mandatory
        private List<OrderItem> items = new ArrayList<>();

        // Optional
        private String deliveryNote;
        private String discountCode;

        public Builder(int id) {
            id = idCounter++;
        }

        public Builder customer(Customer customer){
            this.customer = customer;
            return this;
        }
        public Builder addItem(OrderItem item) {
            items.add(item);
            return this;
        }
        public Builder addItems(List<OrderItem> items) {
            this.items.addAll(items);
            return this;
        }
        public Builder deliveryNote(String deliveryNote){
            this.deliveryNote = deliveryNote;
            return this;
        }
        public Builder discountCode(String discountCode){
            this.discountCode = discountCode;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}