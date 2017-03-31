package com.airsenze.eaomvp.project_list;

import com.airsenze.eaomvp.BasePresenter;
import com.airsenze.eaomvp.BaseView;
import com.airsenze.eaomvp.models.Project;

import java.util.List;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

interface ProjectListContract {

    interface View extends BaseView {
        void setUpToolbar();
        void setUpSearching();
        void setUpRecyclerView();
        void showProjects(List<Project> projects);
        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter extends BasePresenter {
        void getProjects();
        void setFilterString(String filterString);
        void filterProjects();
    }

}
