package com.pro.admin.atssoft.APIResult;

import android.content.Context;
import android.content.Intent;

import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.MainActivity;
import com.pro.admin.atssoft.R;
import com.pro.admin.atssoft.SessionManager;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import model.CategoryClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CF_Category {

    public static class GetAll
    {
        @SerializedName("token")
        public String token;

        public GetAll(String token) {
            this.token = token;
        }
    }


    public static CategoryClass createAreaEmpty(Context context)
    {
        CategoryClass area=new CategoryClass();
        area.setName(context.getString( R.string.area_empty_name));
        area.setID(0);
        return area;
    }

    public static void getArea(final Context context, String mToken)
    {
        try{
        APIInterface  mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
        Call<Object> callArea = mApiInterface.doGetArea(new CF_Category.GetAll(mToken));
        callArea.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {


                DataResult result = new DataResult();
                DataResult.Data data = result.getDataResult(response);

                ArrayList<CategoryClass> mLstArea=new ArrayList<CategoryClass>();
                if (data.Status.contains("OK")) {
                    //Tạo khu vực cho các bàn k có khu vực
                    mLstArea = new ArrayList<CategoryClass>();
                    //mLstArea.add(createAreaEmpty(context));
                    for (ArrayList<String> item : data.Data) {
                        CategoryClass area = new CategoryClass();
                        area.setName(item.get(0));
                        area.setID(Integer.parseInt(item.get(1)));
                        mLstArea.add(area);
                    }
                    Common._LIST_AREA=mLstArea;
                    if(Common._LIST_AREA_SELECTED == null || Common._LIST_AREA_SELECTED.size()==0)
                        Common._LIST_AREA_SELECTED=mLstArea;
                }

                else {
                    if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                        Common.showFailedToast(context, data.Message);
                    }
                    else
                    {
                        Common.errorWhenNotConnected(context);
                    }
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Common.errorWhenNotConnected(context);
                call.cancel();
            }
        });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }

    public static void getBranch(final Context context, String mToken)
    {
        try{
            APIInterface  mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetBranch(new CF_Category.GetAll(mToken));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);

                    ArrayList<CategoryClass> mLstArea=new ArrayList<CategoryClass>();
                    if (data.Status.contains("OK")) {
                        mLstArea = new ArrayList<CategoryClass>();
                        //mLstArea.add(createAreaEmpty(context));
                        for (ArrayList<String> item : data.Data) {
                            CategoryClass branch = new CategoryClass();
                            branch.setName(item.get(0));
                            branch.setID(Integer.parseInt(item.get(1)));
                            branch.setAddress(item.get(3));
                            branch.setPhone(item.get(4));
                            mLstArea.add(branch);
                        }
                        Common._LIST_BRANCH=mLstArea;




                        Intent mIntent = new Intent(context, MainActivity.class);
                        context.startActivity(mIntent);

                    }

                    else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        }
                        else
                        {
                            Common.errorWhenNotConnected(context);
                        }
                    }

                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }

}
