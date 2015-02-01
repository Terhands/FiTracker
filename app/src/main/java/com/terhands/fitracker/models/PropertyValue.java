package com.terhands.fitracker.models;

import io.realm.RealmObject;

public class PropertyValue extends RealmObject {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
