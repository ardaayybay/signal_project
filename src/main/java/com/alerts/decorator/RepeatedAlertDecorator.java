package com.alerts.decorator;

import com.alerts.Alert;

public class RepeatedAlertDecorator extends Alert {
    // Holds the original alert to decorate.
    private final Alert alert;
    // Constructor keeps original alert data.
    public RepeatedAlertDecorator(Alert alert) {
        super(alert.getPatientId(), alert.getCondition(), alert.getTimestamp());
        this.alert = alert;
    }
}
