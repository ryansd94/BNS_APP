package Dialog;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.pro.admin.atssoft.APIResult.CF_Report;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import Control.MyDatepicker;
import Control.MyTextView;
import info.androidhive.fontawesome.FontTextView;

public class rpt_Doanh_Thu_TongDialog extends DialogFragment {

    BarDataSet chartSalesData,  chartCostData, chartProfitData;
    ArrayList<String> label;
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.report_doanh_thu_tong, container, false);

        final BarChart chart = v.findViewById(R.id.chart1);
        final MyDatepicker edtFromDate=v.findViewById(R.id.edtFromDate);
        final MyDatepicker edtToDate=v.findViewById(R.id.edtToDate);
        RadioButton rdToday = v.findViewById(R.id.rdToday);
        final RadioButton rdOnweek = v.findViewById(R.id.rdOnweek);
        final RadioButton rdBydate = v.findViewById(R.id.rdBydate);
        RadioGroup rdGroup = v.findViewById(R.id.rdGroup);
        final RadioButton rdLoiNhuan = v.findViewById(R.id.rdLoiNhuan);
        final RadioButton rdDoanhThu = v.findViewById(R.id.rdDoanhThu);
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
        rdLoiNhuan.setChecked(true);
        MyTextView.setDialogTextTitle(getActivity(), txtTitle);
        imgClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rdDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setData(chart,rdLoiNhuan.isChecked());
                int type=0;
                if(rdOnweek.isChecked())
                    type=1;
                else  if(rdBydate.isChecked())
                    type=2;
                CF_Report.getSales(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,Common.getDateFromCalendar(Common.getCalendarFromString(edtFromDate.getText().toString())),
                        Common.getDateFromCalendar(Common.getCalendarFromString(edtToDate.getText().toString())),type+"",chart,rdLoiNhuan.isChecked());
            }
        });

        rdLoiNhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type=0;
                if(rdOnweek.isChecked())
                    type=1;
                else  if(rdBydate.isChecked())
                    type=2;
                CF_Report.getSales(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,Common.getDateFromCalendar(Common.getCalendarFromString(edtFromDate.getText().toString())),
                        Common.getDateFromCalendar(Common.getCalendarFromString(edtToDate.getText().toString())),type+"",chart,rdLoiNhuan.isChecked());
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
                CF_Report.getSales(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,fromDate,toDate,"0",chart,rdLoiNhuan.isChecked());
            }
        });
        rdOnweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layDatepicker.setVisibility(View.GONE);
                CF_Report.getSales(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,"","","1",chart,rdLoiNhuan.isChecked());
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

                CF_Report.getSales(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,fromDate,toDate,"2",chart,rdLoiNhuan.isChecked());

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

                CF_Report.getSales(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,fromDate,toDate,"2",chart,rdLoiNhuan.isChecked());

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

                CF_Report.getSales(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                        ,fromDate,toDate,"2",chart,rdLoiNhuan.isChecked());
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

        /*

        Drawable dFromDate= edtFromDate.getBackground();
        dFromDate.setColorFilter(getResources().getColor(R.color.colorRed), PorterDuff.Mode.MULTIPLY);


        Drawable dToDate= edtToDate.getBackground();
        dToDate.setColorFilter(getResources().getColor(R.color.color_quantity), PorterDuff.Mode.MULTIPLY);


         */

        CF_Report.getSales(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected()
                ,fromDate,toDate,"2",chart,rdLoiNhuan.isChecked());
        return v;
    }
    public void loadData(BarDataSet chartSalesData, BarDataSet chartCostData,
                         BarDataSet chartProfitData, ArrayList<String> label, BarChart chart, boolean isProfilt)
    {
        chart.clear();
        chart.invalidate();
        chart.notifyDataSetChanged();
        BarData data = new BarData(chartSalesData, chartCostData, chartProfitData);
        data.setBarWidth(0.16f);
        chart.setData(data);
        if(isProfilt) {
            chartSalesData.setDrawValues(true);
            chartProfitData.setVisible(true);
            chartCostData.setVisible(true);
        }
        else
        {
            chartSalesData.setDrawValues(true);
            chartProfitData.setVisible(false);
            chartCostData.setVisible(false);
        }


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



        chart.getAxisRight().setEnabled(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0+chart.getBarData().getGroupWidth(groupSpace,barSpace) *chartSalesData.getEntryCount());
        chart.getAxisLeft().setAxisMinimum(0);

        chart.groupBars(0,groupSpace,barSpace);
        chart.getDescription().setEnabled(false);

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_NoActionBar);

    }
}
