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

    public void save(T object) {
        realm.beginTransaction();

        T savedObject = getSavedRealmObject(object);
        if(savedObject != null) {
            updateValues(object, savedObject);
        } else {
            realm.copyToRealm(object);
        }

        realm.commitTransaction();
    }

    protected abstract T getSavedRealmObject(T object);

    protected abstract void updateValues(T updatedObject, T toSave);
}
