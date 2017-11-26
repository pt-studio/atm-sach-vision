package vn.magik.atmsach.activity.managerScanActivity;

import android.content.Context;

import java.util.List;

import vn.magik.atmsach.model.ScanObject;
import vn.magik.atmsach.realm.scanObject.ScanObjectRepository;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class ManagerScanModel {
    private Context mContext;
    private IManagerScan.IPresenter mPresenter;

    public ManagerScanModel(Context mContext, IManagerScan.IPresenter mPresenter) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
    }
    public List<ScanObject> getAllScan(){
        return ScanObjectRepository.getIns().getAllScan();
    }
}
