package com.pro.admin.atssoft.APIResult;

import android.content.Context;
import android.graphics.Color;
import android.os.Process;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.annotations.SerializedName;
import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.R;
import com.pro.admin.atssoft.SessionManager;

import java.util.ArrayList;
import java.util.List;

import Dialog.rpt_DoanhThuChiTietDialog;
import Dialog.rpt_Doanh_Thu_TongDialog;
import fragment.OverviewFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CF_Report {
    public static class sales{
        @SerializedName("token")
        public String token;
        @SerializedName("branch")
        public String branch;
        @SerializedName("fromdate")
        public String fromdate;
        @SerializedName("todate")
        public String todate;
        @SerializedName("timetype")
        public String timetype;
        public sales(String token, String branch,String fromdate, String todate, String timetype)
        {
            this.token = token;
            this.branch = branch;
            this.fromdate = fromdate;
            this.todate = todate;
            this.timetype = timetype;
        }
    }


    public static void getSales(final Context context, String token,
                                String branch, String fromDate, String toDate, String timeType, final BarChart chart, final boolean isProfilt) {

        //chart.setBackground();
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetSales(new CF_Report.sales(token, branch,fromDate,toDate,timeType));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        ArrayList<String> itemXLable = new ArrayList<>();
                        ArrayList<BarEntry> chartSalesData = new ArrayList<BarEntry>();
                        ArrayList<BarEntry> chartCostData = new  ArrayList<BarEntry>();
                        ArrayList<BarEntry> chartProfitData = new  ArrayList<BarEntry>();



                        for (int i = 0; i < data.Data.size(); i++) {
                            ArrayList<String> item = data.Data.get(i);
                            itemXLable.add(item.get(0));
                            chartSalesData.add(new BarEntry(i+1,Integer.parseInt(item.get(3))));
                            chartCostData.add(new BarEntry(i+1,Integer.parseInt(item.get(2))));
                            chartProfitData.add(new BarEntry(i+1,Integer.parseInt(item.get(3))-Integer.parseInt(item.get(2))));

                            //lstChartProfitTotalSalesDataSet.add(getChartRevenueTodayDataSet(chartProfitTotalSalesData, item.get(1), Common._COLOR_REPORT[i - 1]));
                            //lstChartDataCustomerDataSet.add(getChartRevenueTodayDataSet(chartDataCustomerData, item.get(1), Common._COLOR_REPORT[i - 1]));
                        }

                        BarDataSet barDataSetSales = new BarDataSet(chartSalesData, "Tổng bán");
                        BarDataSet barDataSetCost = new BarDataSet(chartCostData, "Tổng vốn");
                        BarDataSet barDataSetProfit = new BarDataSet(chartProfitData, "Lợi nhuận");

                        barDataSetSales.setColor(Color.RED);
                        barDataSetCost.setColor(Color.YELLOW);
                        barDataSetProfit.setColor(Color.GREEN);

                        rpt_Doanh_Thu_TongDialog rpt_doanh_thu_tongDialog = new rpt_Doanh_Thu_TongDialog();
                        rpt_doanh_thu_tongDialog.loadData(barDataSetSales,barDataSetCost,barDataSetProfit,itemXLable,chart,isProfilt);
                        //overviewFragment.getRevenueToday(itemXLable, lstChartDataCustomerDataSet, mChart3, true);
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

    private static CombinedChart createChart(Context context)
    {
         CombinedChart chart =new CombinedChart(context);

        chart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.setNoDataText("");
        //chart.setBackground(context.getDrawable(R.drawable.delete));


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        return  chart;
    }

    public static void GetRevenueDetail(final Context context, String token,
                                       String branch, String fromDate, String toDate, String timeType, LinearLayout layChart) {


        final CombinedChart mChart = createChart(context);
        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        mChart.addView(progressBar,params);
        layChart.removeAllViews();
        layChart.addView(mChart);
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetRevenueDetail(new CF_Report.sales(token, branch,fromDate,toDate,timeType));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        ArrayList<String> itemXLable = data.Data.get(0);

                        List<DataSet> lstChartProfitTotalSalesDataSet = new ArrayList<>();
                        List<DataSet> lstChartDataCustomerDataSet = new ArrayList<>();
                        int max=0;
                        for (int i = 1; i < data.Data.size(); i++) {
                            ArrayList<String> item = data.Data.get(i);
                            List<Integer> chartProfitTotalSalesData = new ArrayList<Integer>();
                            List<Integer> chartDataCustomerData = new ArrayList<>();
                            for (int j = 2; j < item.size(); j ++) {
                                chartProfitTotalSalesData.add(Integer.parseInt(item.get(j)));
                                if(Integer.parseInt(item.get(j))>max)
                                    max=Integer.parseInt(item.get(j));
                            }

                            lstChartProfitTotalSalesDataSet.add(getChartRevenueTodayDataSet(chartProfitTotalSalesData, item.get(1), Common._COLOR_REPORT[i - 1]));

                        }

                        rpt_DoanhThuChiTietDialog rpt_doanhThuChiTietDialog=new rpt_DoanhThuChiTietDialog();

                        rpt_doanhThuChiTietDialog.loadData(itemXLable, lstChartProfitTotalSalesDataSet, mChart, false,max);
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
