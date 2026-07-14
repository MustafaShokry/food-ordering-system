package payment;

public class CashPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid $" + amount + " using Cash.");
        return true;
    }
}