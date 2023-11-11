package domain.core.payment.factory;

import domain.core.payment.strategy.Payment;

public interface PaymentFactory {
    Payment createPayment(String ...args);
}
