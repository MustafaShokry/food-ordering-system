package model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public MenuItem getItemById(int id) {
        for (MenuItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}