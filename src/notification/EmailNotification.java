package notification;
import model.Customer;

public class EmailNotification implements NotificationChannel {
    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("Sent a notification through email to " + customer.getEmail());
    }
}