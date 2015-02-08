package com.terhands.fitracker.models;

import java.text.DecimalFormat;

import io.realm.RealmObject;

public class PropertyValue extends RealmObject {

    private TrackingProperty trackedProperty;
    private int value;

    public TrackingProperty getTrackedProperty() {
        return trackedProperty;
    }

    public void setTrackedProperty(TrackingProperty trackedProperty) {
        this.trackedProperty = trackedProperty;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDisplayValue() {
        switch(trackedProperty.getProperty()) {
            case WEIGHT:
            case SETS:
            case REPS:
                return String.valueOf(value);

            case DISTANCE:
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                return df.format((double) value / 1000d);

            case DURATION:
                int hours = value / (60 * 60);
                int minutes = (value / 60) % 60;
                int seconds = value % 60;

                return String.valueOf(hours) + ":" +
                        String.valueOf(minutes) + ":" +
                        String.valueOf(seconds);
            default:
                return "";
        }
    }
}
