package Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.util.ArrayList;

import model.CategoryClass;

public class CategoryAdapter extends BaseAdapter {

    private ArrayList<CategoryClass> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private Common.categoryType mType;

    public CategoryAdapter(Context context, ArrayList<CategoryClass> banList, Common.categoryType type) {
        mLayoutInflater = LayoutInflater.from(context);
        mList = banList;
        mContext=context;
        mType=type;
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
        ViewHolder viewImageTextHolder;

        CategoryClass area = mList.get(position);
        int id = area.getID();
        String Name = area.getName();

        if (convertView == null) {
            convertView =
                    mLayoutInflater
                            .inflate(R.layout.area_item, null);
            viewImageTextHolder = new ViewHolder();
            viewImageTextHolder.mID = (TextView) convertView.findViewById(
                    R.id.txtAreaID);
            viewImageTextHolder.mCkcArea = (CheckBox) convertView.findViewById(
                    R.id.ckcArea);
            convertView.setTag(viewImageTextHolder);
        } else {
            viewImageTextHolder = (ViewHolder) convertView.getTag();
        }

        viewImageTextHolder.mID.setText(id + "");
        viewImageTextHolder.mCkcArea.setText(Name);

        if (position % 2 == 0)
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_item_le));
        else
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_item_chan));

        ArrayList<CategoryClass> lstSelected = new ArrayList<>();

        if (mType == Common.categoryType.AREA) {
            lstSelected = Common._LIST_AREA_SELECTED;
        } else
            lstSelected = Common._LIST_BRANCH_SELECTED;
        if(lstSelected != null && lstSelected.size() >0) {
            for (CategoryClass item : lstSelected) {
                if (item.getID() == area.getID()) {
                    viewImageTextHolder.mCkcArea.setChecked(true);
                    break;
                }
            }
        }
        else
        {
            for (CategoryClass item : Common._LIST_BRANCH) {
                if (item.getID() == area.getID()) {
                    viewImageTextHolder.mCkcArea.setChecked(true);
                    break;
                }
            }
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView mID;
        public CheckBox mCkcArea;
    }
}
