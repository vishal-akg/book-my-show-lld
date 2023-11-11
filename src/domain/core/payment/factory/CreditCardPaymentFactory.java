package domain.core.payment.factory;

import domain.core.payment.strategy.CreditCardPayment;
import domain.core.payment.strategy.Payment;

public class CreditCardPaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment(String ...args) {
        return new CreditCardPayment(args[0], args[1]);
    }
}
