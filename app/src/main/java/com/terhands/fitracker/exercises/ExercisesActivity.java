package com.terhands.fitracker.exercises;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.models.ExerciseCategory;
import com.terhands.fitracker.views.collapsinglist.CollapsingListView;
import com.terhands.fitracker.views.collapsinglist.listchild.ListChildView;
import com.terhands.fitracker.views.collapsinglist.listparent.ListParentView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ExercisesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        buildExercisesView();
    }

    private void buildExercisesView() {

        LinearLayout mainView = (LinearLayout) findViewById(R.id.ae_exercises);

        Realm realm = Realm.getInstance(this);
        RealmResults<ExerciseCategory> categories = realm.where(ExerciseCategory.class)
                                                         .findAllSorted("name");

        for(ExerciseCategory category : categories) {

            ListParentView header = (ListParentView) LayoutInflater.from(this).inflate(R.layout.exercise_category_button, null, false);
            header.setText(category.getName());

            ListChildView container = (ListChildView) LayoutInflater.from(this).inflate(R.layout.exercises_container, null, false);
            for(Exercise exercise : category.getExercises()) {
                Button button = (Button) LayoutInflater.from(this).inflate(R.layout.exercise_button, null, false);
                button.setText(exercise.getName());
                container.addView(button);
            }

            new CollapsingListView(mainView, header, container, false);
        }

    }
}
