package com.terhands.fitracker.models;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ExerciseCategory extends RealmObject {

    private String name;
    private RealmList<Exercise> exercises;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(RealmList<Exercise> exercises) {
        this.exercises = exercises;
    }
}
