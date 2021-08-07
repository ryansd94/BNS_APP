package Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pro.admin.atssoft.R;

import java.util.List;

import Control.MyTextView;
import model.OrderClass;

public class ProductPaidAdapter extends BaseAdapter {

    private List<OrderClass> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public ProductPaidAdapter(Context context, List<OrderClass> banList) {
        mLayoutInflater = LayoutInflater.from(context);
        mList = banList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewImageTextHolder viewImageTextHolder;

        if (convertView == null) {
            convertView =
                    LayoutInflater.from(mContext)
                            .inflate(R.layout.product_paid, null);
            viewImageTextHolder = new ViewImageTextHolder();
            viewImageTextHolder.mProductName = (TextView) convertView.findViewById(
                    R.id.txtProductName);

            viewImageTextHolder.mPrice = (TextView) convertView.findViewById(
                    R.id.txtPrice);
            viewImageTextHolder.mTotal = (TextView) convertView.findViewById(
                    R.id.txtTotalmoney);
            viewImageTextHolder.mQuantity = (TextView) convertView.findViewById(
                    R.id.txtQuantity);
            viewImageTextHolder.mSTT = (TextView) convertView.findViewById(
                    R.id.txtSTT);

            convertView.setTag(viewImageTextHolder);
        } else {
            viewImageTextHolder = (ViewImageTextHolder) convertView.getTag();
        }
        OrderClass item = mList.get(position);
        String productName = item.getProductName();


        String price = item.getPrice();
        String total = item.getTotalMoney();
        String quantity = item.getQuantity();


        viewImageTextHolder.mProductName.setText(productName);


        viewImageTextHolder.mPrice.setText(price);
        viewImageTextHolder.mTotal.setText(total);
        viewImageTextHolder.mQuantity.setText(quantity);
        viewImageTextHolder.mSTT.setText((position+1) + "");

        MyTextView.setTextNumber(mContext, viewImageTextHolder.mSTT);
        MyTextView.setTextNumber(mContext, viewImageTextHolder.mPrice);
        MyTextView.setTextNumber(mContext, viewImageTextHolder.mTotal);
        MyTextView.setTextNumber(mContext, viewImageTextHolder.mQuantity);
        MyTextView.setTextProductOrder(mContext, viewImageTextHolder.mProductName);

        if (position % 2 == 0)
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_item_le));
        else
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_item_chan));

        return convertView;
    }

    public class ViewImageTextHolder {
        public TextView mProductName;
        public TextView mQuantity;
        public TextView mPrice;
        public TextView mTotal;
        public TextView mSTT;

    }
}
