import src.model.Customer;

public interface NotificationChannel  {
    void sendNotification(Customer customer, String message);
}