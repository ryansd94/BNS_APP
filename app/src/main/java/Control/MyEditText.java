package Control;

import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.pro.admin.atssoft.R;

public class MyEditText extends EditText {
    public MyEditText(Context context) {
        super(context);
        this.setBackground(getResources().getDrawable(R.drawable.edittext_round));
    }
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackground(getResources().getDrawable(R.drawable.edittext_round));
        this.setPadding(getResources().getDimensionPixelSize(R.dimen.padding_small),getResources().getDimensionPixelSize(R.dimen.padding_small),
                getResources().getDimensionPixelSize(R.dimen.padding_small),getResources().getDimensionPixelSize(R.dimen.padding_small));

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //this.setLayoutParams(params);

    }


}
