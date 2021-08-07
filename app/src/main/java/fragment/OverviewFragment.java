package fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.pro.admin.atssoft.APIResult.CF_Overview;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.util.ArrayList;
import java.util.List;

import Control.MyMarkerView;

import static com.pchmn.materialchips.R2.id.content;

public class OverviewFragment extends Fragment implements DialogInterface.OnDismissListener, OnChartValueSelectedListener {
    @Override
    public void onDismiss(DialogInterface dialog) {
    }

    private BarChart barChart;

    private boolean shouldRefreshOnResume = false;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    //PtrFrameLayout mPtrFrame;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_overview,container,false);
        //barChart = v.findViewById(R.id.chart1);

        /*
         mPtrFrame=v.findViewById(R.id.store_house_ptr_frame);

        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
// default is false
        mPtrFrame.setPullToRefresh(false);
// default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);

         */

        Common._CHART_DOANHTHU = v.findViewById(R.id.chart2);
        Common._CHART_SOLUONGKHACH  = v.findViewById(R.id.chart3);


        Common._CONTROL_TEXT_txtSLDonDaXong= v.findViewById(R.id.txtSLDonDaXong);
        Common._CONTROL_TEXT_txtSoTienDonDaXong= v.findViewById(R.id.txtSoTienDonDaXong);
        Common._CONTROL_TEXT_txtSLChoThanhToan= v.findViewById(R.id.txtSLChoThanhToan);
        Common._CONTROL_TEXT_txtSoTienDonChoThanhToan= v.findViewById(R.id.txtSoTienDonChoThanhToan);
        Common._CONTROL_TEXT_txtSLBanTrong= v.findViewById(R.id.txtSLBanTrong);
        Common._CONTROL_TEXT_txtSLBanCoKhach= v.findViewById(R.id.txtSLBanCoKhach);


        CF_Overview.getBillWailtingPayment(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected());

        CF_Overview.getPaidBill(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected());

        CF_Overview.getRoomEmptyAndUsedt(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected());

        CF_Overview.getRevenueToday(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected(),Common._CHART_DOANHTHU,Common._CHART_SOLUONGKHACH );


        //getRevenueToday();


        return  v;
    }


    public void getRevenueToday(final List<String> xLabel, List<DataSet> lstDataSet,CombinedChart mChart, boolean intFormat)
    {
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);

        /*
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

         */

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xLabel.size());
        xAxis.setSpaceMin(15f);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value);
            }
        });

        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        for(DataSet item : lstDataSet)
        {
            lineDatas.addDataSet((ILineDataSet) item);
        }


        data.setData(lineDatas);
        if(intFormat)
            data.setValueFormatter(new Common.IntValueFormatter());
        //xAxis.setAxisMaximum(data.getXMax() + 0.25f);


        /*
        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
        // Set the marker to the chart
        mv.setChartView(mChart);
        mChart.setMarker(mv);

         */

        mChart.getAxisRight().setEnabled(false);
        mChart.setData(data);
        mChart.invalidate();
        mChart.setSaveEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setAutoScaleMinMaxEnabled(true);
        // draw points over time
        mChart.animateX(1500);
    }



    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
