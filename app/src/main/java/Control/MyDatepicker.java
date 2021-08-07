package Control;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.pro.admin.atssoft.R;

public class MyDatepicker extends android.support.v7.widget.AppCompatTextView {
    public MyDatepicker(Context context) {
        super(context);
    }
    public MyDatepicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackground(getResources().getDrawable(R.drawable.datepicker_round));
        this.setPadding(getResources().getDimensionPixelSize(R.dimen.padding_small),getResources().getDimensionPixelSize(R.dimen.padding_small),
                getResources().getDimensionPixelSize(R.dimen.padding_small),getResources().getDimensionPixelSize(R.dimen.padding_small));
    }
}
