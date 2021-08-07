package com.pro.admin.atssoft;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.APIResult.CF_Category;
import com.pro.admin.atssoft.APIResult.CF_Config;
import com.pro.admin.atssoft.APIResult.CF_Product;
import com.pro.admin.atssoft.APIResult.CF_ProductGroup;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.APIResult.DataResult;
import com.pro.admin.atssoft.APIResult.UserLogin;

import java.util.ArrayList;
import java.util.HashMap;

import Control.MyEditText;
import Dialog.SettingDialog;
import info.androidhive.fontawesome.FontTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pro.admin.atssoft.SessionManager.USER_AREA_ID_SELECTED_LIST;
import static com.pro.admin.atssoft.SessionManager.USER_AREA_SELECTED_LIST;
import static com.pro.admin.atssoft.SessionManager.USER_BRANCH_ID_SELECTED_LIST;
import static com.pro.admin.atssoft.SessionManager.USER_BRANCH_SELECTED_LIST;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {


    // UI references.
    private MyEditText mEmailView, mShopCode;
    private MyEditText mPasswordView;
    TextView mTextError;
    TextView mTextRegiter;
    SessionManager mSessionManager;
    Button mBtnLogin;
    CheckBox mRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSessionManager = new SessionManager(this);
        if (mSessionManager.getScreenSetting() == 1) {
            Common._USE_VER=false;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else {
            Common._USE_VER=true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        TextView txtVersion = (TextView) findViewById(R.id.txtVersion);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            txtVersion.setText(pInfo.versionName);
            Common._VERSION_CURRENT = Float.parseFloat(pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Common._PACKAGENAME = getPackageName();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        display.getMetrics(metrics);
        double mWidthPixels = metrics.widthPixels;
        double mHeightPixels = metrics.heightPixels;

        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        }

        double x = Math.pow(mWidthPixels / metrics.xdpi, 2);
        double y = Math.pow(mHeightPixels / metrics.ydpi, 2);


        double screenInches = Math.sqrt(x + y);

        if (screenInches <= 7)
            Common._USE_MOBILE = true;
        else
            Common._USE_MOBILE = false;

        Common._FRAGMENTMANAGER = getSupportFragmentManager();
        Common.setApiLink(new SessionManager(this).getAPILink());
        if (mSessionManager.getAPILink().length() > 0)
            CF_Config.getAll(this);

        // Set up the login form.
        mEmailView = (MyEditText) findViewById(R.id.email);
        mShopCode = (MyEditText) findViewById(R.id.shopcode);
        mPasswordView = (MyEditText) findViewById(R.id.password);
        FontTextView imgSetting = (FontTextView) findViewById(R.id.imgSetting);

        Common.setButtonHeight(this);
        Common.setParamButtonHeight(this);
        mTextError = (TextView) findViewById(R.id.txtError);
        mTextRegiter = (TextView) findViewById(R.id.email_sign_in_register);

mTextRegiter.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://phanmem.giaiphapantam.com/?type=register"));
        startActivity(browserIntent);
    }
});
        //populateAutoComplete();

        mBtnLogin = (Button) findViewById(R.id.email_sign_in_button);
        mRemember = (CheckBox) findViewById(R.id.ckcRemember);


        mPasswordView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return Common.clearEditText(event, mPasswordView);
            }
        });

        mEmailView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return Common.clearEditText(event, mEmailView);
            }
        });

        mShopCode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return Common.clearEditText(event, mShopCode);
            }
        });


        HashMap<String, String> infoLogin = mSessionManager.getInfoLogin();

        if (Boolean.parseBoolean(infoLogin.get(mSessionManager.USER_REMEMBER))) {
            mEmailView.setText(infoLogin.get(mSessionManager.USER_NAME));
            mShopCode.setText(infoLogin.get(mSessionManager.USER_SHOPCODE));
            mPasswordView.setText(infoLogin.get(mSessionManager.USER_PASSWORD));
            mRemember.setChecked(true);
        } else {
            mShopCode.setText("");
            mEmailView.setText("");
            mPasswordView.setText("");
            mRemember.setChecked(false);
        }

        mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextError.setText("");
                if (Common.checkVersion())
                    return;
                if (!attemptLogin()) {
                    Login();

                }
            }
        });

        imgSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                SettingDialog dialog = SettingDialog.newInstance(mSessionManager.getAPILink());

                dialog.show(fm, null);

            }
        });


        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


    }


    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            //Toast.makeText(LoginActivity.this,"complete",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LoginSuccess(DataResult.Data data) {
        Common.resetValue();
        if (mRemember.isChecked())
            mSessionManager.saveInfo(mEmailView.getText().toString(), mPasswordView.getText().toString(), mRemember.isChecked(), mShopCode.getText().toString());
        else
            mSessionManager.clearInfo();
        String token = data.Data.get(0).get(0).toString();
        String permisssion = data.Data.get(0).get(4).toString();
        String empName = data.Data.get(0).get(1).toString();
        String mainAccount = data.Data.get(0).get(14).toString();
        String shopName = data.Data.get(0).get(5).toString();
        String msg_KhongSuDungTinhNangBep = data.Data.get(0).get(10).toString();
        String printType = data.Data.get(0).get(30).toString();
        String isPayAndPrintBill = data.Data.get(0).get(31).toString();
        String license = data.Data.get(0).get(4).toString();
        String printNote = data.Data.get(0).get(32).toString();
        try {
            Common._PRINTER_IP = Common.formatAPIURL(data.Data.get(0).get(21).toString());
            Common._PRINTER_PORT = Integer.parseInt(data.Data.get(0).get(22).toString());
            Common._SERVICE_PRINT_IP = Common.formatAPIURL(data.Data.get(0).get(23).toString());
            Common._SERVICE_PRINT_PORT = Integer.parseInt(data.Data.get(0).get(24).toString());
        } catch (Exception ex) {
        }
        String[] permissions = permisssion.split("@");
String[] licenses= license.split("@");
        Common._USER_PERMISSION.clear();
        for (String item : permissions) {
            if (item.length() > 0)
                Common._USER_PERMISSION.add(item);
        }

        for (String item : licenses) {
            if (item.length() > 0)
                Common._USER_LINCESES.add(item);
        }

        Common._MSG_KhongSuDungTinhNangBep = Boolean.parseBoolean(msg_KhongSuDungTinhNangBep);


        if (Integer.parseInt(mainAccount) == 1) {
            Common._USER_ADMIN = true;
        }
        else
            Common._USER_ADMIN = false;
        Common._SHOPNAME = shopName;
        Common._USER_TOKEN = token;
        Common._USER_NAME = empName;
        Common._PRINT_NOTE=printNote;
        Common._USER_USERNAME = mEmailView.getText().toString();
        Common._USER_SHOPCODE = mShopCode.getText().toString();
        Common.CannotConnectSystem = false;
        Common._SHOP_PRINTTYPE = Integer.parseInt(printType);
        Common._SHOP_PayAndPrintBill = Boolean.parseBoolean(isPayAndPrintBill);
        //CF_Area.getArea(this, shopIndex);
        //CF_Room.getArea(this,data.Data.get(0).get(0).toString());

        //if (Common._USER_ORDER || Common._USER_ADMIN) {

        Common._LIST_AREA_SELECTED = mSessionManager.getCategorySelect(USER_AREA_SELECTED_LIST);
        Common._LIST_AREA_SELECTED_ID = mSessionManager.getCategoryIDSelect(USER_AREA_ID_SELECTED_LIST);
        //}
        if (Common._USER_ADMIN) {
            Common._LIST_BRANCH_SELECTED = mSessionManager.getCategorySelect(USER_BRANCH_SELECTED_LIST);
            Common._LIST_BRANCH_SELECTED_ID = mSessionManager.getCategoryIDSelect(USER_BRANCH_ID_SELECTED_LIST);
        }


        CF_ProductGroup.getAll(this, token);
        CF_Product.getAll(this, token);
        CF_Category.getBranch(this, token);
        mSessionManager.createSession(data.Data.get(0).get(1).toString(), token);

    }

    private void Login() {
        try {
            mBtnLogin.setEnabled(false);

            APIInterface mApiInterface = APIClient.getClient(Common._API_Link).create(APIInterface.class);
            UserLogin userLogin = new UserLogin(mEmailView.getText().toString(),
                    mPasswordView.getText().toString(), mShopCode.getText().toString());
            Call<Object> call = mApiInterface.doLogin(userLogin);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);

                    if (data.Message.length() > 0) {
                        mTextError.setText(data.Message);
                    } else {
                        LoginSuccess(data);
                    }
                    mBtnLogin.setEnabled(true);
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    mBtnLogin.setEnabled(true);
                    mTextError.setText(R.string.error_not_connect_api);
                    call.cancel();
                }
            });
        } catch (Exception e) {
            mBtnLogin.setEnabled(true);
            mTextError.setText(R.string.error_not_connect_api);
        }
    }

    private boolean attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mShopCode.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String shopCode = mShopCode.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid email address.
        if (!cancel && TextUtils.isEmpty(shopCode)) {
            mShopCode.setError(getString(R.string.error_field_required));
            focusView = mShopCode;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        if (!cancel && TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        if (focusView != null)
            focusView.requestFocus();

        return cancel;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mSessionManager.getScreenSetting() == 1)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}

