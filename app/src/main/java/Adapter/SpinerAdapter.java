package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pro.admin.atssoft.R;

import java.util.List;


import model.SpinerClass;

public class SpinerAdapter extends BaseAdapter {

    private List<SpinerClass> mList;
    private LayoutInflater mLayoutInflater;

    public SpinerAdapter(Context context, List<SpinerClass> listData) {
        mLayoutInflater = LayoutInflater.from(context);
        mList = listData;
    }

    @Override
    public int getCount()  {
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

        if (convertView == null) {
            convertView =
                    mLayoutInflater
                            .inflate(R.layout.spiner_item, null);
            viewImageTextHolder = new ViewHolder();
            viewImageTextHolder.mValue = (TextView) convertView.findViewById(
                    R.id.txtValue);
            viewImageTextHolder.mName = (TextView) convertView.findViewById(
                    R.id.txtName);
            convertView.setTag(viewImageTextHolder);
        } else {
            viewImageTextHolder = (ViewHolder) convertView.getTag();
        }
        SpinerClass city = mList.get(position);
        String value = city.getValue();
        String name = city.getName();
        viewImageTextHolder.mValue.setText(value);
        viewImageTextHolder.mName.setText(name);
        return convertView;
    }

    public class ViewHolder {
        public TextView mValue;
        public TextView mName;
    }
}
