package com.airsenze.eaomvp.models;

import io.realm.RealmObject;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

public class CurrentPhase extends RealmObject {

    private String _id;
    private String name;

    public CurrentPhase() {}

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

}
