package Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Control.MyTextView;
import model.ProductClass;

public class ProductAdapter extends BaseAdapter {

    private List<ProductClass> mList;
    private LayoutInflater mLayoutInflater;
Context mContext;
    public ProductAdapter(Context context, List<ProductClass> banList) {
        mLayoutInflater = LayoutInflater.from(context);
        mList = banList;
        mContext=context;
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
        //Toast.makeText(mContext,"load",Toast.LENGTH_SHORT).show();
        ViewImageTextHolder viewImageTextHolder;

        if (convertView == null) {
            convertView =
                    mLayoutInflater
                            .inflate(R.layout.product_item_type_list, null);
            viewImageTextHolder = new ViewImageTextHolder();
            viewImageTextHolder.mID = (TextView) convertView.findViewById(
                    R.id.txtProductID);
            viewImageTextHolder.mName = (TextView) convertView.findViewById(
                    R.id.txtProductName);
            viewImageTextHolder.mCode = (TextView) convertView.findViewById(
                    R.id.txtProductCode);
            viewImageTextHolder.mPrice = (TextView) convertView.findViewById(
                    R.id.txtPrice);
            viewImageTextHolder.mQuantity = (TextView) convertView.findViewById(
                    R.id.txtQuantity);

            viewImageTextHolder.mImage = (ImageView) convertView.findViewById(
                    R.id.image_product_item);


            convertView.setTag(viewImageTextHolder);
        } else {
            viewImageTextHolder = (ViewImageTextHolder) convertView.getTag();
        }

        ProductClass item = mList.get(position);
        Integer idBan = item.getID();
        String Name = item.getNameView();
        String Image = item.getImage();
        String Code = item.getCode();
        String price=item.getPrice();
        MyTextView.setTextProduct(mContext,viewImageTextHolder.mName);
        viewImageTextHolder.mID.setText(idBan + "");
        viewImageTextHolder.mName.setText(Name);
        viewImageTextHolder.mCode.setText(Code);
        viewImageTextHolder.mPrice.setText(price);
        viewImageTextHolder.mQuantity.setText("");
        Picasso.get().load( Image).into(viewImageTextHolder.mImage);


        if (position % 2 == 0)
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_item_le));
        else
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_item_chan));

        return convertView;
    }

    public class ViewImageTextHolder {
        public TextView mID;
        public TextView mName;
        public TextView mCode;
        public TextView mPrice;
        public TextView mQuantity;
        public ImageView mImage;
    }
}
