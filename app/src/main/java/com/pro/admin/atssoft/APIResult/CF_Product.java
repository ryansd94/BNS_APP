package com.pro.admin.atssoft.APIResult;

import android.content.Context;

import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Control.MyToast;
import model.ProductClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CF_Product {
    static public ArrayList<ProductClass> mLstProduct = new ArrayList<ProductClass>();
    static public boolean isFinishedLoadData = false;

    public static class GetAll {
        @SerializedName("token")
        public String token;

        @SerializedName("byorder")
        public String byorder = "true";

        public GetAll(String token) {
            this.token = token;
        }
    }

    public static void getAll(final Context context, String mToken) {
        try{
        APIInterface mApiInterface = APIClient.getClient(Common._API_Link).create(APIInterface.class);
        Call<Object> callRoom = mApiInterface.doGetProduct(new CF_Product.GetAll(mToken));
        callRoom.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                DataResult result = new DataResult();
                DataResult.Data data = result.getDataResult(response);

                if (data.Status.contains("OK")) {
                    mLstProduct = new ArrayList<ProductClass>();
                    for (ArrayList<String> item : data.Data) {
                        ProductClass product = new ProductClass();
                        product.setID(Integer.parseInt(item.get(0)));
                        String name = item.get(1);
                        if (name.length() > 50)
                            name = name.substring(0, 50) + "...";
                        product.setName(name);
                        product.setCode(item.get(2));
                        product.setGroupID(Integer.parseInt(item.get(22)));
                        product.setImage(item.get(15));
                        product.setPrice(item.get(5));
                        product.setNameKey(item.get(26));
                        product.setNameView(item.get(27));
                        mLstProduct.add(product);
                    }
                    //Collections.sort(mLstProduct, new FishNameComparator());
                }
                else {
                    if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                        Common.showFailedToast(context, data.Message);
                    } else {
                        Common.errorWhenNotConnected(context);
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                call.cancel();
            }
        });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }

    public static ArrayList<ProductClass> getByGroupID(int ProductGroupID, String name) {
        ArrayList<ProductClass> data = new ArrayList<ProductClass>();
        for (ProductClass product : mLstProduct) {
            if ((product.getGroupID() == ProductGroupID || ProductGroupID == 0 || ProductGroupID
                    == -1)
                    && (name == "" || (name.length() >0 && product.getNameKey().toLowerCase().contains(name.toLowerCase()))
                    || (name.length()>0 && product.getName().toLowerCase().contains(name.toLowerCase()))))
                data.add(product);
        }
        /*
        Collections.sort(data, new Comparator<ProductClass>() {
            public int compare(ProductClass o1, ProductClass o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        */
        //Collections.sort(data, new FishNameComparator());
        return data;
    }

    public static class FishNameComparator implements Comparator<ProductClass>
    {
        public int compare(ProductClass left, ProductClass right) {
            return left.getName().compareTo(right.getName());
        }
    }

}
