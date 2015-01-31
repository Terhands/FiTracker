package com.terhands.fitracker.exercises.list.viewholders;

import com.terhands.fitracker.models.ExerciseCategory;
import com.terhands.fitracker.views.collapsinglist.listchild.ListChildView;
import com.terhands.fitracker.views.collapsinglist.listparent.ListParentView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCategoryViewController {

    private final ListParentView exerciseCategory;
    private final ListChildView exerciseContainer;
    private final List<ExerciseViewController> exerciseViews = new ArrayList<>();

    public ExerciseCategoryViewController(ListParentView exerciseCategory, ListChildView exerciseContainer) {
        this.exerciseCategory = exerciseCategory;
        this.exerciseContainer = exerciseContainer;
    }

    public void showCategoryName(ExerciseCategory category) {
        exerciseCategory.setText(category.getName());
    }

    public void addExerciseView(ExerciseViewController exercise) {
        exerciseViews.add(exercise);
        exercise.addToContainer(exerciseContainer);
    }
}
