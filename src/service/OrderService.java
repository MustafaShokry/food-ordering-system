package src.service;

import src.model.Customer;
import src.model.Order;
import src.notification.NotificationChannel;
import src.payment.PaymentMethod;
import src.printer.ReceiptPrinter;

public class OrderService {

    private final ReceiptPrinter receiptPrinter;

    public OrderService(ReceiptPrinter receiptPrinter) {
        this.receiptPrinter = receiptPrinter;
    }


    public Order.Builder createOrder(Customer customer){
        return Order.builder()
                .customer(customer);
    }

    public double calculateTotal(Order order){
        return order.getTotalPrice();
    }

    public void checkout(Order order,
                         PaymentMethod paymentMethod,
                         NotificationService notificationService) {

        double total = calculateTotal(order);

        boolean success = paymentMethod.pay(total);

        if (!success) {
            System.out.println("Payment failed.");
            return;
        }

        receiptPrinter.print(order);

        notificationService.send(
                order.getCustomer(),
                "Your order has been placed successfully."
        );
    }

}