package vn.magik.atmsach.bean;

import android.app.Application;
import android.content.Context;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import vn.magik.atmsach.retrofit.DataFactory;
import vn.magik.atmsach.retrofit.DataServices;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class BookApplication extends Application {
    private DataServices dataServices;
    private Scheduler scheduler;

    private static BookApplication get(Context context) {
        return (BookApplication) context.getApplicationContext();
    }

    public static BookApplication create(Context context) {
        return BookApplication.get(context);
    }

    public DataServices getDataServices() {
        if (dataServices == null) {
            dataServices = DataFactory.create();
        }
        return dataServices;
    }

    public Scheduler getScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }
}
