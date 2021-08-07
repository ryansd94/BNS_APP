package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.pro.admin.atssoft.APIResult.CF_Kitchen;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

public class KitchenFragment  extends Fragment {

    GridView mGrdKitchenUnProcess,mGrdKitchenWailProcess;
    public    Handler timerHandler = new Handler();
    public   Handler timerHandler2 = new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        //Toast.makeText(getContext(),"Kitchen",Toast.LENGTH_SHORT).show();
        View v = inflater.inflate(R.layout.kitchen, container, false);
        mGrdKitchenUnProcess = (GridView) v.findViewById(R.id.grdKitchenUnProcess);
        mGrdKitchenWailProcess = (GridView) v.findViewById(R.id.grdKitchenWailProcess);

        Common._CONTROL_GRD_UnProcess = mGrdKitchenUnProcess;
        Common._CONTROL_GRD_WaitProcess = mGrdKitchenWailProcess;

        if (!Common._MSG_KhongSuDungTinhNangBep) {
            timerHandler.postDelayed(timerRunnable, 0);
            timerHandler2.postDelayed(timerRunnable2, 0);
        }

        return v;
    }

  public   Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            CF_Kitchen.getUnProcess(getContext(), Common._USER_TOKEN);
            timerHandler.postDelayed(this, 1000 * Common._TIME_GET_UNPROCESS);
        }
    };

  public   Runnable timerRunnable2 = new Runnable() {

        @Override
        public void run() {
            CF_Kitchen.getWaitProcess(getContext(), Common._USER_TOKEN);
            timerHandler2.postDelayed(this, 1000 * Common._TIME_GET_PROCESS);
        }
    };


  public void removeCallbacks()
  {
      timerHandler.removeCallbacks(timerRunnable);
      timerHandler2.removeCallbacks(timerRunnable2);
  }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timerHandler.removeCallbacks(timerRunnable);
        timerHandler2.removeCallbacks(timerRunnable2);
    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(),"Start",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getContext(),"Stop",Toast.LENGTH_SHORT).show();
        timerHandler.removeCallbacks(timerRunnable);
        timerHandler2.removeCallbacks(timerRunnable2);
    }
    */
}
