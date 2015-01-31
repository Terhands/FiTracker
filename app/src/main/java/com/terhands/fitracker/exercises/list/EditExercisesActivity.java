package com.terhands.fitracker.exercises.list;

import android.content.Context;
import android.view.View;

import com.terhands.fitracker.exercises.list.viewholders.ExerciseViewController;
import com.terhands.fitracker.models.Exercise;

public class EditExercisesActivity extends BaseExercisesActivity {

    @Override
    protected View.OnLongClickListener onExerciseLongClick(final ExerciseViewController controller) {
        final Context context = this;

        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                controller.toggleEditOptions();
                return false;
            }
        };
    }

    @Override
    protected View.OnClickListener onExerciseClick(Exercise exercise) {
        return null;
    }
}
