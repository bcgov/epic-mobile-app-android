package com.airsenze.eaomvp.main;

import com.airsenze.eaomvp.Constants;
import com.airsenze.eaomvp.models.Inspection;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

/**
 * Created by Aidan Laing on 2017-03-24.
 *
 */

class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private Realm realm;
    private RealmAsyncTask inProgressTask;
    private RealmAsyncTask uploadedTask;

    MainPresenter(MainContract.View view, Realm realm) {
        this.view = view;
    }

    @Override
    public void init() {
        view.setUpToolbar();
        view.setUpTabs();
        view.setUpRecyclerView();
        getInProgress();
    }

    @Override
    public void close() {
        if(realm != null) {
            realm.close();
        }

        if(inProgressTask != null && !inProgressTask.isCancelled()) {
            inProgressTask.cancel();
        }

        if(uploadedTask != null && !uploadedTask.isCancelled()) {
            uploadedTask.cancel();
        }
    }

    @Override
    public void tabSelected(int pos) {

        switch (pos) {

            case Constants.IN_PROGRESS_POS:
                getInProgress();
                break;

            case Constants.UPLOADED_POS:
                getUploaded();
                break;

        }
    }

    @Override
    public void getInProgress() {
        inProgressTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Inspection> inspections = realm.where(Inspection.class).equalTo("uploaded", false).findAll();
                view.showInspections(inspections);
            }
        });
    }

    @Override
    public void getUploaded() {

    }
}
