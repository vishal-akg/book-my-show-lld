package domain.core.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private BigDecimal money;

    public Money(BigDecimal money) {
        this.money = money.setScale(2, RoundingMode.HALF_EVEN);
    }

    public double getAmount() {
        return money.doubleValue();
    }
}
