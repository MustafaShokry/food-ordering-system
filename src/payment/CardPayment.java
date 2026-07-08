public class CardPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid $" + amount + " using Card.");
        return true;
    }
}