package org.subieslaw.finance.stockmonitor;

import java.util.Optional;

public interface StockReader {

    Optional<StockInfo> get(String stockTicker);

}
