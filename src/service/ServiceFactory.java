package service;

import notification.NotificationChannel;
import notification.EmailNotification;
import notification.SmsNotification;
import notification.NotificationType;
import payment.PaymentMethod;
import payment.CardPayment;
import payment.CashPayment;
import payment.InstallmentByValuPayment;
import payment.PaymentType;
import printer.ReceiptPrinter;
import service.OrderService;
import model.Menu;
import model.MenuItem;


public final class ServiceFactory {

    private ServiceFactory() {
    }

    // Bill Pugh Singleton Holder
    private static class Holder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return Holder.INSTANCE;
    }

    public Menu getMenu() {

        Menu menu = new Menu();

        menu.addItem(new MenuItem("Burger", 120));
        menu.addItem(new MenuItem("Pizza", 180));
        menu.addItem(new MenuItem("Fries", 60));
        menu.addItem(new MenuItem("Cola", 35));

        return menu;
    }

    public PaymentMethod createPaymentMethod(PaymentType paymentType) {

        return switch (paymentType) {
            case CASH -> new CashPayment();
            case CARD -> new CardPayment();
            case VALUE -> new InstallmentByValuPayment();
        };
    }

    public NotificationChannel createNotificationChannel(NotificationType notificationType) {

        return switch (notificationType) {
            case EMAIL -> new EmailNotification();
            case SMS -> new SmsNotification();
        };
    }

    public ReceiptPrinter createReceiptPrinter() {
        return new ReceiptPrinter();
    }
    
    public OrderService createOrderService() {
        return new OrderService(createReceiptPrinter());
    }

}