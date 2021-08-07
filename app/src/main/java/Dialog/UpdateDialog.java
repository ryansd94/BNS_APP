package Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.DownloadFILE;
import com.pro.admin.atssoft.R;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import Control.MyButton;
import Control.MyTextView;

public class UpdateDialog  extends DialogFragment {
    View mRootview;

    public static final int progress_bar_type = 0;
    // File url to download
    //private static String file_url = "https://drive.google.com/uc?export=download&id=154enpn4GgdQRPxkSS5cHVF2Jkms3T3Ck";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootview = inflater.inflate(R.layout.update_dialog, container, false);
        TextView txtTitle = mRootview.findViewById(R.id.txtTitle);
        TextView txtVersionNumber = mRootview.findViewById(R.id.txtVersionNumber);
        Button btnAccept = mRootview.findViewById(R.id.btnAcccept);
        Button btnCancel = mRootview.findViewById(R.id.btnCancel);

        MyButton myButton = new MyButton(getActivity());
        myButton.setHeightButton(btnAccept, getActivity());
        myButton.setHeightButton(btnCancel, getActivity());

        MyTextView.setTextRoomTitle(getActivity(), txtTitle);
        txtTitle.setText(R.string.update_version);

        txtVersionNumber.setText(Common._CONFIG.getAndroidVersion());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //d.dismiss();
                dismiss();
            }
        });


        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*

                UpdateDialog.this.dismiss();
                DownloadFILE downloadFILE = new DownloadFILE();
                downloadFILE.downloadFile(getActivity(), Common._CONFIG.getAndroidURL(), "app-debug");

                 */


                final String appPackageName = Common._PACKAGENAME; // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
        return mRootview;
    }





}
