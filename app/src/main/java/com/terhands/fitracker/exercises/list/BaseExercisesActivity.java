package com.terhands.fitracker.exercises.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.terhands.fitracker.R;
import com.terhands.fitracker.exercises.list.viewholders.ExerciseCategoryViewController;
import com.terhands.fitracker.exercises.list.viewholders.ExerciseViewController;
import com.terhands.fitracker.exercises.save.SaveExerciseActivity;
import com.terhands.fitracker.models.Exercise;
import com.terhands.fitracker.models.ExerciseCategory;
import com.terhands.fitracker.repository.ExerciseCategoryRepo;
import com.terhands.fitracker.repository.ExerciseRepo;
import com.terhands.fitracker.views.collapsinglist.CollapsingListView;
import com.terhands.fitracker.views.collapsinglist.listchild.ListChildView;
import com.terhands.fitracker.views.collapsinglist.listparent.ListParentView;

import io.realm.Realm;
import io.realm.RealmResults;

public abstract class BaseExercisesActivity extends ActionBarActivity {

    private ExerciseCategoryRepo exerciseCategoryRepo;
    private ExerciseRepo exerciseRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        exerciseCategoryRepo = new ExerciseCategoryRepo(this);
        exerciseRepo = new ExerciseRepo(this);
    }

    @Override
    protected void onResume() {
        buildExercisesView();

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_exercises_browser, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.meb_create:
                SaveExerciseCategoryDialog saveDialog = new SaveExerciseCategoryDialog(this);
                saveDialog.buildCategoryEditDialog(null).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void buildExercisesView() {

        LinearLayout mainView = (LinearLayout) findViewById(R.id.ae_exercises);
        mainView.removeAllViewsInLayout();

        Realm realm = Realm.getInstance(this);
        RealmResults<ExerciseCategory> categories = realm.where(ExerciseCategory.class)
                                                         .findAllSorted("name");
        for(ExerciseCategory category : categories) {
            buildCollapsingExercisesList(category, mainView);
        }
    }

    private void buildCollapsingExercisesList(ExerciseCategory category, LinearLayout mainView) {
        ListParentView header = (ListParentView) LayoutInflater.from(this).inflate(R.layout.exercise_category_button, null, false);
        ListChildView container = (ListChildView) LayoutInflater.from(this).inflate(R.layout.exercises_container, null, false);

        ExerciseCategoryViewController categoryViewController = new ExerciseCategoryViewController(header, container);
        categoryViewController.showCategoryName(category);
        categoryViewController.setOnLongClickListener(onExerciseCategoryLongClicked(categoryViewController));
        categoryViewController.setEditButtonListsners(onCategoryAddClick(category),
                                                      onCategoryEditClick(category),
                                                      onCategoryDeleteClick(categoryViewController, category));

        for(Exercise exercise : category.getExercises()) {
            buildExerciseView(exercise, categoryViewController);
        }

        new CollapsingListView(mainView, header, container, false);
    }

    private void buildExerciseView(Exercise exercise, ExerciseCategoryViewController controller) {
        View exerciseView = LayoutInflater.from(this).inflate(R.layout.exercise_button, null, false);
        ExerciseViewController holder = new ExerciseViewController(exerciseView);

        holder.showExerciseName(exercise);
        holder.setTopLevelListeners(onExerciseClick(exercise), onExerciseLongClick(holder));
        holder.setExerciseManagerListeners(onExerciseEditClick(exercise), onExerciseDeleteClick(exercise, holder));
        controller.addExerciseView(holder);
    }

    private View.OnClickListener onCategoryAddClick(final ExerciseCategory category) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseExercisesActivity.this, SaveExerciseActivity.class);
                intent.putExtra(SaveExerciseActivity.EXTRA_EXERCISE_CATEGORY_NAME, category.getName());
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onCategoryEditClick(final ExerciseCategory category) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveExerciseCategoryDialog saveDialog = new SaveExerciseCategoryDialog(BaseExercisesActivity.this);
                saveDialog.buildCategoryEditDialog(category).show();
            }
        };
    }

    private View.OnClickListener onCategoryDeleteClick(final ExerciseCategoryViewController controller, final ExerciseCategory category) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseCategoryRepo.delete(category);
                controller.animateOut();
            }
        };
    }

    private View.OnClickListener onExerciseEditClick(final Exercise exercise) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseExercisesActivity.this, SaveExerciseActivity.class);
                intent.putExtra(SaveExerciseActivity.EXTRA_EXERCISE_NAME, exercise.getName());
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onExerciseDeleteClick(final Exercise exercise, final ExerciseViewController controller) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseRepo.delete(exercise);
                controller.collapse();
            }
        };
    }

    private View.OnLongClickListener onExerciseCategoryLongClicked(final ExerciseCategoryViewController controller) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                controller.toggleEditButtons();
                return true;
            }
        };
    }

    protected abstract View.OnLongClickListener onExerciseLongClick(final ExerciseViewController controller);

    protected abstract View.OnClickListener onExerciseClick(final Exercise exercise);
}
