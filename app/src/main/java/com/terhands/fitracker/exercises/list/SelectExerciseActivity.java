package com.terhands.fitracker.exercises.list;

import android.content.Intent;
import android.view.View;

import com.terhands.fitracker.exercises.list.viewholders.ExerciseViewController;
import com.terhands.fitracker.models.Exercise;

public class SelectExerciseActivity extends BaseExercisesActivity {

    public static final String EXTRA_EXERCISE_NAME = "EXTRA_EXERCISE_NAME";

    @Override
    protected View.OnLongClickListener onExerciseLongClick(final ExerciseViewController controller) {
        return null;
    }

    @Override
    protected View.OnClickListener onExerciseClick(final Exercise exercise) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(EXTRA_EXERCISE_NAME, exercise.getName());
                setResult(RESULT_OK, data);
                finish();
            }
        };
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
