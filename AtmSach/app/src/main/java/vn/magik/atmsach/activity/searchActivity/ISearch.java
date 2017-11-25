package vn.magik.atmsach.activity.searchActivity;

import vn.magik.atmsach.model.BookTitle;

/**
 * Created by DucThanh on 11/25/2017.
 */

public interface ISearch {
    interface IView{
        void updateData(BookTitle.BookTitleResponse bookTitleResponse);
        void noticeError(int errorCode);
    }
    interface IPresenter {
        void updateData(BookTitle.BookTitleResponse bookTitleResponse);
        void noticeError(int errorCode);
    }
}
