package Control;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

public class MyTextView {




    public static void setTextAreaTitle(Context context, TextView tv) {
        LinearLayout.LayoutParams param;



        if (!Common._USE_MOBILE) {
            param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    context.getResources().getDimensionPixelSize(R.dimen.textarea_height));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.textSize_Large));
        }
        else {
            param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    context.getResources().getDimensionPixelSize(R.dimen.textarea_mobile_height));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.textSize_Medium));
        }
        tv.setTypeface(null, Typeface.BOLD);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(ContextCompat.getColor(context, R.color.color_text_area_background));
        tv.setLayoutParams(param);
    }

    public static void setTextProduct(Context context, TextView tv)
    {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,context. getResources().getDimensionPixelSize(R.dimen.textSize_Small));
        tv.setTypeface(null, Typeface.BOLD);
        tv.setPadding( context.getResources().getDimensionPixelSize(R.dimen.padding_small),0,0,0);
    }

    public static void setTextRoomTitle(Context context, TextView tv)
    {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimensionPixelSize(R.dimen.textSize_Medium));
        tv.setTypeface(null, Typeface.BOLD);
        tv.setGravity(Gravity.CENTER);
    }

    public static void setDialogTextTitle(Context context, TextView tv)
    {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimensionPixelSize(R.dimen.textSize_Medium));
        tv.setTypeface(null, Typeface.BOLD);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(ContextCompat.getColor(context, R.color.color_text_title));

    }



    public static void setTextNumber(Context context, TextView tv)
    {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimensionPixelSize(R.dimen.textSize_Small));
        tv.setTypeface(null, Typeface.BOLD_ITALIC);
    }

    public static void setTextProductOrder(Context context, TextView tv)
    {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimensionPixelSize(R.dimen.textSize_Small));
        tv.setTextColor(ContextCompat.getColor(context, R.color.area_textcolor));

    }
}
