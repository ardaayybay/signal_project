package com.alerts.decorator;

// Inherits from base Alert class to allow extension of alert behavior dynamically.
import com.alerts.Alert;

public abstract class AlertDecorator extends Alert {

    // Holds the original alert that is being decorated (core of the Decorator Pattern).
    protected Alert decoratedAlert;

    // Constructor passes base alert properties to the superclass Alert.
    public AlertDecorator(Alert decoratedAlert) {
        super(decoratedAlert.getPatientId(), decoratedAlert.getCondition(), decoratedAlert.getTimestamp());
        this.decoratedAlert = decoratedAlert;
    }

    // Base behavior can be extended by subclasses. This acts as a placeholder.
    public void trigger() {
        System.out.println(decoratedAlert.getCondition());
    }
}
