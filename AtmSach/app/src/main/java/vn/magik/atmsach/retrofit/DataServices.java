package vn.magik.atmsach.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.magik.atmsach.model.BookTitle;

/**
 * Created by DucThanh on 11/25/2017.
 */

public interface DataServices {
    @GET(DataFactory.INFO)
    Observable<DataResponse<BookTitle.BookTitleResponse>> getInfoBook(@Query("key") String key);
}
