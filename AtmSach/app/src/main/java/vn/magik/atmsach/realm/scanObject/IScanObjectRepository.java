package vn.magik.atmsach.realm.scanObject;

import java.util.List;

import vn.magik.atmsach.model.ScanObject;

/**
 * Created by DucThanh on 11/25/2017.
 */

public interface IScanObjectRepository {
    void saveScanDraft(ScanObject scanObject);
    List<ScanObject> getAllScan();
    int getMaxId();
}
