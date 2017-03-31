package com.airsenze.eaomvp.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.airsenze.eaomvp.Constants;
import com.airsenze.eaomvp.R;
import com.airsenze.eaomvp.adapters.InspectionsAdapter;
import com.airsenze.eaomvp.add_inspection.AddInspectionActivity;
import com.airsenze.eaomvp.models.Inspection;
import com.airsenze.eaomvp.utils.ToastUtils;
import com.airsenze.eaomvp.ui.toolbar.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this, Realm.getDefaultInstance());
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
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @Override
    public void setUpToolbar() {
        toolbar.setTitle("EAO Inspections");
    }

    //TABS
    @BindView(R.id.main_tab_layout)
    TabLayout tabLayout;

    @Override
    public void setUpTabs() {

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.in_progress)), Constants.IN_PROGRESS_POS);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.uploaded)), Constants.UPLOADED_POS);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                presenter.tabSelected(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

        });
    }

    //RECYCLER VIEW
    @BindView(R.id.main_rv)
    RecyclerView recyclerView;

    private InspectionsAdapter inspectionsAdapter;

    @Override
    public void setUpRecyclerView() {

        //LAYOUT MANAGER
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        //ADAPTER
        inspectionsAdapter = new InspectionsAdapter(this, new ArrayList<Inspection>());

        //SETTING
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(inspectionsAdapter);
    }

    @Override
    public void showInspections(List<Inspection> inspections) {
        inspectionsAdapter.replaceItems(inspections);
    }

    //ADD NEW INSPECTION
    @OnClick(R.id.main_add_inspection_tv)
    void addInspectionClicked() {
        Intent goToAddInspection = new Intent(this, AddInspectionActivity.class);
        startActivity(goToAddInspection);
    }
}
