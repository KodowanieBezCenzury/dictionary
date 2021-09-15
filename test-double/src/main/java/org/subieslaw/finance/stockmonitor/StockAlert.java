package org.subieslaw.finance.stockmonitor;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Getter
public class StockAlert {
    private final String ticker;
    private final BigDecimal threshold;
    private final BigDecimal price;
    private final StockAlertType alertType;    
}

enum StockAlertType {
    LOWER_THEN_EXPECTED, HIGHER_THEN_EXPECTED;
}



