package org.subieslaw.finance.stockmonitor;

public interface AuditLog {

    void record(AuditEvent monitoringTriggered);

}
