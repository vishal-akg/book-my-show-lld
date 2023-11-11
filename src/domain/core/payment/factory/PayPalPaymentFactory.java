package domain.core.payment.factory;

import domain.core.payment.strategy.PayPalPayment;
import domain.core.payment.strategy.Payment;

public class PayPalPaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment(String... args) {
        return new PayPalPayment(args[0]);
    }
}
