package payment;

public class InstallmentByValuPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid $" + amount + " in installment by valu.");
        return true;
    }
}