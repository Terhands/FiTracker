package com.terhands.fitracker.repository;

import android.content.Context;

import com.terhands.fitracker.models.ExerciseCategory;

public class ExerciseCategoryRepo extends BaseRepo<ExerciseCategory> {

    public ExerciseCategoryRepo(Context context) {
        super(context);
    }

    public ExerciseCategory getExerciseCategoryByName(String name) {
        if(name == null) {
            return null;
        } else {
            return realm.where(ExerciseCategory.class).equalTo("name", name).findFirst();
        }
    }

    @Override
    protected ExerciseCategory getSavedRealmObject(String key) {
        return getExerciseCategoryByName(key);
    }

    @Override
    protected void updateValues(ExerciseCategory updatedObject, ExerciseCategory toSave) {
        toSave.setName(updatedObject.getName());
    }
}
