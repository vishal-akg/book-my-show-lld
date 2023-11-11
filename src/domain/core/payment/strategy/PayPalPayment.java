package domain.core.payment.strategy;

public class PayPalPayment implements Payment{
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " with PayPal");
    }
}
