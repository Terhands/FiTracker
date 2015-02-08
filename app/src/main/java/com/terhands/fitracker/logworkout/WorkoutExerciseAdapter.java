package com.terhands.fitracker.logworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.PropertyValue;
import com.terhands.fitracker.models.WorkoutExercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutExerciseAdapter extends BaseAdapter {

    private Context context;
    private List<WorkoutExercise> exercises = new ArrayList<>();

    public WorkoutExerciseAdapter(Context context, List<WorkoutExercise> exercises) {
        this.context = context;
        if(exercises != null) {
            this.exercises.addAll(exercises);
        }
    }

    public void addWorkoutExercise(WorkoutExercise workoutExercise) {
        exercises.add(0, workoutExercise);
        notifyDataSetChanged();
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public WorkoutExercise getItem(int position) {
        return exercises.size() > position ? exercises.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_workout_exercise, null, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        populateCell(holder, position);

        return view;
    }

    private void populateCell(ViewHolder holder, int position) {
        WorkoutExercise exercise = getItem(position);
        holder.name.setText(exercise.getExercise().getName());
        holder.propertiesContainer.removeAllViews();
        for(PropertyValue propertyValue : exercise.getValues()) {
            TextView property = new TextView(context);
            property.setText(propertyValue.getTrackedProperty().getName() + ": " + propertyValue.getDisplayValue());
            holder.propertiesContainer.addView(property);
        }
    }

    private class ViewHolder {

        public final TextView name;
        public final LinearLayout propertiesContainer;

        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.cwe_name);
            propertiesContainer = (LinearLayout) v.findViewById(R.id.cwe_properties_container);
        }

    }
}
