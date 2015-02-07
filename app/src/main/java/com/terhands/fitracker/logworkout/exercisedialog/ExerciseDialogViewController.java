package com.terhands.fitracker.logworkout.exercisedialog;

import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.TrackingProperty;
import com.terhands.fitracker.models.TrackingProperty.Property;
import com.terhands.fitracker.models.WorkoutExercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDialogViewController {

    private ViewHolder holder;
    private ViewState state;

    public ExerciseDialogViewController(WorkoutExercise exercise, View view) {
        holder = new ViewHolder(view);
        state = new ViewState(exercise);
    }

    public void setPropertyVisibility() {
        for(Pair<Property, View> propertyView : holder.propertyContainers) {
            if(state.isVisible(propertyView.first)) {
                propertyView.second.setVisibility(View.VISIBLE);
            } else {
                propertyView.second.setVisibility(View.GONE);
            }
        }
    }

    protected class ViewState {

        private List<TrackingProperty> visibleProperties;

        public ViewState(WorkoutExercise exercise) {
            visibleProperties = exercise.getExercise().getProperties();
        }

        public boolean isVisible(Property property) {
            for(TrackingProperty trackedProperty : visibleProperties) {
                if(Property.getPropertyByName(trackedProperty.getName()) == property) {
                    return true;
                }
            }
            return false;
        }
    }

    protected class ViewHolder {

        public final List<Pair<Property, View>> propertyContainers;

        public final EditText weight;
        public final EditText reps;
        public final EditText sets;
        public final EditText durationHours;
        public final EditText durationMinutes;
        public final EditText durationSeconds;
        public final EditText distance;

        public ViewHolder(View v) {
            propertyContainers = new ArrayList<>();
            propertyContainers.add(new Pair<>(Property.WEIGHT, v.findViewById(R.id.dswe_weight)));
            propertyContainers.add(new Pair<>(Property.SETS, v.findViewById(R.id.dswe_sets)));
            propertyContainers.add(new Pair<>(Property.REPS, v.findViewById(R.id.dswe_reps)));
            propertyContainers.add(new Pair<>(Property.DURATION, v.findViewById(R.id.dswe_duration)));
            propertyContainers.add(new Pair<>(Property.DISTANCE, v.findViewById(R.id.dswe_distance)));

            weight = (EditText) v.findViewById(R.id.dswe_weight_input);
            reps = (EditText) v.findViewById(R.id.dswe_reps_input);
            sets = (EditText) v.findViewById(R.id.dswe_sets_input);
            durationHours = (EditText) v.findViewById(R.id.dswe_hours_input);
            durationMinutes = (EditText) v.findViewById(R.id.dswe_minutes_input);
            durationSeconds = (EditText) v.findViewById(R.id.dswe_seconds_input);
            distance = (EditText) v.findViewById(R.id.dswe_distance_input);
        }
    }
}
