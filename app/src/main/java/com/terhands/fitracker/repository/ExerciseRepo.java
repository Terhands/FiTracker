package com.terhands.fitracker.repository;

import android.content.Context;

import com.terhands.fitracker.models.Exercise;

public class ExerciseRepo extends BaseRepo<Exercise> {

    public ExerciseRepo(Context context) {
        super(context);
    }

    public Exercise getByName(String name) {
        return realm.where(Exercise.class).equalTo("name", name).findFirst();
    }

    @Override
    protected Exercise getSavedRealmObject(Exercise exercise) {
        return realm.where(Exercise.class).equalTo("name", exercise.getName()).findFirst();
    }

    @Override
    protected void updateValues(Exercise updated, Exercise toSave) {
        toSave.setName(updated.getName());
        toSave.setProperties(updated.getProperties());
    }
}
