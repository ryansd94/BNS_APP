package Control;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.GridView;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

public class MyGridView extends GridView {
    public MyGridView(Context context) {

        super(context);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public void setGridViewRoom(GridView gv) {
        gv.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.MATCH_PARENT));
        if (!Common._USE_MOBILE) {
            if (!Common._USE_VER)
                gv.setNumColumns(Common._GRD_ROOM_IPAD_HOR_COLUMN_NUMBER);
            else
                gv.setNumColumns(Common._GRD_ROOM_IPAD_VER_COLUMN_NUMBER);
        }
        else
            gv.setNumColumns(Common._GRD_ROOM_MOBILE_COLUMN_NUMBER);
        gv.setVerticalSpacing(getResources().getDimensionPixelSize(R.dimen.padding_small));
        gv.setHorizontalSpacing(getResources().getDimensionPixelSize(R.dimen.padding_small));
        //gv.setColumnWidth(getResources().getDimensionPixelSize(R.dimen.image_ban_column_size_width));
        gv.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        //gv.setGravity(Gravity.CENTER);
        gv.setVerticalScrollBarEnabled(false);
        gv.setPadding(0,
                getResources().getDimensionPixelSize(R.dimen.padding_small
                ),
               0,
                getResources().getDimensionPixelSize(R.dimen.padding_small));

    }


    public void setGridViewProductMobile(GridView gv) {
        if (Common._USE_MOBILE)
            gv.setNumColumns(Common._GRD_PRODUCT_MOBILE_COLUMN_NUMBER);
        else {
            if (!Common._USE_VER)
                gv.setNumColumns(Common._GRD_PRODUCT_IPAD_HOR_COLUMN_NUMBER);
            else
                gv.setNumColumns(Common._GRD_PRODUCT_IPAD_VER_COLUMN_NUMBER);
        }
    }
}
