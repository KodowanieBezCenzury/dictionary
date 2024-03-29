package org.subieslaw.finance.app.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.subieslaw.finance.stockmonitor.*;

@ExtendWith(MockitoExtension.class)
public class StockMonitorTest {

    @Mock
    private StockReader stockReader;

    @Mock
    private StockEvent stockEvent;
    
    @Mock
    private AuditLog auditLog;

    @InjectMocks
    private StockPriceMonitor stockMonitor;

    @Test
    public void should_register_stock_for_monitoring() {
        //given
        this.stockMonitor = StockPriceMonitor.of(null, null, null);
        //when
        boolean registered = this.stockMonitor.registerStockForMonitoring("XXX", null);
        //then
        assertTrue(registered, "Should register stock for monitoring");
    }

    @Test
    public void should_get_current_stock_price() throws IOException {
        //given
        BigDecimal expectedStockValue = BigDecimal.TEN;
        StockReader stockReaderStub = new StockReader() {
            @Override
            public Optional<StockInfo> get(String stockTicker) {
                return Optional.of(
                    StockInfo.of(stockTicker,expectedStockValue));
            }
        };
        this.stockMonitor = StockPriceMonitor.of(stockReaderStub, stockEvent, auditLog);
        //when
        BigDecimal currentPrice = stockMonitor.readCurrentStockPrice("XXX");
        //then
        assertEquals(currentPrice, expectedStockValue);
    }
    
    @Test
    public void should_verify_component_iteractions(){
        //when
        stockMonitor.registerStockForMonitoring("XXX", BigDecimal.TEN);
        stockMonitor.verifyMonitoredStocks();
        //then
        verify(stockReader, times(2)).get(anyString());
        verify(auditLog, times(2)).record(any(AuditEvent.class));
        verify(stockEvent, times(0)).sendBelowThresholdNotification(
                                            anyString(),
                                            any(BigDecimal.class),
                                            any(BigDecimal.class));
    }
    
    @Test
    public void should_trigger_alert_when_price_under_limit(){
        //given
        this.stockMonitor.registerStockForMonitoring("AMZN", BigDecimal.TEN);
        when(stockReader.get("AMZN"))
            .thenReturn(Optional.of(StockInfo.of("AMZN",BigDecimal.ONE)));
        
        //when
        this.stockMonitor.verifyMonitoredStocks();
        
        //then
        verify(stockEvent, times(1))
            .sendBelowThresholdNotification("AMZN", BigDecimal.TEN, BigDecimal.ONE);
    }
}