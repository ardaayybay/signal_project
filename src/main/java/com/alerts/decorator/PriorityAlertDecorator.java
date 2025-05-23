package com.alerts.decorator;

import com.alerts.Alert;

public class PriorityAlertDecorator extends AlertDecorator {
    private String priorityLevel;

    public PriorityAlertDecorator(Alert decoratedAlert, String priorityLevel) {
        super(decoratedAlert);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void trigger() {
        System.out.println("[PRIORITY: " + priorityLevel + "] " + decoratedAlert.getCondition());
    }
}
