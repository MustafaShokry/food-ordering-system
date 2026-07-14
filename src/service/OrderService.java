package service;

import model.Customer;
import model.Order;
import notification.NotificationChannel;
import payment.PaymentMethod;
import printer.ReceiptPrinter;

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
                         NotificationChannel notificationService) {

        double total = calculateTotal(order);

        boolean success = paymentMethod.pay(total);

        if (!success) {
            System.out.println("Payment failed.");
            return;
        }

        receiptPrinter.print(order);

        notificationService.sendNotification(
                order.getCustomer(),
                "Your order has been placed successfully."
        );
    }

}