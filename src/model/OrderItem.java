package src.model;

public class OrderItem {
    // Mandatory
    private final MenuItem menuItem;
    private final int quantity;

    // Optional
    private final boolean extraSauce;
    private final boolean largeSize;

    private OrderItem(Builder builder) {
        this.menuItem = builder.menuItem;
        this.quantity = builder.quantity;
        this.extraSauce = builder.extraSauce;
        this.largeSize = builder.largeSize;
    }

    public MenuItem getMenuItem(){
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean hasExtraSauce() {
        return extraSauce;
    }

    public boolean isLargeSize() {
        return largeSize;
    }

    public double getSubTotalPrice(){
        double price = menuItem.getPrice();
        if (largeSize) {
            price += 20;
        }
        if (extraSauce) {
            price += 5;
        }
        return price * quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        // Mandatory
        private MenuItem menuItem;
        private int quantity;

        // Optional
        private boolean extraSauce = false;
        private boolean largeSize = false;

        public Builder menuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
            return this;
        }
        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }
        public Builder extraSauce(boolean extraSauce) {
            this.extraSauce = extraSauce;
            return this;
        }
        public Builder largeSize(boolean largeSize) {
            this.largeSize = largeSize;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}