package com.terhands.fitracker.logworkout.exercisedialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.WorkoutExercise;

public class ExerciseDialogBuilder {

    private final Context context;
    private ExerciseDialogViewController controller;
    private ExerciseDialogCallback callback;

    public ExerciseDialogBuilder(Context context, ExerciseDialogCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void showWorkoutExerciseDialog(WorkoutExercise exercise) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_save_workout_exercise, null);

        controller = new ExerciseDialogViewController(exercise, view);
        controller.setPropertyVisibility();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(exercise.getExercise().getName());
        builder.setView(view);
        builder.setPositiveButton(R.string.save, onSave(exercise));
        builder.setNegativeButton(R.string.cancel, onCancel);
        builder.show();
    }

    private DialogInterface.OnClickListener onCancel = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

    public interface ExerciseDialogCallback {
        public void onSave(WorkoutExercise exercise);
    }

    private DialogInterface.OnClickListener onSave(final WorkoutExercise exercise) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exercise.setValues(controller.getTrackedProperties());
                callback.onSave(exercise);
            }
        };
    }
}
