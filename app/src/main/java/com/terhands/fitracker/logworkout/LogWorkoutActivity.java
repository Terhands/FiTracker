package com.terhands.fitracker.logworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.terhands.fitracker.R;
import com.terhands.fitracker.exercises.list.SelectExerciseActivity;
import com.terhands.fitracker.logworkout.exercisedialog.ExerciseDialogBuilder;
import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.models.Workout;
import com.terhands.fitracker.models.WorkoutExercise;
import com.terhands.fitracker.repository.ExerciseRepo;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;

public class LogWorkoutActivity extends ActionBarActivity {

    private static final int CODE_REQUEST_EXERCISE = 1000;
    private Workout workout;
    private ExerciseRepo exerciseRepo;
    private ListView exerciseList;
    private WorkoutExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_workout);

        exerciseRepo = new ExerciseRepo(this);

        workout = new Workout();
        workout.setExerciseDate(new Date());

        initActionBar();
        initViews();
    }

    private void initActionBar() {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(true);
        actionbar.setTitle(workout.getFormattedDate());
    }

    private void initViews() {
        findViewById(R.id.alw_add).setOnClickListener(onLogExerciseClicked);
        adapter = new WorkoutExerciseAdapter(this, null);
        exerciseList = (ListView) findViewById(R.id.alw_exercise_list);
        exerciseList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.ms_save:
                saveWorkout();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveWorkout() {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
//        TODO figure the fuck out how to save this shit.
//        Workout savedWorkout = realm.copyToRealm(workout);
//        for(WorkoutExercise exercise : workout.getExercises()) {
//            RealmList<PropertyValue> values = new RealmList<>();
//            values.addAll(realm.copyToRealm(exercise.getValues()));
//            exercise = realm.copyToRealm(exercise);
//            exercise.setValues(values);
//            savedWorkout.getExercises().add(exercise);
//        }
        realm.commitTransaction();
    }

    private View.OnClickListener onLogExerciseClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LogWorkoutActivity.this, SelectExerciseActivity.class);
            startActivityForResult(intent, CODE_REQUEST_EXERCISE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODE_REQUEST_EXERCISE && resultCode == RESULT_OK) {
            String exerciseName = data.getStringExtra(SelectExerciseActivity.EXTRA_EXERCISE_NAME);
            Exercise exercise = exerciseRepo.getByName(exerciseName);

            WorkoutExercise workoutExercise = new WorkoutExercise();
            workoutExercise.setExercise(exercise);

            addWorkoutExercise(workoutExercise);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addWorkoutExercise(WorkoutExercise workoutExercise) {

        ExerciseDialogBuilder builder = new ExerciseDialogBuilder(this, onSaveExercise);
        builder.showWorkoutExerciseDialog(workoutExercise);
    }

    private ExerciseDialogBuilder.ExerciseDialogCallback onSaveExercise = new ExerciseDialogBuilder.ExerciseDialogCallback() {
        @Override
        public void onSave(final WorkoutExercise workoutExercise) {
            if(workout.getExercises() == null) {
                workout.setExercises(new RealmList<WorkoutExercise>());
            }
            workout.getExercises().add(workoutExercise);
            adapter.addWorkoutExercise(workoutExercise);
        }
    };
}
