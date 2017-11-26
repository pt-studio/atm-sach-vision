package vn.magik.atmsach.activity.searchActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.squareup.picasso.Picasso;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.atmsach.R;
import vn.magik.atmsach.activity.managerScanActivity.ManagerScanActivity;
import vn.magik.atmsach.activity.resultSearchActivity.ResultSearchActivity;
import vn.magik.atmsach.bean.AppConstants;
import vn.magik.atmsach.model.BookTitle;
import vn.magik.atmsach.model.ScanObject;
import vn.magik.atmsach.realm.scanObject.ScanObjectRepository;
import vn.magik.atmsach.util.BitmapTransform;
import vn.magik.atmsach.util.CompareImage;
import vn.magik.atmsach.util.Utils;

public class SearchActivity extends AppCompatActivity implements ISearch.IView {

    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 300;
    private static final String EXTRA_SEARCH = "extra_search_activity";
    private static final String EXTRA_SCAN_OBJECT = "extra_search_scan_object";
    private static final int REQUEST_CODE_FOR_SEARCH_RESULT = 1;

    private SearchPresenter mPresenter;
    private ProgressDialog progressDialog;
    private String sku = "";
    private ScanObject scanObject = new ScanObject();

    private boolean status = false;

    @BindView(R.id.image_search_book)
    ImageView imageBook;
    @BindView(R.id.text_search_name_book)
    TextView textNameBook;
    @BindView(R.id.text_search_author_book)
    TextView textAuthorBook;
    @BindView(R.id.text_search_price_book)
    TextView textPriceBook;
    @BindView(R.id.text_search_describe)
    TextView textDescription;
    @BindView(R.id.text_search_info)
    TextView textInfo;
    @BindView(R.id.linear_content)
    LinearLayout linearLayoutContent;
    @BindView(R.id.text_search_status_damage)
    TextView textViewStatus;


    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i("OPENCV", "OpenCV loaded successfully");

//                    String a = "/storage/emulated/0/Pictures/myDirectoryName/myPhotoName_20171126065431.jpeg";
//                    String b = "/storage/emulated/0/Pictures/myDirectoryName/myPhotoName_20171126065327.jpeg";
//
//                    Bitmap bitmapA = BitmapFactory.decodeFile(a);
//                    Bitmap bitmapB = BitmapFactory.decodeFile(b);
//
//
//                    int result = CompareImage.getInst(ManagerScanActivity.this).run(bitmapA, bitmapB);
//                    Log.d("RESULT_", result +"");

                    setStatus(true);
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };


    private void setStatus(boolean status) {
        this.status = status;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        sku = (String) getIntent().getStringExtra(EXTRA_SEARCH);
        scanObject = (ScanObject) getIntent().getSerializableExtra(EXTRA_SCAN_OBJECT);
        mPresenter = new SearchPresenter(this, this);
        searchInfoBook();

    }

    private void searchInfoBook() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(this.getString(R.string.text_dialog_wait));
        progressDialog.setMessage(this.getString(R.string.progress_wait));
        progressDialog.show();
        mPresenter.getModel().searchInfo(sku);
    }

    public static Intent launchActivity(Context context, String sku, ScanObject scanObject) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_SEARCH, sku);
        intent.putExtra(EXTRA_SCAN_OBJECT, scanObject);
        return intent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOR_SEARCH_RESULT) {
            if (resultCode == CommonStatusCodes.SUCCESS && data != null) {
                BookTitle bookTitle = (BookTitle) data.getSerializableExtra(AppConstants.EXTRA_REQUEST.EXTRA_BOOK_RESULT);
                updateView(bookTitle);
                // TODO  xử lý bookTitle chọn
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void updateData(BookTitle.BookTitleResponse bookTitleResponse) {

        if (bookTitleResponse.getBookTitleList().size() == 0) {
            linearLayoutContent.setVisibility(View.GONE);
            Toast.makeText(this, "Không tìm thấy", Toast.LENGTH_SHORT).show();

        } else if (bookTitleResponse.getBookTitleList().size() == 1) {
            updateView(bookTitleResponse.getBookTitleList().get(0));
        } else {
            startActivityForResult(ResultSearchActivity.launchActivity(this, bookTitleResponse), REQUEST_CODE_FOR_SEARCH_RESULT);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this,
                mLoaderCallback);
    }

    @Override
    public void noticeError(int errorCode) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (errorCode == 0) {
            Toast.makeText(this, "Vui lòng kết nối mạng internet", Toast.LENGTH_SHORT).show();
        } else if (errorCode == 1) {
            Toast.makeText(this, "Không xác nhận được mã sku", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateView(BookTitle bookTitle) {
        String imageA = scanObject.getImageFront();
        String imageB = bookTitle.getLogo();

        if (this.status) {
            int result = CompareImage.getInst(this).run(BitmapFactory.decodeFile(imageA), BitmapFactory.decodeFile(imageB));

            switch (result) {
                case 0:
                    textViewStatus.setText("Độ mới: 100%");
                    break;
                case 1:
                    textViewStatus.setText("Độ mới: 94%");
                    break;

                case 2:
                    textViewStatus.setText("Độ mới: 50%");
                    break;

                case 3:
                    textViewStatus.setText("Không xác định được");
                    break;
            }

        }


        scanObject.setNameBook(bookTitle.getName());
        ScanObjectRepository.getIns().saveScanDraft(scanObject);
        int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));
        linearLayoutContent.setVisibility(View.VISIBLE);
        Picasso.with(imageBook.getContext())
                .load(bookTitle.getLogo())
                .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                .skipMemoryCache()
                .fit()
                .into(imageBook);
        textNameBook.setText(bookTitle.getName().toString());
        textAuthorBook.setText(bookTitle.getAuthor().toString());
        textPriceBook.setText(Utils.formatPrice(bookTitle.getListPrice()));
        textDescription.setText(Html.fromHtml(bookTitle.getDescription()));
        textInfo.setText((Html.fromHtml(bookTitle.getInfo())));
    }
}
