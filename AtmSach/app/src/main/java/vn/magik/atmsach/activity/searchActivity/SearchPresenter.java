package vn.magik.atmsach.activity.searchActivity;

import android.content.Context;

import vn.magik.atmsach.model.BookTitle;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class SearchPresenter implements ISearch.IPresenter {
    private Context mContext;
    private ISearch.IView mView;
    private SearchModel mModel;

    public SearchPresenter(Context mContext, ISearch.IView mView) {
        this.mContext = mContext;
        this.mView = mView;
        this.mModel = new SearchModel(mContext, this);
    }


    public SearchModel getModel() {
        return this.mModel;
    }

    @Override
    public void updateData(BookTitle.BookTitleResponse bookTitleResponse) {
        mView.updateData(bookTitleResponse);
    }

    @Override
    public void noticeError(int errorCode) {
        mView.noticeError(errorCode);
    }
}
