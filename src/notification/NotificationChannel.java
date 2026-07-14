package notification;

import model.Customer;

public interface NotificationChannel  {
    void sendNotification(Customer customer, String message);
}