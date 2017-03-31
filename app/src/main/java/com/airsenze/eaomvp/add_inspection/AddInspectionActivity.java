package com.airsenze.eaomvp.add_inspection;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.airsenze.eaomvp.Constants;
import com.airsenze.eaomvp.R;
import com.airsenze.eaomvp.models.Project;
import com.airsenze.eaomvp.project_list.ProjectListActivity;
import com.airsenze.eaomvp.utils.TimeUtils;
import com.airsenze.eaomvp.utils.ToastUtils;
import com.airsenze.eaomvp.ui.toolbar.Toolbar;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class AddInspectionActivity extends AppCompatActivity implements AddInspectionContract.View {

    private AddInspectionContract.Presenter presenter;
    private Project selectedProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inspection);
        ButterKnife.bind(this);

        presenter = new AddInspectionPresenter(this, Realm.getDefaultInstance());
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(startPickerDialog != null && startPickerDialog.isShowing()) {
            startPickerDialog.dismiss();
        }

        if(endPickerDialog != null && endPickerDialog.isShowing()) {
            endPickerDialog.dismiss();
        }

        if(presenter != null) {
            presenter.close();
        }
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            presenter.activityResult(requestCode, data);
        }
    }

    //VIEWS
    @BindView(R.id.add_inspection_title_et)
    EditText titleEt;

    @BindView(R.id.add_inspection_sub_text_et)
    EditText subTextEt;

    @BindView(R.id.add_inspection_number_et)
    EditText numberEt;

    //TOOLBAR
    @BindView(R.id.add_inspection_toolbar)
    Toolbar toolbar;

    @Override
    public void setUpToolbar() {
        toolbar.setTitle("Add Inspection");
        toolbar.enableBacking(this);
    }

    //PROJECT
    @BindView(R.id.add_inspection_project_tv)
    TextView projectTv;

    @OnClick(R.id.add_inspection_project_tv)
    void projectClicked() {
        Intent goToProjectListIntent = new Intent(this, ProjectListActivity.class);
        startActivityForResult(goToProjectListIntent, Constants.REQUEST_PROJECT);
    }

    @Override
    public void showSelectedProject(Project project) {
        selectedProject = project;
        String name = selectedProject.getName();
        if(name != null) {
            projectTv.setText(name);
        }
    }

    //START DATE
    @BindView(R.id.add_inspection_start_date_tv)
    TextView startDateTv;

    private Calendar startCalendar = Calendar.getInstance();
    private DatePickerDialog startPickerDialog;

    @OnClick(R.id.add_inspection_start_date_tv)
    void startDateClicked() {

        DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startCalendar.set(Calendar.YEAR, year);
                startCalendar.set(Calendar.MONTH, monthOfYear);
                startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                startDateTv.setText(TimeUtils.getDateString(startCalendar.getTime()));
            }

        };

        if(startPickerDialog == null) {
            startPickerDialog = new DatePickerDialog(this, startDateListener,
                    startCalendar.get(Calendar.YEAR),
                    startCalendar.get(Calendar.MONTH),
                    startCalendar.get(Calendar.DAY_OF_MONTH));
        }

        startPickerDialog.show();
    }

    //END DATE
    @BindView(R.id.add_inspection_end_date_tv)
    TextView endDateTv;

    private Calendar endCalendar = Calendar.getInstance();
    private DatePickerDialog endPickerDialog;

    @OnClick(R.id.add_inspection_end_date_tv)
    void endDateClicked() {

        DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                endCalendar.set(Calendar.YEAR, year);
                endCalendar.set(Calendar.MONTH, monthOfYear);
                endCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                endDateTv.setText(TimeUtils.getDateString(endCalendar.getTime()));
            }

        };

        if(endPickerDialog == null) {
            endPickerDialog = new DatePickerDialog(this, endDateListener,
                    endCalendar.get(Calendar.YEAR),
                    endCalendar.get(Calendar.MONTH),
                    endCalendar.get(Calendar.DAY_OF_MONTH));
        }

        endPickerDialog.show();
    }

    //CREATE
    @OnClick(R.id.add_inspection_create_tv)
    void createClicked() {
        String title = titleEt.getText().toString().trim();
        String subText = subTextEt.getText().toString().trim();
        String number = numberEt.getText().toString().trim();
        presenter.createInspection(selectedProject, title, subText, number, startCalendar, endCalendar);
    }

    @Override
    public void showCreated() {
        showToast("Successfully created");
        finish();
    }
}
