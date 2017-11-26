package vn.magik.atmsach.realm.scanObject;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import vn.magik.atmsach.model.ScanObject;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class ScanObjectRepository implements IScanObjectRepository {
    private Realm realm;
    private static ScanObjectRepository ins;

    public ScanObjectRepository() {
        this.realm = Realm.getDefaultInstance();

    }

    public static ScanObjectRepository getIns() {
        if (ins == null) {
            ins = new ScanObjectRepository();
        }
        return ins;
    }

    @Override
    public void saveScanDraft(final ScanObject scanObject) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(scanObject);
            }
        });
    }

    @Override
    public List<ScanObject> getAllScan() {
        RealmResults result = realm.where(ScanObject.class)
                .findAll();
        return realm.copyFromRealm(result);
    }

    @Override
    public int getMaxId() {
        int max;
        Number idCurrent = realm.where(ScanObject.class)
                .max("id");
        if (idCurrent == null) {
            max = -1;
        } else {
            max = idCurrent.intValue();
        }
        return max;
    }
}

