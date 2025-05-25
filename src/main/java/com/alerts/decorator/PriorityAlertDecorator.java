package com.alerts.decorator;

import com.alerts.Alert;

public class PriorityAlertDecorator extends Alert {
    private final Alert alert;

    public PriorityAlertDecorator(Alert alert) {
        super(alert.getPatientId(), "PRIORITY: " + alert.getCondition(), alert.getTimestamp());
        this.alert = alert;
    }
}
