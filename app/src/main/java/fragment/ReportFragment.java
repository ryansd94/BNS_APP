package fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pro.admin.atssoft.R;

import Dialog.rpt_DoanhThuChiTietDialog;
import Dialog.rpt_Doanh_Thu_TongDialog;

public class ReportFragment extends Fragment implements DialogInterface.OnDismissListener  {
    @Override
    public void onDismiss(DialogInterface dialog) {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report,container,false);

        Button edtDoanhThuTong=v.findViewById(R.id.rptDoanhThuTong);
        Button edtDoanhThuChiTiet=v.findViewById(R.id.rptDoanhThuChiTiet);




        edtDoanhThuTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rpt_Doanh_Thu_TongDialog rpt_doanh_thu_tongDialog=new rpt_Doanh_Thu_TongDialog();
                rpt_doanh_thu_tongDialog.show(getFragmentManager(),"");
            }
        });
        edtDoanhThuChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rpt_DoanhThuChiTietDialog rpt_doanhThuChiTietDialog=new rpt_DoanhThuChiTietDialog();
                rpt_doanhThuChiTietDialog.show(getFragmentManager(),"");
            }
        });
        return  v;
    }
}
