package com.airsenze.eaomvp.main;

import com.airsenze.eaomvp.BasePresenter;
import com.airsenze.eaomvp.BaseView;
import com.airsenze.eaomvp.models.Inspection;

import java.util.List;

/**
 * Created by Aidan Laing on 2017-03-24.
 *
 */

interface MainContract {

    interface View extends BaseView {
        void setUpToolbar();
        void setUpTabs();
        void setUpRecyclerView();
        void showInspections(List<Inspection> inspections);
    }

    interface Presenter extends BasePresenter {
        void tabSelected(int pos);
        void getInProgress();
        void getUploaded();
    }

}
