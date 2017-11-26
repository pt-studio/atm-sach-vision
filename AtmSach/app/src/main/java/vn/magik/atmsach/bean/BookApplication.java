package vn.magik.atmsach.bean;

import android.app.Application;
import android.content.Context;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import vn.magik.atmsach.realm.Module;
import vn.magik.atmsach.retrofit.DataFactory;
import vn.magik.atmsach.retrofit.DataServices;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class BookApplication extends Application {
    private DataServices dataServices;
    private Scheduler scheduler;

    @Override
    public void onCreate() {
        super.onCreate();

        //khởi tạo Realm
        Realm.init(this);
        //tạo 1 config mới cho realm
        // lưu ở /data/user/0/vn.magik.book/files
        RealmConfiguration config = new RealmConfiguration.Builder()
                .modules(new Module())
                .deleteRealmIfMigrationNeeded()
                .name("bookrealm.realm").build();
        //đưa RealmConfiguation tùy chỉnh thành RealmConfiguation mặc định
        Realm.setDefaultConfiguration(config);

    }

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
