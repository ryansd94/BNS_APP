package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.util.ArrayList;

import model.ProductGroupClass;

public class ProductGroupAdapter extends RecyclerView.Adapter<ProductGroupAdapter.ViewHolder> {
    private ArrayList<ProductGroupClass> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public ProductGroupAdapter(Context context, ArrayList<ProductGroupClass> list)
    {
        mLayoutInflater = LayoutInflater.from(context);
        mList=list;
        mContext=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = mLayoutInflater.inflate(R.layout.product_group_item, viewGroup, false);
        final TextView txtID = (TextView) itemView.findViewById(R.id.txtID);
        final Button btnName = (Button) itemView.findViewById(R.id.btnName);

        //btnName.setTextSize(Common._PhoneButtonHeight);
        LinearLayout.LayoutParams param = Common.getParamButton();
        btnName.setLayoutParams(param);



        ViewHolder ret = new ViewHolder(itemView);
        itemView.setTag(ret);
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProductGroupClass productGroupClass = mList.get(i);
        viewHolder.getButtonName().setText(productGroupClass.getName());
        viewHolder.getTextID().setText(productGroupClass.getID() + "");
        viewHolder.getButtonName().setTag(mList.get(i));
        viewHolder.getButtonName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common._PRODUCT_GROUP_CLASS = (ProductGroupClass) v.getTag();
                Common.loadProduct(Common._PRODUCT_GROUP_CLASS, "", mContext);
                ViewParent parent= v.getParent();
                ViewGroup row = (ViewGroup) parent.getParent();
                for (int itemPos = 0; itemPos < row.getChildCount(); itemPos++) {
                    View view = row.getChildAt(itemPos);
                    ViewHolder holder=(ViewHolder) view.getTag();
                    holder.mBtnName.setBackground(mContext.getDrawable( R.drawable.button_productgroup_background));
                }
                v.setBackground(mContext.getDrawable( R.drawable.button_click_background));

            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mID;
        public Button mBtnName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(itemView!=null) {
                this.mID = itemView.findViewById(R.id.txtID);
                this.mBtnName = itemView.findViewById(R.id.btnName);
            }
        }

        public TextView getTextID() {
            return mID;
        }

        public TextView getButtonName() {
            return mBtnName;
        }
    }
}
