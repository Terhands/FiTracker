package com.terhands.fitracker.models;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Exercise extends RealmObject {

    private String name;
    private RealmList<TrackingProperty> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<TrackingProperty> getProperties() {
        return properties;
    }

    public void setProperties(RealmList<TrackingProperty> properties) {
        this.properties = properties;
    }
}
