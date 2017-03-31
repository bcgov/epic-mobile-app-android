package com.airsenze.eaomvp;

import android.app.Application;

import com.airsenze.eaomvp.utils.ToastUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Injection.initialize(this);
        ToastUtils.initialize(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("test.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
