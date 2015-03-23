package com.terhands.fitracker.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.Workout;

import io.realm.Realm;

public class PastWorkoutsActivity extends ActionBarActivity {

    private PastWorkoutsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView list = (ListView) findViewById(R.id.al_list);
        adapter = new PastWorkoutsAdapter(this, Realm.getInstance(this).allObjects(Workout.class));
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PastWorkoutsActivity.this, WorkoutDetailsActivity.class);
                intent.putExtra(WorkoutDetailsActivity.EXTRA_WORKOUT_DATE, adapter.getItem(position).getExerciseDate());
                startActivity(intent);
            }
        });
    }
}
