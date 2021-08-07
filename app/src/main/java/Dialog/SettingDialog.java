package Dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;
import com.pro.admin.atssoft.SessionManager;

import java.util.ArrayList;

import Control.MyTextView;
import info.androidhive.fontawesome.FontTextView;

public class SettingDialog extends DialogFragment {
    EditText edt;
    ArrayList<String> listAPI = new ArrayList<>();

    //Được dùng khi khởi tạo dialog mục đích nhận giá trị
    public static SettingDialog newInstance(String valueSelect) {
        SettingDialog dialog = new SettingDialog();
        Bundle args = new Bundle();
        args.putString("value", valueSelect);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_settings, container);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_NoActionBar);
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        Button btnXacNhan =view.findViewById(R.id.btnXacNhan);
        edt = view.findViewById(R.id.linkapi);
        final RadioGroup radApi = view.findViewById(R.id.radApi);
final LinearLayout layListAPI= view.findViewById(R.id.listAPI);
        final RadioButton rdDung=view.findViewById(R.id.rdPortrait);
        final RadioButton rdNgang=view.findViewById(R.id.rdLandscape);
        SessionManager mSessionManager = new SessionManager(getActivity());
        String apiSelected = mSessionManager.getAPILink();

        MyTextView.setDialogTextTitle(getActivity(), txtTitle);
        FontTextView imgClosed=view.findViewById(R.id.imgClosed);
        edt.setText(apiSelected);
        listAPI = mSessionManager.getListAPI();
        if(Common._USE_MOBILE)
            btnXacNhan.setLayoutParams(Common._paramsPhoneButtonHeight);
        else
            btnXacNhan.setLayoutParams(Common._paramsIpadButtonHeight);

        SessionManager sessionManager = new SessionManager(getActivity());
        if(sessionManager.getScreenSetting() ==0) {
            rdDung.setChecked(true);
            rdNgang.setChecked(false);
        }
        else {
            rdDung.setChecked(false);
            rdNgang.setChecked(true);
        }


        if (listAPI.size() > 1) {
            for (String api : listAPI) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(api);
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            edt.setText(buttonView.getText());
                        }
                    }
                });

                radApi.addView(radioButton);
            }
            layListAPI.setVisibility(View.VISIBLE);
        }



        imgClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager(getActivity());
                sessionManager.createAPIList(edt.getText().toString());

                Common._API_Link = edt.getText().toString();
                if (!listAPI.contains(edt.getText().toString())) {
                    if (edt.getText().toString().length() > 0)
                        listAPI.add(edt.getText().toString());
                }


                sessionManager.saveListAPI(listAPI);


                if(rdNgang.isChecked())
                    sessionManager.saveScreenSetting(1);
                else
                    sessionManager.saveScreenSetting(0);
                dismiss();
            }
        });

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }

    }
}
