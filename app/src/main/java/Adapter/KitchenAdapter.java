package Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.CF_Kitchen;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.util.List;


import model.KitchenClass;

public class KitchenAdapter extends BaseAdapter {


    private List<KitchenClass> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    int mType;

    public KitchenAdapter(Context context, List<KitchenClass> banList, int Type) {
        mLayoutInflater = LayoutInflater.from(context);
        mList = banList;
        mContext = context;
        //0: chờ chế biến, 1: đang chế biến, 3: đã xong
        mType = Type;
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
        KitchenAdapter.ViewHolder viewImageTextHolder;
        if (convertView == null) {
            if(!Common._USE_MOBILE)
            convertView =
                    mLayoutInflater
                            .inflate(R.layout.kitchen_wailting_item, null);
            else
                convertView =
                        mLayoutInflater
                                .inflate(R.layout.kitchen_wailting_item_phone, null);

            viewImageTextHolder = new KitchenAdapter.ViewHolder();
            viewImageTextHolder.mProductName = (TextView) convertView.findViewById(
                    R.id.txtProductName);
            viewImageTextHolder.mIndex = (TextView) convertView.findViewById(
                    R.id.txtIndex);
            viewImageTextHolder.mTableName = (TextView) convertView.findViewById(
                    R.id.txtRoomName);
            viewImageTextHolder.mQuantity = (TextView) convertView.findViewById(
                    R.id.txtQuantity);
            viewImageTextHolder.mTime = (TextView) convertView.findViewById(
                    R.id.txtTime);
            viewImageTextHolder.mNote = (TextView) convertView.findViewById(
                    R.id.txtNote);
            viewImageTextHolder.mOne = (Button) convertView.findViewById(
                    R.id.btnOne);
            viewImageTextHolder.mAll = (Button) convertView.findViewById(
                    R.id.btnAll);
            convertView.setTag(viewImageTextHolder);
        } else {
            viewImageTextHolder = (KitchenAdapter.ViewHolder) convertView.getTag();
        }

        KitchenClass kitchen = mList.get(position);


        viewImageTextHolder.mProductName.setText(kitchen.getProductName());
        viewImageTextHolder.mIndex.setText(kitchen.getIndex());
        viewImageTextHolder.mTableName.setText(kitchen.getTableName());
        viewImageTextHolder.mQuantity.setText(kitchen.getQuantity());
        viewImageTextHolder.mTime.setText(kitchen.getTime());
        viewImageTextHolder.mAll.setTag(kitchen);
        viewImageTextHolder.mOne.setTag(kitchen);
        if (kitchen.getNote().length() > 0)
            viewImageTextHolder.mNote.setText("(" + kitchen.getNote() + ")");

        else
            viewImageTextHolder.mNote.setVisibility(View.GONE);

        if (!Common._USER_PERMISSION.contains("kitchenpage") && mType != 2) {
            viewImageTextHolder.mAll.setVisibility(View.GONE);
            viewImageTextHolder.mOne.setVisibility(View.GONE);
        } else {
            viewImageTextHolder.mAll.setVisibility(View.VISIBLE);
            viewImageTextHolder.mOne.setVisibility(View.VISIBLE);
        }
        if (position % 2 == 0)
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_item_le));
        else
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_item_chan));

        if (mType == 0)
            viewImageTextHolder.mAll.setBackground(mContext.getDrawable(R.drawable.button_kitchen_left_chuyenall));
        else
            viewImageTextHolder.mAll.setBackground(mContext.getDrawable(R.drawable.button_kitchen_right_chuyenall));
        viewImageTextHolder.mAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KitchenClass vp = (KitchenClass) v.getTag();
                if (mType == 0)
                    CF_Kitchen.waitProcess(mContext, Common._USER_TOKEN, vp.getIndex(), vp.getQuantity());
                else if (mType == 1)
                    CF_Kitchen.done(mContext, Common._USER_TOKEN, vp.getIndex(), vp.getQuantity());
                else if (mType == 2)
                    CF_Kitchen.finished(mContext, Common._USER_TOKEN, vp.getIndex(), vp.getQuantity());
            }
        });

        viewImageTextHolder.mOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KitchenClass vp = (KitchenClass) v.getTag();
                if (mType == 0)
                    CF_Kitchen.waitProcess(mContext, Common._USER_TOKEN, vp.getIndex(), "1");
                else if (mType == 1)
                    CF_Kitchen.done(mContext, Common._USER_TOKEN, vp.getIndex(), "1");
                else if (mType == 2)
                    CF_Kitchen.finished(mContext, Common._USER_TOKEN, vp.getIndex(), "1");
            }
        });

        return convertView;
    }

    public class ViewHolder {
        public TextView mProductName;
        public TextView mIndex;
        public TextView mTableName;
        public TextView mQuantity;
        public TextView mTime;
        public TextView mNote;
        public TextView mPrioritize;
        public Button mAll;
        public Button mOne;
    }
}
