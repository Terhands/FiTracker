package com.terhands.fitracker.exercises.list;

import android.view.View;

import com.terhands.fitracker.exercises.list.viewholders.ExerciseViewController;
import com.terhands.fitracker.models.Exercise;

public class SelectExerciseActivity extends BaseExercisesActivity {

    @Override
    protected View.OnLongClickListener onExerciseLongClick(final ExerciseViewController controller) {
        return null;
    }

    @Override
    protected View.OnClickListener onExerciseClick(final Exercise exercise) {
        return null;
    }

}
