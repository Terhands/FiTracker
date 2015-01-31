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

import io.realm.RealmList;

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

    public void initState(Exercise exercise) {
        if(exercise != null) {
            name.setText(exercise.getName());
            initPropertyStates(exercise);
        }
    }

    public String getExerciseName() {
        return name.getText().toString();
    }

    public RealmList<TrackingProperty> getSelectedProperties() {
        RealmList<TrackingProperty> selected = new RealmList<>();

        for(CheckBox propertySelector : trackedProperties) {
            if(propertySelector.isChecked()) {

                String name = propertySelector.getText().toString();
                TrackingProperty.Property p = TrackingProperty.Property.getPropertyByName(name);

                TrackingProperty tp = new TrackingProperty();
                tp.setName(name);
                tp.setUnit(p.unit);

                selected.add(tp);
            }
        }
        return selected;
    }

    private void addPropertyViews(Context context) {
        for(TrackingProperty.Property property : TrackingProperty.Property.values()) {
            CheckBox selector = (CheckBox) LayoutInflater.from(context).inflate(R.layout.property_selector, null, false);
            selector.setText(property.toString());
            trackedProperties.add(selector);
            propertiesContainer.addView(selector);
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
