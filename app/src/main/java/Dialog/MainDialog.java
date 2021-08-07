package Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pro.admin.atssoft.R;

public class MainDialog extends Dialog {
    private Activity mActivity;
    public MainDialog(Activity activity) {
        super(activity, android.R.style.Theme_Translucent);
        mActivity = activity;

        //Customize your dialog here
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = mActivity.getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        if(item.getItemId()==R.id.action_setting){
            //do whatever you want
        }
        return super.onOptionsItemSelected(item);
    }
}
