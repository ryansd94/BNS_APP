package Dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.CF_Overview;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;
import com.pro.admin.atssoft.SessionManager;

import java.util.ArrayList;

import Adapter.CategoryAdapter;
import Control.MyTextView;
import info.androidhive.fontawesome.FontTextView;
import model.CategoryClass;

public class BranchDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.branch_dialog, container, false);
        TextView txtTitle = v.findViewById(R.id.txtTitle);
        final GridView grdArea = v.findViewById(R.id.grdArea);
        FontTextView imgClosed=v.findViewById(R.id.imgClosed);
        CheckBox ckcSelectAll=v.findViewById(R.id.ckcSelectAll);
        Button btnXacNhan=v.findViewById(R.id.btnXacNhan);
        if(Common._USE_MOBILE)
            btnXacNhan.setLayoutParams(Common._paramsPhoneButtonHeight);
        else
            btnXacNhan.setLayoutParams(Common._paramsIpadButtonHeight);


        MyTextView.setDialogTextTitle(getActivity(), txtTitle);

        CategoryAdapter categoryAdapter =new CategoryAdapter(getContext(), Common._LIST_BRANCH,Common.categoryType.BRANCH);
        grdArea.setAdapter(categoryAdapter);

        if(Common._LIST_BRANCH_SELECTED != null && Common._LIST_BRANCH != null)
        {
            if (Common._LIST_BRANCH_SELECTED.size() == Common._LIST_BRANCH.size())
                ckcSelectAll.setChecked(true);
        }

        imgClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ckcSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < grdArea.getChildCount() ; i++ ){
                    View v = grdArea.getChildAt(i);
                    CategoryAdapter.ViewHolder viewHolder=(CategoryAdapter.ViewHolder)v.getTag();
                    viewHolder.mCkcArea.setChecked(isChecked);
                }
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<CategoryClass> areaList=new ArrayList<>();
                ArrayList<Integer> areaListID=new ArrayList<>();
                for (int i = 0; i < grdArea.getChildCount() ; i++ ){
                    View view = grdArea.getChildAt(i);
                    CategoryAdapter.ViewHolder viewHolder=(CategoryAdapter.ViewHolder)view.getTag();
                    boolean checked= viewHolder.mCkcArea.isChecked();
                    if(checked) {
                        CategoryClass area=new CategoryClass();
                        area.setID(Integer.parseInt( viewHolder.mID.getText().toString()));
                        area.setName(viewHolder.mCkcArea.getText().toString());
                        areaList.add(area);
                        areaListID.add(area.getID());
                    }
                }
                SessionManager sessionManager=new SessionManager(getContext());
                sessionManager.saveBranchSelect(areaList);
                sessionManager.saveBranchIDSelect(areaListID);

                Common._LIST_BRANCH_SELECTED=areaList;
                Common._LIST_BRANCH_SELECTED_ID=areaListID;


                CF_Overview.getBillWailtingPayment(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected());

                CF_Overview.getPaidBill(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected());

                CF_Overview.getRoomEmptyAndUsedt(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected());

                CF_Overview.getRevenueToday(getContext(), Common._USER_TOKEN,Common.getStrBranchSelected(),Common._CHART_DOANHTHU,Common._CHART_SOLUONGKHACH );


                dismiss();
            }
        });

        return v;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_NoActionBar);

    }
}
