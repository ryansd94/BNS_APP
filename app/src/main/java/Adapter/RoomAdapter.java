package Adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Control.MyImageView;
import model.RoomClass;

public class RoomAdapter extends BaseAdapter {
    private List<RoomClass> mList;
    private LayoutInflater mLayoutInflater;
    private static Context mContext;

    public RoomAdapter(Context context, List<RoomClass> banList) {
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
        //return Integer.parseInt(mList.get(position)._ID);
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewImageTextHolder viewImageTextHolder;

        if (convertView == null) {
            convertView =
                    mLayoutInflater
                            .inflate(R.layout.dsban_item, null);
            viewImageTextHolder = new ViewImageTextHolder();
            viewImageTextHolder.mID = (TextView) convertView.findViewById(
                    R.id.text_id_dsban_item);
            viewImageTextHolder.mTenBan = (TextView) convertView.findViewById(
                    R.id.text_name_dsban_item);
            viewImageTextHolder.mTableOrderID = (TextView) convertView.findViewById(
                    R.id.text_id_tableorderindex);
            viewImageTextHolder.mImageBan = (ImageView) convertView.findViewById(
                    R.id.image_dsban_item);
            viewImageTextHolder.mAreaName = (TextView) convertView.findViewById(
                    R.id.text_area_name);
            viewImageTextHolder.mAreaID = (TextView) convertView.findViewById(
                    R.id.text_area_id);

            if (Common._USE_MOBILE)
                viewImageTextHolder.mTenBan.setTextSize(TypedValue.COMPLEX_UNIT_PX,mContext.getResources().getDimensionPixelSize(R.dimen.textSize_Small));
            else
                viewImageTextHolder.mTenBan.setTextSize(TypedValue.COMPLEX_UNIT_PX,mContext.getResources().getDimensionPixelSize(R.dimen.textSize_Small));
            convertView.setTag(viewImageTextHolder);
        } else {
            viewImageTextHolder = (ViewImageTextHolder) convertView.getTag();
        }

        RoomClass room = mList.get(position);
        setViewHolder(viewImageTextHolder, room);
        MyImageView myImageView=new MyImageView(mContext);
        myImageView.setImageRoom(viewImageTextHolder.mImageBan);
        return convertView;
    }
    public static void setViewHolder(ViewImageTextHolder view,RoomClass room)
    {
        Integer idBan = room.getID();
        String tenBan = room.getTenBan();
        String hinhBan = room.getHinhAnh();
        String areaName=room.getAreaName();
        Integer tableOrderID=room.getTableOrderIndex();
        Integer areaID= room.getAreaID();
        int branchID=room.getBranchID();
        view.mTableOrderID.setText(tableOrderID +"");
        view.mID.setText(idBan +"");
        view.mTenBan.setText(tenBan);
        view.mAreaName.setText(areaName);
        view.mAreaID.setText(areaID+"");
        view.mStatus=room.getStatus();
        view.mBranchID=branchID;
        if(view.mStatus==0)
            view.mTenBan.setTextColor(mContext.getResources().getColor(R.color.roomempty_textcolor));
        else
            view.mTenBan.setTextColor(mContext.getResources().getColor(R.color.roomused_textcolor));
        Picasso.get().load(hinhBan).into(view.mImageBan);
    }

    public class ViewImageTextHolder {
        public TextView mID;
        public TextView mTenBan;
        public TextView mTableOrderID;
        public ImageView mImageBan;
        public TextView mAreaName;
        public TextView mAreaID;
        public int mStatus;
        public int mBranchID;
    }
}
