package com.airsenze.eaomvp.add_inspection;

import android.content.Intent;

import com.airsenze.eaomvp.BasePresenter;
import com.airsenze.eaomvp.BaseView;
import com.airsenze.eaomvp.models.Project;

import java.util.Calendar;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

interface AddInspectionContract {

    interface View extends BaseView {
        void setUpToolbar();
        void showSelectedProject(Project project);
        void showCreated();
    }

    interface Presenter extends BasePresenter {
        void activityResult(int requestCode, Intent data);
        void createInspection(Project project, String title, String subText, String number, Calendar startCalendar, Calendar endCalendar);
        boolean validInspectionData(Project project, String title, String subText, String number, Calendar startCalendar, Calendar endCalendar);
    }
}
