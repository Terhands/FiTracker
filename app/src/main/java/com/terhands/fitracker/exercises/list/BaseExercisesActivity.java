package com.terhands.fitracker.exercises.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.terhands.fitracker.R;
import com.terhands.fitracker.exercises.list.viewholders.ExerciseCategoryViewController;
import com.terhands.fitracker.exercises.list.viewholders.ExerciseViewController;
import com.terhands.fitracker.exercises.save.SaveExerciseActivity;
import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.models.ExerciseCategory;
import com.terhands.fitracker.repository.ExerciseRepo;
import com.terhands.fitracker.views.collapsinglist.CollapsingListView;
import com.terhands.fitracker.views.collapsinglist.listchild.ListChildView;
import com.terhands.fitracker.views.collapsinglist.listparent.ListParentView;

import io.realm.Realm;
import io.realm.RealmResults;

public abstract class BaseExercisesActivity extends ActionBarActivity {

    private ExerciseRepo exerciseRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        exerciseRepo = new ExerciseRepo(this);
    }

    @Override
    protected void onResume() {
        buildExercisesView();

        super.onResume();
    }

    private void buildExercisesView() {

        LinearLayout mainView = (LinearLayout) findViewById(R.id.ae_exercises);
        mainView.removeAllViewsInLayout();

        Realm realm = Realm.getInstance(this);
        RealmResults<ExerciseCategory> categories = realm.where(ExerciseCategory.class)
                                                         .findAllSorted("name");

        for(ExerciseCategory category : categories) {

            ListParentView header = (ListParentView) LayoutInflater.from(this).inflate(R.layout.exercise_category_button, null, false);
            ListChildView container = (ListChildView) LayoutInflater.from(this).inflate(R.layout.exercises_container, null, false);
            ExerciseCategoryViewController categoryViewController = new ExerciseCategoryViewController(header, container);
            categoryViewController.showCategoryName(category);

            for(Exercise exercise : category.getExercises()) {
                View exerciseView = LayoutInflater.from(this).inflate(R.layout.exercise_button, null, false);
                ExerciseViewController holder = new ExerciseViewController(exerciseView);

                holder.showExerciseName(exercise);
                holder.setTopLevelListeners(onExerciseClick(exercise), onExerciseLongClick(holder));
                holder.setExerciseManagerListeners(onEditClick(exercise), onDeleteClick(exercise, holder));
                categoryViewController.addExerciseView(holder);
            }

            new CollapsingListView(mainView, header, container, false);
        }
    }

    private View.OnClickListener onEditClick(final Exercise exercise) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseExercisesActivity.this, SaveExerciseActivity.class);
                intent.putExtra(SaveExerciseActivity.EXTRA_EXERCISE_NAME, exercise.getName());
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onDeleteClick(final Exercise exercise, final ExerciseViewController controller) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseRepo.delete(exercise);
                controller.collapse();
            }
        };
    }

    protected abstract View.OnLongClickListener onExerciseLongClick(final ExerciseViewController controller);

    protected abstract View.OnClickListener onExerciseClick(final Exercise exercise);
}
