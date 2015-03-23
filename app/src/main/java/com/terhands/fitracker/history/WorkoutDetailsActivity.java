package com.terhands.fitracker.history;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.terhands.fitracker.R;
import com.terhands.fitracker.logworkout.WorkoutExerciseAdapter;
import com.terhands.fitracker.models.Workout;

import java.util.Date;

import io.realm.Realm;

public class WorkoutDetailsActivity extends ActionBarActivity {

    protected static final String EXTRA_WORKOUT_DATE = "EXTRA_WORKOUT_DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Date workoutDate = (Date) getIntent().getSerializableExtra(EXTRA_WORKOUT_DATE);
        Workout workout = Realm.getInstance(this).where(Workout.class).equalTo("exerciseDate", workoutDate).findFirst();

        ListView list = (ListView) findViewById(R.id.al_list);
        list.setAdapter(new WorkoutExerciseAdapter(this, workout.getExercises()));
    }
}
