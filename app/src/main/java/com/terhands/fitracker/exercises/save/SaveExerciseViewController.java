package com.terhands.fitracker.exercises.save;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.models.TrackingProperty;

import java.util.ArrayList;
import java.util.List;

public class SaveExerciseViewController {

    private final EditText name;
    private final ViewGroup propertiesContainer;
    private List<CheckBox> trackedProperties;

    public SaveExerciseViewController(SaveExerciseActivity activity) {
        name = (EditText) activity.findViewById(R.id.ase_name);
        propertiesContainer = (ViewGroup) activity.findViewById(R.id.ase_tracking_properties);
        trackedProperties = new ArrayList<>();

        addPropertyViews(activity);
    }

    private void addPropertyViews(Context context) {
        for(TrackingProperty.Property property : TrackingProperty.Property.values()) {
            CheckBox selector = (CheckBox) LayoutInflater.from(context).inflate(R.layout.property_selector, null, false);
            selector.setText(property.toString());
            trackedProperties.add(selector);
            propertiesContainer.addView(selector);
        }
    }

    public void initState(Exercise exercise) {
        if(exercise != null) {
            name.setText(exercise.getName());
            initPropertyStates(exercise);
        }
    }

    private void initPropertyStates(Exercise exercise) {
        for(CheckBox propertyView : trackedProperties) {
            String propertyName = propertyView.getText().toString();
            TrackingProperty.Property property = TrackingProperty.Property.getPropertyByName(propertyName);

            for(TrackingProperty trackingProperty : exercise.getProperties()) {
                if (trackingProperty.getProperty() == property) {
                    propertyView.setChecked(true);
                }
            }
        }
    }
}
