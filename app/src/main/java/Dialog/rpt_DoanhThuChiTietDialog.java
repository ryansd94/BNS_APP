package Dialog;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.pro.admin.atssoft.APIResult.CF_Report;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import Control.MyDatepicker;
import Control.MyTextView;
import fragment.OverviewFragment;
import info.androidhive.fontawesome.FontTextView;

public class rpt_DoanhThuChiTietDialog extends DialogFragment implements OnChartValueSelectedListener {
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.report_doanhthuchitiet, container, false);

        //final CombinedChart chart = v.findViewById(R.id.chart1);

        final LinearLayout layChart=v.findViewById(R.id.layChart);
        final MyDatepicker edtFromDate=v.findViewById(R.id.edtFromDate);
        final MyDatepicker edtToDate=v.findViewById(R.id.edtToDate);
        RadioButton rdToday = v.findViewById(R.id.rdToday);
        final RadioButton rdOnweek = v.findViewById(R.id.rdOnweek);
        final RadioButton rdBydate = v.findViewById(R.id.rdBydate);
        RadioGroup rdGroup = v.findViewById(R.id.rdGroup);
        final LinearLayout layDatepicker=v.findViewById(R.id.layDatepicker);
        //final Calendar myCalendar = Calendar.getInstance(TimeZone.getDefault());
        FontTextView imgClosed=v.findViewById(R.id.imgClosed);
        TextView txtTitle = v.findViewById(R.id.txtTitle);
        String fromDate="",toDate="";
        Calendar myCalendar = Calendar.getInstance(TimeZone.getDefault());
        myCalendar.set(Calendar.YEAR,myCalendar.get(Calendar.YEAR));
        myCalendar.set(Calendar.MONTH, myCalendar.get(Calendar.MONTH));
        myCalendar.set(Calendar.DAY_OF_MONTH, 1);
        Common.updateLabelDate(edtFromDate,myCalendar);
        fromDate=Common.getDateFromCalendar(myCalendar);

        myCalendar = Calendar.getInstance(TimeZone.getDefault());
        myCalendar.set(Calendar.YEAR, myCalendar.get(Calendar.YEAR));
        myCalendar.set(Calendar.MONTH, myCalendar.get(Calendar.MONTH));
        myCalendar.set(Calendar.DAY_OF_MONTH, myCalendar.get(Calendar.DAY_OF_MONTH));
        Common.updateLabelDate(edtToDate,myCalendar);
        toDate=Common.getDateFromCalendar(myCalendar);

        rdBydate.setChecked(true);
        MyTextView.setDialogTextTitle(getActivity(), txtTitle);
        imgClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });






        rdToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layDatepicker.setVisibility(View.GONE);
                String fromDate="",toDate="";
                Calendar myCalendar = Calendar.getInstance(TimeZone.getDefault());
                fromDate=Common.getDateFromCalendar(myCalendar);
                toDate=fromDate;
                //Toast.makeText(getContext(),fromDate+"-"+toDate,Toast.LENGTH_SHORT).show();
                CF_Report.GetRevenueDetail(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,fromDate,toDate,"0",layChart);
            }
        });
        rdOnweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layDatepicker.setVisibility(View.GONE);
                CF_Report.GetRevenueDetail(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,"","","1",layChart);
            }
        });
        rdBydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fromDate="",toDate="";
                Calendar myCalendar = Calendar.getInstance(TimeZone.getDefault());
                myCalendar.set(Calendar.YEAR,myCalendar.get(Calendar.YEAR));
                myCalendar.set(Calendar.MONTH, myCalendar.get(Calendar.MONTH));
                myCalendar.set(Calendar.DAY_OF_MONTH, 1);
                Common.updateLabelDate(edtFromDate,myCalendar);
                fromDate=Common.getDateFromCalendar(myCalendar);

                myCalendar = Calendar.getInstance(TimeZone.getDefault());
                myCalendar.set(Calendar.YEAR, myCalendar.get(Calendar.YEAR));
                myCalendar.set(Calendar.MONTH, myCalendar.get(Calendar.MONTH));
                myCalendar.set(Calendar.DAY_OF_MONTH, myCalendar.get(Calendar.DAY_OF_MONTH));
                Common.updateLabelDate(edtToDate,myCalendar);
                toDate=Common.getDateFromCalendar(myCalendar);

                CF_Report.GetRevenueDetail(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,fromDate,toDate,"2",layChart);

                layDatepicker.setVisibility(View.VISIBLE);
            }
        });



        final DatePickerDialog.OnDateSetListener dateFrom = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                Calendar myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Common.updateLabelDate(edtFromDate,myCalendar);

                String fromDate="",toDate="";
                fromDate=Common.getDateFromCalendar(myCalendar);
                toDate= Common.getDateFromString(edtToDate.getText()+"");
                //Toast.makeText(getContext(),fromDate+"-"+toDate,Toast.LENGTH_SHORT).show();

                CF_Report.GetRevenueDetail(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,fromDate,toDate,"2",layChart);

            }

        };

        final DatePickerDialog.OnDateSetListener dateTo = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                Calendar myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Common.updateLabelDate(edtToDate,myCalendar);
                String fromDate="",toDate="";
                toDate=Common.getDateFromCalendar(myCalendar);
                fromDate= Common.getDateFromString(edtFromDate.getText()+"");
                //Toast.makeText(getContext(),fromDate+"-"+toDate,Toast.LENGTH_SHORT).show();

                CF_Report.GetRevenueDetail(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,fromDate,toDate,"2",layChart);
            }

        };

        edtFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar myCalendar=   Common.getCalendarFromString(edtFromDate.getText()+"");
                new DatePickerDialog(getContext(), dateFrom, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar myCalendar=   Common.getCalendarFromString(edtToDate.getText()+"");
                new DatePickerDialog(getContext(), dateTo, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        CF_Report.GetRevenueDetail(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                ,fromDate,toDate,"2",layChart);








        return v;
    }


    public void loadData(final List<String> xLabel, List<DataSet> lstDataSet, CombinedChart mChart, boolean intFormat,int maxValue) {


        /*
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);




 */

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

        XAxis xAxis = mChart.getXAxis();
        xAxis.setLabelCount(xLabel.size());
        xAxis.setSpaceMin(15f);


        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value);
            }
        });
        if(maxValue > 0)
        mChart.getAxisLeft().setAxisMaximum(maxValue + 100000);
        mChart.setSaveEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setAutoScaleMinMaxEnabled(true);
        // draw points over time
        //mChart.animateX(1500);
        mChart.setVisibleXRangeMaximum(7);
        //mChart.setBackgroundResource(0);
        mChart.removeAllViews();
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_NoActionBar);

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
