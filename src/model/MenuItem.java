package src.model;

public class MenuItem {
    private final int id;
    private final String name;
    private double price;
    private static idCounter = 0;

    public MenuItem(String name, double price) {
        id = idCounter++;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}