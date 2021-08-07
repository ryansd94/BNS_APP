package Control;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

public class MyToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    Context context;
    String mValue;
    public MyToast(Context context,int Type,String value) {
        super(context);
        this.context = context;
        mValue=value;
        this.setDuration(LENGTH_SHORT);
        if (Type == Common._SUCCESS)
            setViewSuccess();
        else
            setViewError();
    }

    private void setViewSuccess() {
        View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
        TextView txtValue=view.findViewById(R.id.txtValue);
        txtValue.setText(mValue);
        //view.setBackgroundColor(ContextCompat.getColor(context,R.color.color_success));
        view.setBackground(context.getDrawable(R.drawable.toast_success_background));
        this.setView(view);
    }
    private void setViewError() {
        View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
        TextView txtValue=view.findViewById(R.id.txtValue);
        txtValue.setText(mValue);
        //view.setBackgroundColor(ContextCompat.getColor(context,R.color.colorError));
        view.setBackground(context.getDrawable(R.drawable.toast_error_background));
        this.setView(view);
    }
}
