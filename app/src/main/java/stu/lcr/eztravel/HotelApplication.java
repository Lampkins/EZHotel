package stu.lcr.eztravel;

import android.app.Application;

import stu.lcr.eztravel.entity.UserInfo;

public class HotelApplication extends Application {

    private UserInfo userInfo = null;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
