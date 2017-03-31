package com.airsenze.eaomvp.models;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by Aidan Laing on 2017-03-13.
 *
 */

public class Inspection extends RealmObject {

    private Project project;
    private String title;
    private String subText;
    private String number;
    private String startDate;
    private long startLong;
    private String endDate;
    private long endLong;

    @Index
    private boolean uploaded;

    public Inspection() {}

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getStartLong() {
        return startLong;
    }

    public void setStartLong(long startLong) {
        this.startLong = startLong;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getEndLong() {
        return endLong;
    }

    public void setEndLong(long endLong) {
        this.endLong = endLong;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }
}
