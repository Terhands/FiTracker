package com.terhands.fitracker.exercises.save;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.repository.ExerciseRepo;

public class SaveExerciseActivity extends ActionBarActivity {

    public static final String EXTRA_EXERCISE_NAME = "EXTRA_EXERCISE_NAME";

    private ExerciseRepo exerciseRepo;
    private Exercise exercise;
    private SaveExerciseViewController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_exercise);

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
        } else {
            exercise = new Exercise();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save_exercise, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.mse_save:
                saveExercise();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveExercise() {
        String oldName = exercise.getName();
        exercise = new Exercise();
        exercise.setProperties(controller.getSelectedProperties());
        exercise.setName(controller.getExerciseName());
        exerciseRepo.save(oldName, exercise);
    }
}
