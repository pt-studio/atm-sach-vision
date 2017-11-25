package vn.magik.atmsach.activity.resultSearchActivity;

import vn.magik.atmsach.model.BookTitle;

/**
 * Created by DucThanh on 11/25/2017.
 */

public interface IResultSearch {
    interface IView{
        void setDataResult(BookTitle bookTitle);
    }
}
