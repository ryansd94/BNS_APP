package Control;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

public class MyButton extends android.support.v7.widget.AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }


    public void setStyleProductGroupButton(Button btn, Context context)
    {
        btn.setAllCaps(false);


        btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.textSize_Small));
        btn.setTextColor(ContextCompat.getColor(context, R.color.color_while));
        btn.setPadding(10,10,10,10);

        Drawable d = getResources().getDrawable(R.drawable.button_productgroup_background);

        btn.setBackground(d);


    }
    public void setHeightButton(Button btn, Context context)
    {
        LinearLayout.LayoutParams param=null;
        if(Common._USE_MOBILE) {
            param =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,Common._PhoneButtonHeight);

        }
        else {
            param =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        param.setMargins(getResources().getDimensionPixelSize(R.dimen.padding_small),
                getResources().getDimensionPixelSize(R.dimen.padding_small),
                getResources().getDimensionPixelSize(R.dimen.padding_small),
                getResources().getDimensionPixelSize(R.dimen.padding_small));
        btn.setLayoutParams(param);

    }

    public void setProductGroupHeightButton(Button btn, Context context)
    {
        FlexboxLayoutManager.LayoutParams param=null;
        if(Common._USE_MOBILE) {
            param=new FlexboxLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,Common._PhoneButtonHeight);
        }
        else
            param =new FlexboxLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        param.setMargins(getResources().getDimensionPixelSize(R.dimen.padding_small),
                getResources().getDimensionPixelSize(R.dimen.padding_small),
                getResources().getDimensionPixelSize(R.dimen.padding_small),
                getResources().getDimensionPixelSize(R.dimen.padding_small));
        param.setFlexGrow(10);
        btn.setLayoutParams(param);

    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        this.setBackgroundColor(Color.RED);
    }
}
