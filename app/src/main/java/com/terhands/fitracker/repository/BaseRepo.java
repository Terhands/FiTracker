package com.terhands.fitracker.repository;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;

public abstract class BaseRepo<T extends RealmObject> {

    protected final Realm realm;

    public BaseRepo(Context context) {
        realm = Realm.getInstance(context);
    }

    public void delete(T realmObject) {
        realm.beginTransaction();
        realmObject.removeFromRealm();
        realm.commitTransaction();
    }

    public void save(String key, T updatedObject) {
        realm.beginTransaction();

        T savedObject = getSavedRealmObject(key);
        if(savedObject != null) {
            updateValues(updatedObject, savedObject);
        } else {
            realm.copyToRealm(updatedObject);
        }

        realm.commitTransaction();
    }

    protected abstract T getSavedRealmObject(String key);

    protected abstract void updateValues(T updatedObject, T toSave);
}
