package fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.pro.admin.atssoft.APIResult.CF_Category;
import com.pro.admin.atssoft.APIResult.CF_Overview;
import com.pro.admin.atssoft.APIResult.CF_Room;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;
import com.pro.admin.atssoft.SessionManager;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import Control.MyButton;
import Dialog.AreaDialog;
import model.CategoryClass;

public class OrderFragment  extends Fragment implements DialogInterface.OnDismissListener  {

    SessionManager mSessionManager;
    String mUserName, mUserToken;


    MyButton mClassButton;
    //MyGridView mGvRoom;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.order,container,false);
        mSessionManager = new SessionManager(getContext());
        mSessionManager.checkLogin();


        HashMap<String, String> user = mSessionManager.getUserDetail();
        mUserName = user.get(mSessionManager.EmployeeName);
        mUserToken = user.get(mSessionManager.USER_TOKEN);
        Common._USER_TOKEN = mUserToken;


        mClassButton = new MyButton(getContext());
        Common._CONTROL_GvArea = v.findViewById(R.id.grdArea);
        Common._LAYOUT_ROOM = v.findViewById(R.id.grdRoom);
        RadioButton rdAll = v.findViewById(R.id.rdAll);
        RadioButton rdEmpty = v.findViewById(R.id.rdEmpty);
        RadioButton rdUsed = v.findViewById(R.id.rdUsed);
        Button btnArea=v.findViewById(R.id.btnSelectArea);

        if(Common._USE_MOBILE)
            btnArea.setLayoutParams(Common._paramsPhoneButtonHeight);
        else
            btnArea.setLayoutParams(Common._paramsIpadButtonHeight);

        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaDialog areaDialog=new AreaDialog();
                areaDialog.show(getFragmentManager(),"");
            }
        });


        CF_Category.getArea(getContext(),  Common._USER_TOKEN);
        Common._FRAGMENTMANAGER=getFragmentManager();
        Common.loadRoom(getContext());

        TimerTask updateBall = new UpdateBallTask();
        if(Common.__TimerLoadRoom != null)
        Common.__TimerLoadRoom.scheduleAtFixedRate(updateBall, 0, 3000);

        rdEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common._ROOM_STATUS_SELECTED = 0;
                CF_Room.getAll(getContext(), Common._USER_TOKEN,  0);
            }
        });
        rdAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common._ROOM_STATUS_SELECTED = -1;
                CF_Room.getAll(getContext(), Common._USER_TOKEN, -1);
            }
        });
        rdUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common._ROOM_STATUS_SELECTED = 1;
                CF_Room.getAll(getContext(), Common._USER_TOKEN,  1);
            }
        });

        //loadRoom(Common._AREA_CLASS);

        return v;
    }
    class UpdateBallTask extends TimerTask {
        public void run() {
            Common.loadRoomReal(getContext());
        }
    }
    public void loadRoom(CategoryClass area) {
        if (area != null)
            Common._AREA_CLASS = area;
        else
            Common._AREA_CLASS = CF_Category.createAreaEmpty(getContext());
        //Common._CONTROL_TxtAreaName.setText(Common._AREA_CLASS.getName());
        CF_Room.getAll(getContext(), Common._USER_TOKEN,  Common._ROOM_STATUS_SELECTED);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(Common.__TimerLoadRoom != null)
        {
            Common.__TimerLoadRoom.cancel();
        Common.__TimerLoadRoom = null;
        }
    }
}
