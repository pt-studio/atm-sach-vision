package vn.magik.atmsach.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DecimalFormat;

/**
 * Created by DucThanh on 11/25/2017.
 */

public class Utils {

    public static boolean checkConnectNetwork(Context mContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    // Format giá tiền về dạng chuẩn cho dễ nhìn
    public static String formatPrice(double price) {
        String formattedPrice = new DecimalFormat("##,##0 đ").format(price);
        return formattedPrice;
    }
}
