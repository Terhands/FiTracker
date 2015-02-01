package com.terhands.fitracker.exercises.list.viewholders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.ExerciseCategory;
import com.terhands.fitracker.views.collapsinglist.listchild.ListChildView;
import com.terhands.fitracker.views.collapsinglist.listparent.ListParentView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCategoryViewController {

    private final ListParentView exerciseCategory;
    private final ListChildView exerciseContainer;
    private final List<ExerciseViewController> exerciseViews = new ArrayList<>();

    private final TextView categoryName;
    private final ViewGroup editButtonsContainer;

    private final ImageButton add;
    private final ImageButton edit;
    private final ImageButton delete;

    public ExerciseCategoryViewController(ListParentView exerciseCategory, ListChildView exerciseContainer) {
        this.exerciseCategory = exerciseCategory;
        this.exerciseContainer = exerciseContainer;

        categoryName = (TextView) exerciseCategory.findViewById(R.id.ecb_category_name);
        editButtonsContainer = (ViewGroup) exerciseCategory.findViewById(R.id.ecb_buttons);

        add = (ImageButton) exerciseCategory.findViewById(R.id.ecb_add);
        edit = (ImageButton) exerciseCategory.findViewById(R.id.ecb_edit);
        delete = (ImageButton) exerciseCategory.findViewById(R.id.ecb_delete);
    }

    public void showCategoryName(ExerciseCategory category) {
        categoryName.setText(category.getName());
    }

    public void addExerciseView(ExerciseViewController exercise) {
        exerciseViews.add(exercise);
        exercise.addToContainer(exerciseContainer);
    }

    public void setEditButtonListsners(View.OnClickListener onAdd, View.OnClickListener onEdit, View.OnClickListener onDelete) {
        add.setOnClickListener(onAdd);
        edit.setOnClickListener(onEdit);
        delete.setOnClickListener(onDelete);
    }

    public void setOnLongClickListener(View.OnLongClickListener onCategoryLongClicked) {
        exerciseCategory.setOnLongClickListener(onCategoryLongClicked);
    }

    public void toggleEditButtons() {
        if(editButtonsContainer.getVisibility() == View.INVISIBLE) {
            editButtonsContainer.setVisibility(View.VISIBLE);
        } else {
            editButtonsContainer.setVisibility(View.INVISIBLE);
        }
    }
}
