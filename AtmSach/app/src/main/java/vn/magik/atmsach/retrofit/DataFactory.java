package vn.magik.atmsach.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class DataFactory {
    public static final String BASE_URL = "http://500sach.com/api/";
    public static final String INFO = "book/search";
    public static DataServices create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(DataServices.class);
    }
}
