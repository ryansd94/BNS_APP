package com.pro.admin.atssoft;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    private BarChart chart;
    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;
    SessionManager mSessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        mSessionManager = new SessionManager(this);
        if (mSessionManager.getScreenSetting() == 1)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);

        chart = findViewById(R.id.chart1);

        /*
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);
        */

        BarDataSet barDataSet1 = new BarDataSet(barEntries1(), "data 1");
        barDataSet1.setColor(Color.RED);

        BarDataSet barDataSet2 = new BarDataSet(barEntries2(), "data 2");
        barDataSet2.setColor(Color.YELLOW);

        BarDataSet barDataSet3 = new BarDataSet(barEntries3(), "data 3");
        barDataSet3.setColor(Color.GREEN);

        BarData data = new BarData(barDataSet1, barDataSet2, barDataSet3);
        chart.setData(data);


        ArrayList<String> label = new ArrayList<String>();
        label.add("one");
        label.add("two");
        label.add("three");
        label.add("four");
        label.add("five");
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        chart.setDragEnabled(true);
        chart.setVisibleXRangeMaximum(3);

        float barSpace=0.05f;
        float groupSpace=0.37f;

        data.setBarWidth(0.16f);

        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0+chart.getBarData().getGroupWidth(groupSpace,barSpace) *5);
        chart.getAxisLeft().setAxisMinimum(0);

        chart.groupBars(0,groupSpace,barSpace);
        chart.invalidate();
    }

    private ArrayList<BarEntry> barEntries1()
    {
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(1,2000));
        barEntries.add(new BarEntry(2,200));
        barEntries.add(new BarEntry(3,1231));
        barEntries.add(new BarEntry(4,2231));
        barEntries.add(new BarEntry(5,4000));
        return  barEntries;
    }

    private ArrayList<BarEntry> barEntries2()
    {
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(1,1234));
        barEntries.add(new BarEntry(2,35346));
        barEntries.add(new BarEntry(3,12321));
        barEntries.add(new BarEntry(4,134));
        barEntries.add(new BarEntry(5,3425));
        return  barEntries;
    }

    private ArrayList<BarEntry> barEntries3()
    {
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(1,3453));
        barEntries.add(new BarEntry(2,3214));
        barEntries.add(new BarEntry(3,7574));
        barEntries.add(new BarEntry(4,4563));
        barEntries.add(new BarEntry(5,1231));
        return  barEntries;
    }

    public class XAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public XAxisValueFormatter(String[] values) {
            mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }

}
