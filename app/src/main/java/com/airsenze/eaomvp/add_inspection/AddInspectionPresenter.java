package com.airsenze.eaomvp.add_inspection;

import android.content.Intent;

import com.airsenze.eaomvp.Constants;
import com.airsenze.eaomvp.models.Inspection;
import com.airsenze.eaomvp.models.Project;
import com.airsenze.eaomvp.utils.TimeUtils;

import java.util.Calendar;

import io.realm.Realm;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

class AddInspectionPresenter implements AddInspectionContract.Presenter {

    private AddInspectionContract.View view;
    private Realm realm;

    AddInspectionPresenter(AddInspectionContract.View view, Realm realm) {
        this.view = view;
        this.realm = realm;
    }

    @Override
    public void init() {
        view.setUpToolbar();
    }

    @Override
    public void close() {
        realm.close();
    }

    @Override
    public void activityResult(int requestCode, Intent data) {
        if(requestCode == Constants.REQUEST_PROJECT && data != null && data.hasExtra(Constants.PROJECT)) {
            Project project = data.getParcelableExtra(Constants.PROJECT);
            view.showSelectedProject(project);
        }
    }

    @Override
    public void createInspection(Project project, String title, String subText, String number, Calendar startCalendar, Calendar endCalendar) {
        if(validInspectionData(project, title, subText, number, startCalendar, endCalendar)) {

            final Inspection inspection = new Inspection();
            inspection.setProject(project);
            inspection.setTitle(title);
            inspection.setSubText(subText);
            inspection.setNumber(number);
            inspection.setStartDate(TimeUtils.getDateString(startCalendar.getTime()));
            inspection.setStartLong(startCalendar.getTimeInMillis());
            inspection.setEndDate(TimeUtils.getDateString(endCalendar.getTime()));
            inspection.setEndLong(endCalendar.getTimeInMillis());
            inspection.setUploaded(false);

            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.beginTransaction();
                    realm.copyToRealm(inspection);
                    realm.commitTransaction();
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    view.showCreated();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    view.showToast(error.getMessage());
                }
            });
        }
    }

    @Override
    public boolean validInspectionData(Project project, String title, String subText, String number, Calendar startCalendar, Calendar endCalendar) {

        if(project == null) {
            view.showToast("Please select a project");
            return false;
        }

        if(title == null || title.equals("")) {
            view.showToast("Please enter a title");
            return false;
        }

        if(subText == null || subText.equals("")) {
            view.showToast("Please enter a sub text");
            return false;
        }

        if(number == null || number.equals("")) {
            view.showToast("Please enter a number");
            return false;
        }

        if(startCalendar == null) {
            view.showToast("Please pick a start date");
            return false;
        }

        if(endCalendar == null) {
            view.showToast("Please pick a end date");
            return false;
        }

        return true;
    }
}
