package com.alerts.decorator;

import com.alerts.Alert;

public class RepeatedAlertDecorator extends AlertDecorator {
    private int repeatCount;

    public RepeatedAlertDecorator(Alert decoratedAlert, int repeatCount) {
        super(decoratedAlert);
        this.repeatCount = repeatCount;
    }

    @Override
    public void trigger() {
        for (int i = 0; i < repeatCount; i++) {
            System.out.println("Repeat " + (i + 1) + ": " + decoratedAlert.getCondition());
        }
    }
}

