package com.alerts.decorator;

import com.alerts.Alert;

public class RepeatedAlertDecorator extends Alert {
    private final Alert alert;

    public RepeatedAlertDecorator(Alert alert) {
        super(alert.getPatientId(), alert.getCondition(), alert.getTimestamp());
        this.alert = alert;
    }
}
