package com.terhands.fitracker.repository;

import android.content.Context;

import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.models.TrackingProperty;

import java.util.List;

public class ExerciseRepo extends BaseRepo<Exercise> {

    public ExerciseRepo(Context context) {
        super(context);
    }

    public Exercise getByName(String name) {
        return realm.where(Exercise.class).equalTo("name", name).findFirst();
    }

    @Override
    protected Exercise getSavedRealmObject(String key) {
        return getByName(key);
    }

    @Override
    protected void updateValues(Exercise updated, Exercise toSave) {
        if(toSave != null) {
            toSave.getProperties().clear();
            List<TrackingProperty> newProperties = realm.copyToRealm(updated.getProperties());
            toSave.getProperties().addAll(newProperties);
            toSave.setName(updated.getName());
        }
    }
}
