import src.notification.NotificationChannel;
import src.notification.EmailNotification;
import src.notification.SmsNotification;
import src.notification.NotificationType;
import src.payment.PaymentMethod;
import src.payment.CardPayment;
import src.payment.CashPayment;
import src.payment.PaymentType;
import src.printer.ReceiptPrinter;


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

    public PaymentMethod createPaymentMethod(PaymentType paymentType) {

        return switch (paymentType) {
            case CASH -> new CashPayment();
            case CARD -> new CardPayment();
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