package com.terhands.fitracker.home;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.terhands.fitracker.R;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ah_profile:
                break;
            case R.id.ah_exercises:
                break;
            case R.id.ah_workout:
                break;
            case R.id.ah_history:
                break;
            case R.id.ah_progress:
                break;
        }
    }
}