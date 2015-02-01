package com.terhands.fitracker.models;

import io.realm.RealmList;
import io.realm.RealmObject;

public class WorkoutExercise extends RealmObject {

    private Exercise exercise;
    private boolean isCompleted;
    private RealmList<PropertyValue> values;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public RealmList<PropertyValue> getValues() {
        return values;
    }

    public void setValues(RealmList<PropertyValue> values) {
        this.values = values;
    }
}
