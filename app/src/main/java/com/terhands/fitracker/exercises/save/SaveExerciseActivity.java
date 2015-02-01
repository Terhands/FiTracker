package com.terhands.fitracker.exercises.save;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.models.ExerciseCategory;
import com.terhands.fitracker.repository.ExerciseCategoryRepo;
import com.terhands.fitracker.repository.ExerciseRepo;

public class SaveExerciseActivity extends ActionBarActivity {

    public static final String EXTRA_EXERCISE_NAME = "EXTRA_EXERCISE_NAME";
    public static final String EXTRA_EXERCISE_CATEGORY_NAME = "EXTRA_EXERCISE_CATEGORY_NAME";

    private ExerciseCategoryRepo exerciseCategoryRepo;
    private ExerciseRepo exerciseRepo;
    private Exercise exercise;
    private SaveExerciseViewController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_exercise);

        exerciseCategoryRepo = new ExerciseCategoryRepo(this);
        exerciseRepo = new ExerciseRepo(this);
        controller = new SaveExerciseViewController(this);

        init();
    }

    private void init() {

        String exerciseName = getIntent().getStringExtra(EXTRA_EXERCISE_NAME);
        if(exerciseName != null) {
            exercise = exerciseRepo.getByName(exerciseName);
        }

        if(exercise != null) {
            controller.initState(exercise);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.ms_save:
                saveExercise();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveExercise() {
        if(getIntent().getStringExtra(EXTRA_EXERCISE_CATEGORY_NAME) != null) {
            createNewExercise();
        } else {
            updateExercise();
        }
    }

    private void updateExercise() {
        String oldName = null;
        if(exercise != null) {
            oldName = exercise.getName();
        }

        exercise = new Exercise();
        exercise.setProperties(controller.getSelectedProperties());
        exercise.setName(controller.getExerciseName());
        exercise = exerciseRepo.save(oldName, exercise);
    }

    private void createNewExercise() {
        updateExercise();

        String categoryName = getIntent().getStringExtra(EXTRA_EXERCISE_CATEGORY_NAME);
        ExerciseCategory exerciseCategory = exerciseCategoryRepo.getExerciseCategoryByName(categoryName);
        exerciseCategory.getExercises().add(exercise);
        exerciseCategoryRepo.save(categoryName, exerciseCategory);
    }
}
