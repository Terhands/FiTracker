package com.terhands.fitracker.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Workout extends RealmObject{

    private Date exerciseDate;
    private RealmList<WorkoutExercise> exercises;

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public RealmList<WorkoutExercise> getExercises() {
        return exercises;
    }

    public void setExercises(RealmList<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }
}
