package com.airsenze.eaomvp.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

public class Project extends RealmObject implements Parcelable {

    private String _id;
    private String name;
    private String code;
    private String status;
    private String description;
    private double lat;
    private double lon;
    private String region;
    private String type;
    private CurrentPhase currentPhase;

    public Project() {}

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CurrentPhase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(CurrentPhase currentPhase) {
        this.currentPhase = currentPhase;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(name);
        parcel.writeString(code);
        parcel.writeString(status);
        parcel.writeString(description);
        parcel.writeDouble(lat);
        parcel.writeDouble(lon);
        parcel.writeString(region);
        parcel.writeString(type);
        parcel.writeString(currentPhase.get_id());
        parcel.writeString(currentPhase.getName());
    }

    protected Project(Parcel in) {
        _id = in.readString();
        name = in.readString();
        code = in.readString();
        status = in.readString();
        description = in.readString();
        lat = in.readDouble();
        lon = in.readDouble();
        region = in.readString();
        type = in.readString();

        CurrentPhase passedPhase = new CurrentPhase();
        passedPhase.set_id(in.readString());
        passedPhase.setName(in.readString());
        currentPhase = passedPhase;
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
