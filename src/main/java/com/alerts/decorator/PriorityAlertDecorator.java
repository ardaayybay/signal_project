package com.alerts.decorator;

import com.alerts.Alert;
// This class adds priority information to an existing Alert.
public class PriorityAlertDecorator extends Alert {
    private final Alert alert;
    // Constructor modifies the condition to mark it as high priority.
    public PriorityAlertDecorator(Alert alert) {
        
        super(alert.getPatientId(), "PRIORITY: " + alert.getCondition(), alert.getTimestamp());
        this.alert = alert;
    }
}
