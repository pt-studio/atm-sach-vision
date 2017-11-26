package vn.magik.atmsach.activity.managerScanActivity;

import android.content.Context;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class ManagerScanPresenter implements IManagerScan.IPresenter {
    private IManagerScan.IView mView;
    private ManagerScanModel mModel;

    public ManagerScanPresenter(Context mContext, IManagerScan.IView mView){
        this.mView = mView;
        mModel = new ManagerScanModel(mContext, this);
    }

    public ManagerScanModel getModel(){
        return  this.mModel;
    }
}
