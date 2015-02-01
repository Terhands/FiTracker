package com.terhands.fitracker.exercises.list;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.ExerciseCategory;
import com.terhands.fitracker.repository.ExerciseCategoryRepo;

public class SaveExerciseCategoryDialog {

    private ExerciseCategoryRepo repo;
    private BaseExercisesActivity activity;
    private TextView inputText;

    public SaveExerciseCategoryDialog(BaseExercisesActivity activity) {
        this.activity = activity;

        repo = new ExerciseCategoryRepo(activity);
    }

    public Dialog buildCategoryEditDialog(final ExerciseCategory category) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_save_exercise_category, null);
        inputText = (TextView) dialogView.findViewById(R.id.dsec_category_name);
        if(category != null) {
            inputText.setText(category.getName());
        }

        builder.setView(dialogView);
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String newName = inputText.getText().toString();

                ExerciseCategory toSave = new ExerciseCategory();
                toSave.setName(newName);
                if(category != null) {
                    String oldName = category.getName();
                    repo.save(oldName, toSave);
                } else {
                    repo.save(null, toSave);
                }

                dialog.dismiss();
                activity.onResume();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
