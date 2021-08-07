package com.pro.admin.atssoft.APIResult;

import android.content.Context;

import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.R;
import com.pro.admin.atssoft.SessionManager;

import java.util.ArrayList;

import Dialog.UpdateDialog;
import model.ConfigClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CF_Config {


    public static void getAll(final Context context) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetConfig();
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    try {
                        if(response.body()==null)
                            return;;
                        DataResult result = new DataResult();
                        DataResult.Data data = result.getDataResult(response);
                        if (data.Status.contains("OK")) {
                            ConfigClass config = new ConfigClass();
                            for (ArrayList<String> item : data.Data) {

                                config.setAndroidVersion(item.get(0));
                                config.setAndroidURL(item.get(1));
                                config.setIosVersion(item.get(2));
                                config.setIosURL(item.get(3));
                                config.setWinformVersion(item.get(4));
                                config.setWinformURL(item.get(5));
                            }
                            Common._CONFIG = config;
                        }
                        // check version

                        if (Common._CONFIG != null) {
                            try {
                                Common._VERSION_SERVER = Float.parseFloat(Common._CONFIG.getAndroidVersion());
                                Common.checkVersion();

                            } catch (Exception ex) {
                            }
                        }



                    }
                    catch (Exception ex)
                    {}
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    call.cancel();
                }
            });
        }catch (Exception ex)
        {}
    }
}
