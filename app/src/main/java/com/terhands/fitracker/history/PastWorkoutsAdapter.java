package com.terhands.fitracker.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.Workout;

import java.util.List;

public class PastWorkoutsAdapter extends BaseAdapter {

    private Context context;
    private List<Workout> workouts;

    public PastWorkoutsAdapter(Context context, List<Workout> workouts) {
        this.context = context;
        this.workouts = workouts;
    }

    @Override
    public int getCount() {
        return workouts != null ? workouts.size() : 0;
    }

    @Override
    public Workout getItem(int position) {
        return workouts != null && workouts.size() > position ? workouts.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_workout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Workout workout = getItem(position);
        if(workout != null) {
            holder.workoutDate.setText(workout.getFormattedDate());
        }

        return view;
    }

    private class ViewHolder {

        public final TextView workoutDate;

        public ViewHolder(View v) {
            workoutDate = (TextView) v.findViewById(R.id.cw_workout_date);
        }
    }
}
