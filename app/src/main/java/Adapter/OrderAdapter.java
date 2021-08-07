package Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pro.admin.atssoft.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Control.MyTextView;
import model.OrderClass;
import model.RoomClass;

public class OrderAdapter extends BaseAdapter {

    private List<OrderClass> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public OrderAdapter(Context context, List<OrderClass> banList) {
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
                            .inflate(R.layout.product_order, null);
            viewImageTextHolder = new ViewImageTextHolder();
            viewImageTextHolder.mProductName = (TextView) convertView.findViewById(
                    R.id.txtName);
            viewImageTextHolder.mNote = (TextView) convertView.findViewById(
                    R.id.txtNote);
            viewImageTextHolder.mPrice = (TextView) convertView.findViewById(
                    R.id.txtPrice);
            viewImageTextHolder.mTotal = (TextView) convertView.findViewById(
                    R.id.txtMoney);
            viewImageTextHolder.mQuantity = (TextView) convertView.findViewById(
                    R.id.txtQuantity);
            viewImageTextHolder.mOrderID = (TextView) convertView.findViewById(
                    R.id.txtOrderID);
            viewImageTextHolder.mProductID = (TextView) convertView.findViewById(
                    R.id.txtProductID);
            viewImageTextHolder.mIsPrioritize = (ImageView) convertView.findViewById(
                    R.id.imgIsPrioritize);
            viewImageTextHolder.mNotIsPrioritize = (ImageView) convertView.findViewById(
                    R.id.imgNotIsPrioritize);
            viewImageTextHolder.mPrioritize = (TextView) convertView.findViewById(
                    R.id.txtPrioritize);


            convertView.setTag(viewImageTextHolder);
        } else {
            viewImageTextHolder = (ViewImageTextHolder) convertView.getTag();
        }
        OrderClass item = mList.get(position);
        String OrderID = item.getID();
        String note = item.getNote();
        String productName = item.getProductName();
        String price = item.getPrice();
        String total = item.getTotalMoney();
        String quantity = item.getQuantity();
        String productID=item.getProductID();
        String isPrioritize=item.getIsPrioritize();

        viewImageTextHolder.mOrderID.setText(OrderID);
        viewImageTextHolder.mNote.setText(note);
        viewImageTextHolder.mProductName.setText(productName);
        viewImageTextHolder.mPrice.setText(price);
        viewImageTextHolder.mTotal.setText(total);
        viewImageTextHolder.mQuantity.setText(quantity);
        viewImageTextHolder.mProductID.setText(productID);
        viewImageTextHolder.mPrioritize.setText(isPrioritize);

        if(Boolean.parseBoolean( isPrioritize)) {
            viewImageTextHolder.mIsPrioritize.setVisibility(View.VISIBLE);
            viewImageTextHolder.mNotIsPrioritize.setVisibility(View.GONE);
        }
        else
        {
            viewImageTextHolder.mIsPrioritize.setVisibility(View.GONE);
            viewImageTextHolder.mNotIsPrioritize.setVisibility(View.VISIBLE);
        }
        MyTextView.setTextNumber(mContext, viewImageTextHolder.mPrice);
        MyTextView.setTextNumber(mContext, viewImageTextHolder.mTotal);
        MyTextView.setTextNumber(mContext, viewImageTextHolder.mQuantity);
        MyTextView.setTextProductOrder(mContext, viewImageTextHolder.mProductName);
        if(position %2==0)
        convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.product_order_background_color));
        else
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.product_order_background_color2));
        return convertView;
    }



    public class ViewImageTextHolder {
        public TextView mProductName;
        public TextView mNote;
        public TextView mQuantity;
        public TextView mPrice;
        public TextView mTotal;
        public TextView mOrderID;
        public TextView mProductID;
        public ImageView mIsPrioritize;
        public ImageView mNotIsPrioritize;
        public TextView mPrioritize;

    }
}
