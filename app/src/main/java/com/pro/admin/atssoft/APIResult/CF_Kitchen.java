package com.pro.admin.atssoft.APIResult;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.R;
import com.pro.admin.atssoft.SessionManager;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import Adapter.KitchenAdapter;
import model.KitchenClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CF_Kitchen {
    public static class getUnProcess {
        @SerializedName("token")
        public String token;

        @SerializedName("area")
        public String area;

        public getUnProcess(String token, String area) {
            this.token = token;
            this.area = area;
        }
    }

    public static class getWaitProcess {
        @SerializedName("token")
        public String token;

        @SerializedName("area")
        public String area;

        public getWaitProcess(String token, String area) {
            this.token = token;
            this.area = area;
        }
    }

    public static class getFinished {
        @SerializedName("token")
        public String token;

        @SerializedName("area")
        public String area;

        public getFinished(String token, String area) {
            this.token = token;
            this.area = area;
        }
    }

    public static class waitProcess {
        @SerializedName("token")
        public String token;

        @SerializedName("index")
        public String index;

        @SerializedName("quantity")
        public String quantity;

        public waitProcess(String token, String index, String quantity) {
            this.token = token;
            this.index = index;
            this.quantity = quantity;

        }
    }


    public static class done {
        @SerializedName("token")
        public String token;

        @SerializedName("index")
        public String index;

        @SerializedName("quantity")
        public String quantity;

        public done(String token, String index, String quantity) {
            this.token = token;
            this.index = index;
            this.quantity = quantity;

        }
    }

    public static class finished {
        @SerializedName("token")
        public String token;

        @SerializedName("index")
        public String index;

        @SerializedName("quantity")
        public String quantity;

        public finished(String token, String index, String quantity) {
            this.token = token;
            this.index = index;
            this.quantity = quantity;

        }
    }


    public static void done(final Context context, final String mToken, String index, String quantity) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doDone(new CF_Kitchen.done(mToken, index, quantity));
            ;

            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        Common.showSuccessToast(context);
                        getUnProcess(context, mToken);
                        getWaitProcess(context, mToken);
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


    public static void finished(final Context context, final String mToken, String index, String quantity) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doFinished(new CF_Kitchen.finished(mToken, index, quantity));

            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        Common.showSuccessToast(context);
                        getFinished(context, mToken);
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

    public static void waitProcess(final Context context, final String mToken, String index, String quantity) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doWaitProcess(new CF_Kitchen.waitProcess(mToken, index, quantity));
            ;

            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        Common.showSuccessToast(context);
                        getUnProcess(context, mToken);
                        getWaitProcess(context, mToken);
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


    public static void getUnProcess(final Context context, String mToken) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);

            int area = -1;
            //if(!Common._USER_KITCHEN)
            //area= Common._AREA_SELECTED;

            Call<Object> callArea = mApiInterface.doGetUnProcess(new CF_Kitchen.getUnProcess(mToken, String.valueOf(area)));

            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        //Tạo khu vực cho các bàn k có khu vực
                        ArrayList<KitchenClass> mLstData = new ArrayList<KitchenClass>();

                        for (ArrayList<String> item : data.Data) {
                            if (Common._LIST_AREA_SELECTED_ID != null && Common._LIST_AREA_SELECTED_ID.size() > 0 && !Common._LIST_AREA_SELECTED_ID.contains(Integer.parseInt(item.get(7))))
                                continue;
                            KitchenClass kitchen = new KitchenClass();
                            kitchen.setIndex(item.get(0));
                            kitchen.setProductName(item.get(1));
                            kitchen.setTableName(item.get(2));
                            kitchen.setQuantity(item.get(3));
                            kitchen.setTime(item.get(4));
                            kitchen.setNote(item.get(5));
                            mLstData.add(kitchen);

                        }

                        KitchenAdapter adapter = new KitchenAdapter(context, mLstData, 0);
                        if (Common._CONTROL_GRD_UnProcess != null)
                            Common._CONTROL_GRD_UnProcess.setAdapter(adapter);

                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            //Common.errorWhenNotConnected(context);
                        }
                    }

                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    //Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            //Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }


    public static void getFinished(final Context context, String mToken) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            int area = -1;

            Call<Object> callArea = mApiInterface.doGetFinished(new CF_Kitchen.getFinished(mToken, String.valueOf(area)));

            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {

                    try {
                        DataResult result = new DataResult();
                        DataResult.Data data = result.getDataResult(response);
                        if (data.Status.contains("OK")) {


                            ArrayList<KitchenClass> mLstData = new ArrayList<KitchenClass>();
                            int quantity = 0;
                            int quantitynew = 0;
                            for (ArrayList<String> item : data.Data) {

                                if (Common._LIST_AREA_SELECTED_ID != null && Common._LIST_AREA_SELECTED_ID.size() > 0 && !Common._LIST_AREA_SELECTED_ID.contains(Integer.parseInt(item.get(7))))
                                    continue;

                                KitchenClass kitchen = new KitchenClass();
                                kitchen.setIndex(item.get(0));
                                kitchen.setProductName(item.get(1));
                                kitchen.setTableName(item.get(2));
                                kitchen.setQuantity(item.get(3));
                                kitchen.setTime(item.get(4));
                                kitchen.setNote(item.get(5));
                                kitchen.setTimestamp(Integer.parseInt(item.get(8)));
                                //Toast.makeText(context,kitchen.getTimestamp()+", "+ Common._NOTIFY_PRODUCT_DONE_TIMESTAMP,Toast.LENGTH_SHORT).show();

                                if (kitchen.getTimestamp() > Common._NOTIFY_PRODUCT_DONE_TIMESTAMP) {
                                    Common._NOTIFY_PRODUCT_DONE_TIMESTAMP = kitchen.getTimestamp();
                                }

                                mLstData.add(kitchen);
                                quantity += Integer.parseInt(item.get(3));
                            }
                            quantitynew = quantity - Common._NOTIFY_PRODUCT_DONE_COUNT;

                            KitchenAdapter adapter = new KitchenAdapter(context, mLstData, 2);
                            Common._NOTIFY_PRODUCT_DONE_COUNT = quantity;
                            if (Common._CONTROL_GRD_Finished != null)
                                Common._CONTROL_GRD_Finished.setAdapter(adapter);
                            if (Common._NOTIFY_PRODUCT_DONE_BUTTON != null) {
                                Common._NOTIFY_PRODUCT_DONE_BUTTON.setText(Common._NOTIFY_PRODUCT_DONE_COUNT + "");
                                if (Common._NOTIFY_PRODUCT_DONE_COUNT > 0) {
                                    Common._NOTIFY_PRODUCT_DONE_BUTTON.setVisibility(View.VISIBLE);
                                } else
                                    Common._NOTIFY_PRODUCT_DONE_BUTTON.setVisibility(View.GONE);
                            }
                            if (quantitynew > 0 && !Common._IS_FIRST_LOGON) {
                                Common.sendNotifyProductDoneNew(context, quantitynew);
                            }
                            Common._IS_FIRST_LOGON = false;

                        } else {
                            if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                                Common.showFailedToast(context, data.Message);
                            } else {
                                //Common.errorWhenNotConnected(context);
                            }
                        }
                    } catch (Exception ex) {
                        //Common.showFailedToast(context, ex.toString());
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    //Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            Common.showFailedToast(context, ex.toString());
        }
    }

    public static void getWaitProcess(final Context context, String mToken) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            int area = -1;
            //if(!Common._USER_KITCHEN)
            //area= Common._AREA_SELECTED;
            Call<Object> callArea = mApiInterface.doGetWaitProcess(new CF_Kitchen.getWaitProcess(mToken, String.valueOf(area)));

            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        //Tạo khu vực cho các bàn k có khu vực
                        ArrayList<KitchenClass> mLstData = new ArrayList<KitchenClass>();
                        for (ArrayList<String> item : data.Data) {
                            if ( Common._LIST_AREA_SELECTED_ID != null && Common._LIST_AREA_SELECTED_ID.size() > 0  && !Common._LIST_AREA_SELECTED_ID.contains(Integer.parseInt(item.get(7))))
                                continue;
                            KitchenClass kitchen = new KitchenClass();
                            kitchen.setIndex(item.get(0));
                            kitchen.setProductName(item.get(1));
                            kitchen.setTableName(item.get(2));
                            kitchen.setQuantity(item.get(3));
                            kitchen.setTime(item.get(4));
                            kitchen.setNote(item.get(5));
                            mLstData.add(kitchen);
                        }

                        KitchenAdapter adapter = new KitchenAdapter(context, mLstData, 1);
                        if (Common._CONTROL_GRD_WaitProcess != null)
                            Common._CONTROL_GRD_WaitProcess.setAdapter(adapter);
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
