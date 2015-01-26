package com.terhands.fitracker.exercises;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.terhands.fitracker.R;
import com.terhands.fitracker.views.collapsinglist.CollapsingListView;
import com.terhands.fitracker.views.collapsinglist.listchild.ListChildView;
import com.terhands.fitracker.views.collapsinglist.listparent.ListParentView;

public class ExercisesActivity extends ActionBarActivity {

    private CollapsingListView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        new CollapsingListView((ListParentView)findViewById(R.id.ae_parent), (ListChildView)findViewById(R.id.ae_child), true);
    }
}
