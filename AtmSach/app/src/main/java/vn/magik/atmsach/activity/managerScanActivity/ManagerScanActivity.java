package vn.magik.atmsach.activity.managerScanActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.atmsach.MainActivity;
import vn.magik.atmsach.R;
import vn.magik.atmsach.adapter.AdapterHistoryScan;
import vn.magik.atmsach.model.ScanObject;
import vn.magik.atmsach.util.CompareImage;

public class ManagerScanActivity extends AppCompatActivity implements IManagerScan.IView {

    private AdapterHistoryScan mAdapterHistoryScan;
    private List<ScanObject> scanObjects = new ArrayList<>();
    private ManagerScanPresenter mPresenter;
    String TAG = "ManagerScanActivity";

    @BindView(R.id.recycler_manager_scan)
    RecyclerView recyclerViewHistoryScan;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_scan);
        ButterKnife.bind(this);
        mPresenter = new ManagerScanPresenter(this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume(){
        initData();
        initView();
        super.onResume();


    }
    public static Intent launchActivity(Context context) {
        Intent intent = new Intent(context, ManagerScanActivity.class);
        return intent;
    }

    private void initData() {
        scanObjects = mPresenter.getModel().getAllScan();
    }

    private void initView() {
        mAdapterHistoryScan = new AdapterHistoryScan(this,scanObjects);
        LinearLayoutManager linearLayoutManagerWait = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewHistoryScan.setLayoutManager(linearLayoutManagerWait);
        recyclerViewHistoryScan.setHasFixedSize(true);
        recyclerViewHistoryScan.setAdapter(mAdapterHistoryScan);
    }
    @OnClick(R.id.floating_button)
    public void onClickNewScan(View view) {
        startActivity(MainActivity.launchActivity(ManagerScanActivity.this,null));
    }
}
