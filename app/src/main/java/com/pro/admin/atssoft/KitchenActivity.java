package com.pro.admin.atssoft;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.pro.admin.atssoft.APIResult.CF_Kitchen;
import com.pro.admin.atssoft.APIResult.Common;

import java.util.HashMap;

public class KitchenActivity extends AppCompatActivity {

    GridView mGrdKitchenUnProcess;

    SessionManager mSessionManager;
    String mUserName, mUserToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        mSessionManager = new SessionManager(this);
        if (mSessionManager.getScreenSetting() == 1)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mGrdKitchenUnProcess =(GridView) findViewById(R.id.grdKitchenUnProcess);

        /*
        HashMap<String, String> user = mSessionManager.getUserDetail();
        mUserName = user.get(mSessionManager.EmployeeName);
        mUserToken = user.get(mSessionManager.USER_TOKEN);
        Common._USER_TOKEN = mUserToken;
        */
        //CF_Kitchen.getUnProcess(this,Common._USER_TOKEN,mGrdKitchenUnProcess);

    }
}
