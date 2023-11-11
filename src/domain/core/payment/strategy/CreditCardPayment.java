package domain.core.payment.strategy;

public class CreditCardPayment implements Payment{
    private String cardNumber;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " with Credit Card");
    }
}
