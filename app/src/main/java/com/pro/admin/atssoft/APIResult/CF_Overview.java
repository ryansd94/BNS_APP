package com.pro.admin.atssoft.APIResult;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.annotations.SerializedName;
import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.SessionManager;

import java.util.ArrayList;
import java.util.List;

import fragment.OverviewFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CF_Overview {
    public static class report {
        @SerializedName("token")
        public String token;

        @SerializedName("branch")
        public String branch;

        public report(String token, String branch) {
            this.token = token;
            this.branch = branch;
        }
    }


    public static void getBillWailtingPayment(final Context context, String token, String branch) {


        try{
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetBillWailtingPayment(new CF_Overview.report(token,branch));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        for (ArrayList<String> item : data.Data) {
                            Common._CONTROL_TEXT_txtSoTienDonChoThanhToan.setText(item.get(2));
                            Common._CONTROL_TEXT_txtSLChoThanhToan.setText(item.get(1));
                        }
                    }
                    else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            //Common.showFailedToast(context, data.Message);
                        }
                        else
                        {
                            //Common.errorWhenNotConnected(context);
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
            //Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }


    public static void getPaidBill(final Context context, String token, String branch) {


        try{
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetPaidBill(new CF_Overview.report(token,branch));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        for (ArrayList<String> item : data.Data) {
                            Common._CONTROL_TEXT_txtSoTienDonDaXong.setText(item.get(2));
                            Common._CONTROL_TEXT_txtSLDonDaXong.setText(item.get(1));
                        }
                    }
                    else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            //Common.showFailedToast(context, data.Message);
                        }
                        else
                        {
                            //Common.errorWhenNotConnected(context);
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
            //Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }


    public static void getRoomEmptyAndUsedt(final Context context, String token, String branch) {


        try{
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetRoomEmptyAndUsedt(new CF_Overview.report(token,branch));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        for (ArrayList<String> item : data.Data) {
                            Common._CONTROL_TEXT_txtSLBanTrong.setText(item.get(1));
                            Common._CONTROL_TEXT_txtSLBanCoKhach.setText(item.get(0));
                        }
                    }
                    else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            //Common.showFailedToast(context, data.Message);
                        }
                        else
                        {
                            //Common.errorWhenNotConnected(context);
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
            //Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }

    public static void getRevenueToday(final Context context, String token,
                String branch, final CombinedChart mChart, final CombinedChart mChart3) {


        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetRevenueToday(new CF_Overview.report(token, branch));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        ArrayList<String> itemXLable = data.Data.get(0);

                        List<DataSet> lstChartProfitTotalSalesDataSet = new ArrayList<>();
                        List<DataSet> lstChartDataCustomerDataSet = new ArrayList<>();

                        for (int i = 1; i < data.Data.size(); i++) {
                            ArrayList<String> item = data.Data.get(i);
                            List<Integer> chartProfitTotalSalesData = new ArrayList<Integer>();
                            List<Integer> chartDataCustomerData = new ArrayList<>();
                            for (int j = 2; j < item.size() - 1; j = j + 2) {
                                chartProfitTotalSalesData.add(Integer.parseInt(item.get(j)));
                                chartDataCustomerData.add(Integer.parseInt(item.get(j + 1)));
                            }

                            lstChartProfitTotalSalesDataSet.add(getChartRevenueTodayDataSet(chartProfitTotalSalesData, item.get(1), Common._COLOR_REPORT[i - 1]));
                            lstChartDataCustomerDataSet.add(getChartRevenueTodayDataSet(chartDataCustomerData, item.get(1), Common._COLOR_REPORT[i - 1]));
                        }


                        OverviewFragment overviewFragment = new OverviewFragment();
                        overviewFragment.getRevenueToday(itemXLable, lstChartProfitTotalSalesDataSet, mChart, false);
                        overviewFragment.getRevenueToday(itemXLable, lstChartDataCustomerDataSet, mChart3, true);
                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            //Common.showFailedToast(context, data.Message);
                        } else {
                            //Common.errorWhenNotConnected(context);
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
            //Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }

    private static DataSet getChartRevenueTodayDataSet(List<Integer> data, String label, int color) {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < data.size(); index++) {
            entries.add(new Entry(index, data.get(index)));
        }

        LineDataSet set = new LineDataSet(entries, label);
        set.setColor(color);
        set.setLineWidth(2.5f);
        set.setCircleColor(color);
        set.setCircleRadius(5f);
        set.setFillColor(color);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //set.setDrawValues(false);
        set.setValueTextSize(10f);
        set.setValueTextColor(color);





        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);
        return set;
    }
}
