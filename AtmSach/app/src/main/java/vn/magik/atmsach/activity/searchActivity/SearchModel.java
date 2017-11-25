package vn.magik.atmsach.activity.searchActivity;

import android.content.Context;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import vn.magik.atmsach.bean.AppConstants;
import vn.magik.atmsach.bean.BookApplication;
import vn.magik.atmsach.model.BookTitle;
import vn.magik.atmsach.retrofit.DataResponse;
import vn.magik.atmsach.retrofit.DataServices;
import vn.magik.atmsach.util.Utils;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class SearchModel {
    private Context mContext;
    private ISearch.IPresenter mPresenter;
    private vn.magik.atmsach.bean.BookApplication mApplication;
    private Disposable mDisposable;
    private DataServices mServices;

    public SearchModel(Context mContext, ISearch.IPresenter mPresenter) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
        mApplication = BookApplication.create(mContext);
        mServices = mApplication.getDataServices();
    }

    public void searchInfo(String key){
        if (Utils.checkConnectNetwork(mContext)) {
            mDisposable = mServices.getInfoBook(key)
                    .subscribeOn(mApplication.getScheduler())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<DataResponse<BookTitle.BookTitleResponse>>() {
                        @Override
                        public void accept(@NonNull DataResponse<BookTitle.BookTitleResponse> response) throws Exception {
                            //TODO xử lý sự kiện khi load hoàn thành
                            int errorCode = response.getErrorCode();
                            //lưu lại thông tin user
                            if(response.getData()!=null && errorCode== AppConstants.ERROR_CODE_SERVER.SUCCESS){
                                BookTitle.BookTitleResponse bookTitleResponse = response.getData();
                                mPresenter.updateData(bookTitleResponse);
                            } else {
                                mPresenter.updateData(null);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            //TODO xử lý khi bị lỗi
                            Log.d("Error:","err");
                            mPresenter.noticeError(1);
                        }
                    });
        } else {
            //TODO xử lý khi không có kết nối internet
            mPresenter.noticeError(0);
        }
    }
}
