package com.terhands.fitracker.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.terhands.fitracker.R;
import com.terhands.fitracker.exercises.list.EditExercisesActivity;
import com.terhands.fitracker.logworkout.LogWorkoutActivity;
import com.terhands.fitracker.prefs.RepositoryPrefs;
import com.terhands.fitracker.startup.StartupTask;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (RepositoryPrefs.isInitializationComplete(this)) {
            setContentView(R.layout.activity_home);
        } else {
            StartupTask initTask = new StartupTask(this, initListener);
            initTask.execute();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ah_profile:
                break;
            case R.id.ah_exercises:
                startActivity(new Intent(this, EditExercisesActivity.class));
                break;
            case R.id.ah_workout:
                startActivity(new Intent(this, LogWorkoutActivity.class));
                break;
            case R.id.ah_history:
                break;
            case R.id.ah_progress:
                break;
        }
    }

    private StartupTask.AppInitListener initListener = new StartupTask.AppInitListener() {
        @Override
        public void onInitComplete() {
            RepositoryPrefs.setInitializationComplete(HomeActivity.this, true);
            setContentView(R.layout.activity_home);
        }

        @Override
        public void onInitError() {
            Toast.makeText(HomeActivity.this, getString(R.string.init_error), Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}