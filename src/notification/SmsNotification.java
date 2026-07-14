package notification;

import model.Customer;

public class SmsNotification implements NotificationChannel {
    @Override
    public void sendNotification(Customer customer, String message) {
        System.out.println("Sent a notification through SMS to " + customer.getPhoneNumber());
    }
}