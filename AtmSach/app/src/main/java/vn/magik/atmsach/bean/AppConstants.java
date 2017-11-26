package vn.magik.atmsach.bean;

/**
 * Created by DucThanh on 11/25/2017.
 */

public interface AppConstants {

    int MAX_WIDTH = 1024;
    int MAX_HEIGHT = 768;

    interface ERROR_CODE_SERVER {
        int SUCCESS = 2000; // thành công
        int USER_PASSWORD = 7000; //sai tên đăng nhập mật  khẩu
        int USER_ACTIVE = 7001; // user chưa active
        int USER_PHONE = 7002; // số điện thoại đã tồn tại
    }

    interface EXTRA_REQUEST {

        String EXTRA_BOOK_RESULT = "extra_book_result";
    }

}
