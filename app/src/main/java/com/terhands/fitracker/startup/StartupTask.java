package com.terhands.fitracker.startup;

import android.content.Context;
import android.os.AsyncTask;

import com.terhands.fitracker.R;
import com.terhands.fitracker.models.ExerciseCategory;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;

public class StartupTask extends AsyncTask<Void, Void, Boolean> {

    private final Context context;
    private final AppInitListener callback;

    public StartupTask(Context context, AppInitListener callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        InputStream inputStream = null;

        try {
            String exercisesLocation = context.getString(R.string.asset_exercises);
            inputStream = context.getAssets().open(exercisesLocation);

            Realm realm = Realm.getInstance(context);
            realm.beginTransaction();
            realm.createAllFromJson(ExerciseCategory.class, inputStream);
            realm.commitTransaction();

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch(IOException e) { }
            }
        }


        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {

        if(result) {
            callback.onInitComplete();
        } else {
            callback.onInitError();
        }

        super.onPostExecute(result);
    }

    public interface AppInitListener {
        public void onInitComplete();
        public void onInitError();
    }
}
