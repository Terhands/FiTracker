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

    public ExerciseDialogBuilder(Context context) {
        this.context = context;
    }

    public void showWorkoutExerciseDialog(WorkoutExercise exercise, ExerciseDialogCallback callback) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_save_workout_exercise, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(exercise.getExercise().getName());
        builder.setView(view);
        builder.setPositiveButton(R.string.save, callback.onSave(exercise, view));
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
        public DialogInterface.OnClickListener onSave(WorkoutExercise exercise, View dialogView);
    }

}
