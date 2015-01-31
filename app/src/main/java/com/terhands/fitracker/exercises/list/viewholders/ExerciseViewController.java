package com.terhands.fitracker.exercises.list.viewholders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.views.collapsinglist.CLVAnimationUtils;

public class ExerciseViewController {

    private final View exerciseContainer;
    private final TextView exerciseName;
    private final View buttonContainer;
    private final ImageButton edit;
    private final ImageButton delete;

    public ExerciseViewController(View exerciseContainer) {
        this.exerciseContainer = exerciseContainer;
        this.exerciseName = (TextView) exerciseContainer.findViewById(R.id.eb_text);
        this.buttonContainer = exerciseContainer.findViewById(R.id.eb_buttons);
        this.edit = (ImageButton) exerciseContainer.findViewById(R.id.eb_edit);
        this.delete = (ImageButton) exerciseContainer.findViewById(R.id.eb_delete);
    }

    public void showExerciseName(Exercise exercise) {
        exerciseName.setText(exercise.getName());
    }

    public void setTopLevelListeners(View.OnClickListener onClick, View.OnLongClickListener onLongClick) {
        exerciseContainer.setOnClickListener(onClick);
        exerciseContainer.setOnLongClickListener(onLongClick);
    }

    public void setExerciseManagerListeners(View.OnClickListener onEdit, View.OnClickListener onDelete) {
        edit.setOnClickListener(onEdit);
        delete.setOnClickListener(onDelete);
    }

    public void addToContainer(ViewGroup container) {
        container.addView(exerciseContainer);
    }

    public void toggleEditOptions() {
        if(buttonContainer.getVisibility() == View.GONE) {
            buttonContainer.setVisibility(View.VISIBLE);
        } else {
            buttonContainer.setVisibility(View.GONE);
        }
    }

    public void collapse() {
        CLVAnimationUtils.collapse(exerciseContainer, 300);
    }
}
