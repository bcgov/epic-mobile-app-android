package com.airsenze.eaomvp.project_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.airsenze.eaomvp.Constants;
import com.airsenze.eaomvp.Injection;
import com.airsenze.eaomvp.R;
import com.airsenze.eaomvp.adapters.ProjectsAdapter;
import com.airsenze.eaomvp.models.Project;
import com.airsenze.eaomvp.utils.ToastUtils;
import com.airsenze.eaomvp.ui.toolbar.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectListActivity extends AppCompatActivity implements ProjectListContract.View, ProjectsAdapter.ProjectClickListener {

    private ProjectListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        ButterKnife.bind(this);

        presenter = new ProjectListPresenter(this, Injection.epicApi);
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) {
            presenter.close();
        }
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(message);
    }

    //TOOLBAR
    @BindView(R.id.project_list_toolbar)
    Toolbar toolbar;

    @Override
    public void setUpToolbar() {
        toolbar.setTitle("Project List");
        toolbar.enableBacking(this);
    }

    //SEARCHING
    @BindView(R.id.project_list_search_et)
    EditText searchEt;

    @Override
    public void setUpSearching() {

        searchEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable editable) {
                String filterString = editable.toString().trim();
                presenter.setFilterString(filterString);
                presenter.filterProjects();
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });

    }

    //RECYCLER VIEW
    @BindView(R.id.project_list_rv)
    RecyclerView projectListRv;

    private ProjectsAdapter projectsAdapter;

    @Override
    public void setUpRecyclerView() {

        //LAYOUT MANAGER
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //ADAPTER
        projectsAdapter = new ProjectsAdapter(this, new ArrayList<Project>(), this);

        //SETTING
        projectListRv.setLayoutManager(layoutManager);
        projectListRv.setAdapter(projectsAdapter);
    }

    @Override
    public void projectClicked(Project project) {
        Intent data = new Intent();
        data.putExtra(Constants.PROJECT, project);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void showProjects(List<Project> projects) {
        projectsAdapter.replaceItems(projects);
    }

    //LOADING
    @BindView(R.id.project_list_progress_bar)
    ProgressBar progressBar;

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
