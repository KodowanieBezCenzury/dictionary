package org.subieslaw.finance.stockmonitor;

import java.math.BigDecimal;

public interface StockEvent {

    StockAlert sendBelowThresholdNotification(String ticker, BigDecimal threshold, BigDecimal price);

}
