package com.airsenze.eaomvp.project_list;

import com.airsenze.eaomvp.api.EpicApi;
import com.airsenze.eaomvp.models.Project;
import com.airsenze.eaomvp.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

class ProjectListPresenter implements ProjectListContract.Presenter {

    private ProjectListContract.View view;
    private EpicApi epicApi;
    private List<Project> allProjects;
    private String filterString = "";

    ProjectListPresenter(ProjectListContract.View view, EpicApi epicApi) {
        this.view = view;
        this.epicApi = epicApi;
    }

    @Override
    public void init() {
        view.setUpToolbar();
        view.setUpSearching();
        view.setUpRecyclerView();
        getProjects();
    }

    @Override
    public void close() {

    }

    @Override
    public void getProjects() {

        view.showProgressBar();

        Consumer<List<Project>> projectsConsumer = new Consumer<List<Project>>() {
            @Override
            public void accept(List<Project> projects) throws Exception {
                view.hideProgressBar();
                allProjects = projects;
                filterProjects();
            }
        };

        Observable<List<Project>> projectsObservable = epicApi.getProjects();
        projectsObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(projectsConsumer);
    }

    @Override
    public void setFilterString(String filterString) {
        this.filterString = filterString.toLowerCase().trim();
    }

    @Override
    public void filterProjects() {

        //SHOWS NOTHING IF ALL PROJECTS IS NULL
        if(allProjects == null || allProjects.size() == 0) {
            view.showProjects(new ArrayList<Project>());
            return;
        }

        //SHOWS ALL PROJECTS IF NO FILTER IS APPLIED
        if(filterString == null || filterString.equals("")) {
            view.showProjects(allProjects);
            return;
        }

        //FILTERS PROJECTS BY NAME IF THERE IS A VALID FILTER STRING
        List<Project> filteredList = new ArrayList<>();
        for(int i = 0; i < allProjects.size(); i++) {
            Project currProject = allProjects.get(i);
            String name = currProject.getName();
            if(name != null) {
                if(name.toLowerCase().trim().contains(filterString)) {
                    filteredList.add(currProject);
                }
            }
        }

        //SORT THE LIST BASED ON WHICH ARE MOST LIKE THE FILTER STRING
        Collections.sort(filteredList, new Comparator<Project>() {
            @Override
            public int compare(Project project1, Project project2) {

                String name1 = project1.getName().toLowerCase();
                String name2 = project2.getName().toLowerCase();

                int similarity1 = StringUtils.getSimilarity(name1, filterString);
                int similarity2 = StringUtils.getSimilarity(name2, filterString);

                return similarity2 - similarity1;
            }
        });

        view.showProjects(filteredList);
    }
}
