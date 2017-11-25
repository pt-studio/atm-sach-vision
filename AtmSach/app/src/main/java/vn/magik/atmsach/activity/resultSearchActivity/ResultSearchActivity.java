package vn.magik.atmsach.activity.resultSearchActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.common.api.CommonStatusCodes;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.atmsach.R;
import vn.magik.atmsach.adapter.AdapterBookTitle;
import vn.magik.atmsach.bean.AppConstants;
import vn.magik.atmsach.model.BookTitle;

public class ResultSearchActivity extends AppCompatActivity implements IResultSearch.IView {

    private static final String EXTRA_RESULT_SEARCH = "result_search";
    private AdapterBookTitle mAdapterBookTitle;
    private BookTitle.BookTitleResponse mBookTitleResponse;

    @BindView(R.id.recycler_result_search)
    RecyclerView recyclerViewResultSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        ButterKnife.bind(this);
        this.mBookTitleResponse = (BookTitle.BookTitleResponse)getIntent().getSerializableExtra(EXTRA_RESULT_SEARCH);
        initView();
    }

    private void initView() {
        mAdapterBookTitle = new AdapterBookTitle(this, mBookTitleResponse, this);
        LinearLayoutManager linearLayoutManagerWait = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewResultSearch.setLayoutManager(linearLayoutManagerWait);
        recyclerViewResultSearch.setHasFixedSize(true);
        recyclerViewResultSearch.setAdapter(mAdapterBookTitle);
    }

    public static Intent launchActivity(Context context, BookTitle.BookTitleResponse bookTitleResponse) {
        Intent intent = new Intent(context, ResultSearchActivity.class);
        intent.putExtra(EXTRA_RESULT_SEARCH, bookTitleResponse);
        return intent;
    }

    @Override
    public void setDataResult(BookTitle bookTitle) {
        Intent intent = new Intent();
        intent.putExtra(AppConstants.EXTRA_REQUEST.EXTRA_BOOK_RESULT, bookTitle);
        setResult(CommonStatusCodes.SUCCESS, intent);
        finish();
    }
}
