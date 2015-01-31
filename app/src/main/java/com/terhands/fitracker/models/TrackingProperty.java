package com.terhands.fitracker.models;

import io.realm.RealmObject;

public class TrackingProperty extends RealmObject {

    private String name;
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(Property.getPropertyByName(name) != null) {
            this.name = name;
        }
    }

    public String getUnit() {
        return unit;
    }

    public Property getProperty() {
        return Property.getPropertyByName(getName());
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public enum Property {
        REPS("#"), WEIGHT("lbs"), SETS("#"), DURATION("ms"), DISTANCE("km");

        public final String unit;

        private Property(String unit) {
            this.unit = unit;
        }

        public static Property getPropertyByName(String name) {
            for(Property p : Property.values()) {
                if(p.toStoredString().equals(name.toLowerCase())) {
                    return p;
                }
            }
            return null;
        }

        public String toStoredString() {
            return toString().toLowerCase();
        }
    }

}
