package rs.raf.projekatjun.dejan_kulic_10619rn.application;

import android.app.Application;

import timber.log.Timber;


public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
