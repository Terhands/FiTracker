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
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle(getString(R.string.delete_exercise_title).replace("%s", exercise.getName()));
//                builder.setMessage(getString(R.string.delete_exercise_message).replace("%s", exercise.getName()));
//                builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Realm realm = Realm.getInstance(context);
//                        realm.beginTransaction();
//                        exercise.removeFromRealm();
//                        realm.commitTransaction();
//
//                        CLVAnimationUtils.collapse(v, 300);
//                    }
//                });
//
//                builder.show();
                return false;
            }
        };
    }

    @Override
    protected View.OnClickListener onExerciseClick(Exercise exercise) {
        return null;
    }
}
