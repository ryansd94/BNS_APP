package com.pro.admin.atssoft.APIResult;

import com.google.gson.annotations.SerializedName;

public class UserLogin {
    @SerializedName("user")
    public String user;
    @SerializedName("pass")
    public String pass;
    @SerializedName("shopcode")
    public String shopcode;
    @SerializedName("loginonmobile")
    public String loginonmobile="true";

    public UserLogin(String user, String pass,String shopcode) {
        this.user = user;
        this.pass = pass;
        this.shopcode=shopcode;

    }
}
