import src.model.Customer;
import src.model.MenuItem;
import src.model.Order;
import src.printer.ReceiptPrinter;

public class OrderService {

    private final ReceiptPrinter receiptPrinter;

    public OrderService(ReceiptPrinter receiptPrinter) {
        this.receiptPrinter = receiptPrinter;
    }


    public order createOrder(int orderId, Customer customer){
        return new Order(orderId, customer);
    }

    public void addItem(Order order, MenuItem menuItem, int quantity){
        OrderItem orderItem = new orderItem(menuItem, quantity);
        order.addItem(orderItem);
    }

    public void removeItem(Order order, OrderItem orderItem){
        order.removeItem(OrderItem orderItem);
    }

    public double calculateTotal(Order order) {
        return order.getTotalPrice();
    }

}