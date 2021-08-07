package com.pro.admin.atssoft.APIResult;

import android.content.Context;

import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import model.ProductGroupClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CF_ProductGroup {

    public static class GetAll
    {
        @SerializedName("token")
        public String token;

        public GetAll(String token) {
            this.token = token;
        }
    }

    public static ProductGroupClass createEmpty(Context context)
    {
        ProductGroupClass item=new ProductGroupClass();
        item.setName(context.getString( R.string.area_empty_name));
        item.setID(0);
        return item;
    }
    public static void getAll(final Context context, String mToken)
    {
        try{
        APIInterface mApiInterface = APIClient.getClient(Common._API_Link).create(APIInterface.class);
        Call<Object> callRoom = mApiInterface.doGetProductGroup(new CF_ProductGroup.GetAll(mToken));
        callRoom.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                DataResult result = new DataResult();
                DataResult.Data data = result.getDataResult(response);

                if (data.Status.contains("OK")) {
                    ArrayList<ProductGroupClass> mLstProductGroup =new ArrayList<ProductGroupClass>();
                    mLstProductGroup.add(createEmpty(context));
                    for (ArrayList<String> item : data.Data) {
                        ProductGroupClass room = new ProductGroupClass();
                        room.setID(Integer.parseInt(item.get(1)));
                        room.setName(item.get(0));
                        room.setDescription(item.get(2));

                        mLstProductGroup.add(room);
                    }
                    Common._PRODUCT_GROUP=mLstProductGroup;
                } else {
                    if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                        Common.showFailedToast(context, data.Message);
                    } else {
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
