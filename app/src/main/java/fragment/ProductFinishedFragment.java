package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.pro.admin.atssoft.APIResult.CF_Kitchen;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

public class ProductFinishedFragment  extends Fragment {

    public Handler timerHandler = new Handler();

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.product_finished, container, false);

        Common._CONTROL_GRD_Finished=(GridView) v.findViewById(R.id.grdKitchenFinished);

        if (!Common._MSG_KhongSuDungTinhNangBep) {
            timerHandler.postDelayed(timerRunnable, 0);
        }
        return v;
    }

    public   Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            CF_Kitchen.getFinished(getContext(), Common._USER_TOKEN);
            timerHandler.postDelayed(this, 1000 * Common._TIME_GET_FINISHED);
        }
    };

    public void removeCallbacks()
    {
        timerHandler.removeCallbacks(timerRunnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timerHandler.removeCallbacks(timerRunnable);
    }

}
