package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pro.admin.atssoft.R;

public class PayFragment extends Fragment {
    public static PayFragment createInstance(String txt)
    {
        PayFragment fragment = new PayFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pay,container,false);
        return v;
    }
}
