package vn.magik.atmsach;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.magik.atmsach.activity.searchActivity.SearchActivity;
import vn.magik.atmsach.util.BitmapTransform;

public class MainActivity extends AppCompatActivity {

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_ID_PERMISSION = 200;
    private MagicalPermissions magicalPermissions;
    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 80;
    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;
    private MagicalCamera magicalCamera;
    private String LOG_TAG = "scan_barcode";
    private int countImage = 0;
    private String sku = "";

    @BindView(R.id.img_main_img1)
    ImageView imageView1;
    @BindView(R.id.img_main_img2)
    ImageView imageView2;
    @BindView(R.id.img_main_img3)
    ImageView imageView3;
    @BindView(R.id.img_main_img4)
    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        permisionRequest();
    }

    @OnClick(R.id.btn_camera)
    public void buttonCameraPressed(View view) {

        magicalCamera = new MagicalCamera(this, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);
        magicalCamera.takePhoto();

    }

    @OnClick(R.id.btn_search)
    public void buttonSearch(View view) {
        startActivity(SearchActivity.launchActivity(MainActivity.this,sku));
    }

    public void permisionRequest() {

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            isAskPermission();
        }
        magicalPermissions = new MagicalPermissions(this, PERMISSIONS);
    }

    public boolean isAskPermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            List<String> requestPermission = new ArrayList<>();
            for (int i = 0; i < PERMISSIONS.length; i++) {
                int permission = ActivityCompat.checkSelfPermission(this, PERMISSIONS[i]);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    requestPermission.add(PERMISSIONS[i]);
                }
            }
            if (requestPermission.size() > 0) {
                String[] permissions = new String[requestPermission.size()];
                permissions = requestPermission.toArray(permissions);
                this.requestPermissions(permissions, REQUEST_ID_PERMISSION);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "myPhotoName", "myDirectoryName", MagicalCamera.JPEG, true);

        if (path != null) {
            countImage = countImage + 1;
            showImage(path);
            scanBarcodeFromImage(path);
        } else {
            Toast.makeText(MainActivity.this, "Sorry your photo dont write in devide", Toast.LENGTH_SHORT).show();
        }
    }


    //scan barcode from image
    public void scanBarcodeFromImage(String photoPath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .build();
        if(barcodeDetector.isOperational()){
            SparseArray<Barcode> sparseArray = barcodeDetector.detect(frame);
            if(sparseArray != null && sparseArray.size() > 0){
                for (int i = 0; i < sparseArray.size(); i++){
                    if (sku.contains(sparseArray.valueAt(i).rawValue) == false){
                        if (sku.equals("") == false){
                            sku = sku.concat("~");
                        }
                        sku = sku.concat(sparseArray.valueAt(i).rawValue);
                    }
                }
            }else {
                Log.e(LOG_TAG,"SparseArray null or empty");
            }

        }else{
            Log.e(LOG_TAG, "Detector dependencies are not yet downloaded");
        }
    }

    public void showImage(String path){
        Uri uri = Uri.fromFile(new File(path));
        switch(countImage){
            case 1:
                Picasso.with(imageView1.getContext())
                        .load(uri)
                        .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                        .skipMemoryCache()
                        .centerCrop()
                        .fit()
                        .into(imageView1);
                break;
            case 2:
                Picasso.with(imageView2.getContext())
                        .load(uri)
                        .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                        .skipMemoryCache()
                        .centerCrop()
                        .fit()
                        .into(imageView2);
                break;
            case 3:
                Picasso.with(imageView3.getContext())
                        .load(uri)
                        .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                        .skipMemoryCache()
                        .centerCrop()
                        .fit()
                        .into(imageView3);
                break;
            case 4:
                Picasso.with(imageView4.getContext())
                        .load(uri)
                        .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                        .skipMemoryCache()
                        .centerCrop()
                        .fit()
                        .into(imageView4);
                break;
            default:

        }
    }
}
