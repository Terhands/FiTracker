package com.terhands.fitracker.logworkout.exercisedialog;

import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.PropertyValue;
import com.terhands.fitracker.models.TrackingProperty;
import com.terhands.fitracker.models.TrackingProperty.Property;
import com.terhands.fitracker.models.WorkoutExercise;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

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

    public RealmList<PropertyValue> getTrackedProperties() {
        RealmList<PropertyValue> values = new RealmList<>();

        for(TrackingProperty property : state.visibleProperties) {
            PropertyValue value = new PropertyValue();
            value.setTrackedProperty(property);
            value.setValue(holder.getValue(property.getProperty()));
            values.add(value);
        }

        return values;
    }

    protected class ViewState {

        public final List<TrackingProperty> visibleProperties;

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

        public int getValue(Property property) {
            switch(property) {
                case WEIGHT:
                    return convertToValue(weight.getText().toString());
                case REPS:
                    return convertToValue(reps.getText().toString());
                case SETS:
                    return convertToValue(sets.getText().toString());
                case DISTANCE:
                    return convertDistanceToValue(distance.getText().toString());
                case DURATION:
                    String hours = durationHours.getText().toString();
                    String minutes = durationMinutes.getText().toString();
                    String seconds = durationSeconds.getText().toString();
                    return convertDurationToValue(hours, minutes, seconds);
                default:
                    return 0;
            }
        }

        private int convertToValue(String valueString) {
            if(valueString != null && valueString.length() > 0) {
                try {
                    return Integer.parseInt(valueString);
                } catch(NumberFormatException e) { }
            }
            return 0;
        }

        private int convertDistanceToValue(String valueString) {
            if(valueString != null && valueString.length() > 0) {
                try {
                    // TODO move this to a utility - convert to meters from km
                    return (int) Math.ceil(Double.parseDouble(valueString) * 1000);
                } catch(NumberFormatException e) { }
            }
            return 0;
        }

        private int convertDurationToValue(String hours, String minutes, String seconds) {
            return convertToValue(seconds) +
                    (convertToValue(minutes) * 60) +
                    (convertToValue(hours) * 60 * 60);
        }
    }
}
