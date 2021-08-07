package Dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.CF_Order;
import com.pro.admin.atssoft.APIResult.CF_Room;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.util.ArrayList;

import Adapter.SpinerAdapter;
import Control.MyButton;
import Control.MyTextView;
import Control.MyToast;
import model.CategoryClass;
import model.SpinerClass;

public class ChangeTableDialog extends DialogFragment {
    View mRootview;
    String mKey;
    ChangeTableDialog mDialog;

    public void setValue(String key,  ChangeTableDialog changeTableDialog) {
        mKey = key;
        mDialog = changeTableDialog;
    }

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootview = getActivity().getLayoutInflater().inflate(R.layout.change_room_dialog, null, false);
        final Button btnAccept = mRootview.findViewById(R.id.btnAcccept);
        final Button btnCancel = mRootview.findViewById(R.id.btnCancel);
        final Spinner spiArea = mRootview.findViewById(R.id.spiArea);
        final Spinner spiRoom = mRootview.findViewById(R.id.spiRoom);
        TextView txtTitle = mRootview.findViewById(R.id.txtTitle);

        MyButton myButton=new MyButton(getActivity());
        myButton.setHeightButton(btnAccept,getActivity());
        myButton.setHeightButton(btnCancel,getActivity());
        MyTextView.setTextRoomTitle(getActivity(), txtTitle);

        if (mKey == Common._ROOM_CHANGE)
            txtTitle.setText(R.string.change_room);
        else
            txtTitle.setText(R.string.collect_room);
        ArrayList<SpinerClass> lstSpiner = new ArrayList<SpinerClass>();

        for (CategoryClass area : Common._LIST_AREA) {
            SpinerClass spinerClass = new SpinerClass();
            spinerClass.setName(area.getName());
            spinerClass.setValue(area.getID() + "");
            lstSpiner.add(spinerClass);
        }

        SpinerAdapter spinerAdapter = new SpinerAdapter(getActivity(), lstSpiner);
        spiArea.setAdapter(spinerAdapter);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //d.dismiss();
                dismiss();
            }
        });


        spiArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinerAdapter.ViewHolder viewHolder = (SpinerAdapter.ViewHolder) view.getTag();
                if (mKey == Common._ROOM_COLLECT) {
                    CF_Room.getAllByAreaIDAndStatus(getActivity(), Common._USER_TOKEN,
                            Integer.parseInt(viewHolder.mValue.getText().toString()), Common._ROOM_STATUS_USED, spiRoom);
                } else {
                    CF_Room.getAllByAreaIDAndStatus(getActivity(), Common._USER_TOKEN,
                            Integer.parseInt(viewHolder.mValue.getText().toString()), Common._ROOM_STATUS_EMPTY, spiRoom);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKey == Common._ROOM_COLLECT) {
                    View view = (View) spiRoom.getSelectedView();
                    if (view == null) {
                        MyToast myToast = new MyToast(getActivity(), Common._FALIED, getActivity().getString(R.string.error_selected_room));
                        myToast.show();
                        return;
                    }
                    SpinerAdapter.ViewHolder viewHolder = (SpinerAdapter.ViewHolder) view.getTag();
                    CF_Order.changeTable(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                            viewHolder.mValue.getText().toString(), Common._ROOM_COLLECT,
                            Common._ROOM_STATUS_SELECTED, mDialog);
                } else {
                    View view = (View) spiRoom.getSelectedView();
                    if (view == null) {
                        MyToast myToast = new MyToast(getActivity(), Common._FALIED, getActivity().getString(R.string.error_selected_room));
                        myToast.show();
                        return;
                    }
                    SpinerAdapter.ViewHolder viewHolder = (SpinerAdapter.ViewHolder) view.getTag();
                    CF_Order.changeTable(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                            viewHolder.mValue.getText().toString(), Common._ROOM_CHANGE, Common._ROOM_STATUS_SELECTED, mDialog);

                }

                //Toast.makeText(getActivity(), viewHolder.mValue.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        return mRootview;
    }
}



