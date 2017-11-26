package vn.magik.atmsach.activity.splashActivity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.magik.atmsach.R;
import vn.magik.atmsach.activity.managerScanActivity.ManagerScanActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        next();
    }


    private void next() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startActivity(ManagerScanActivity.launchActivity(SplashActivity.this));
                finish();
            }
        }, 3000);
    }
}
