package fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;

import com.pro.admin.atssoft.APIResult.CF_Order;
import com.pro.admin.atssoft.APIResult.CF_ProductGroup;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import Adapter.ProductAdapter;
import Control.MyEditText;
import Control.MyGridView;

public class Order_ProductListFragment extends Fragment {
    View mRootview;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        mRootview = inflater.inflate(R.layout.order_product_list, container, false);

        final MyEditText edtSearch = mRootview.findViewById(R.id.edtProductSearch);
        //mProductGroupLayout = mRootview.findViewById(R.id.layoutProductGroup);

        Common._CONTROL_GRD_ProductGroup = mRootview.findViewById(R.id.layoutProductGroup);
        Common._CONTROL_GRD_PRODUCT = mRootview.findViewById(R.id.grdProduct);
        MyGridView myGridView = new MyGridView(getActivity());
        myGridView.setGridViewProductMobile(Common._CONTROL_GRD_PRODUCT);
        Common.loadProduct(CF_ProductGroup.createEmpty(getActivity()), "", getContext());
        Common.loadProductGroup(getContext());

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    Common.loadProduct(Common._PRODUCT_GROUP_CLASS, s.toString(), getContext());
                } else
                    Common.loadProduct(Common._PRODUCT_GROUP_CLASS, "", getContext());
            }
        });

        edtSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return Common.clearEditText(event, edtSearch);
            }
        });

        /*
        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //hideSoftKeyboard(v);
                }
            }
        });

         */

        Common._CONTROL_GRD_PRODUCT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductAdapter.ViewImageTextHolder holder = (ProductAdapter.ViewImageTextHolder) view.getTag();
                int iquantity = 0;
                String squantity = holder.mQuantity.getText().toString();
                if (squantity.trim().length() > 0)
                    iquantity = Integer.parseInt(squantity);
                iquantity += 1;
                if (iquantity>0) {
                    holder.mQuantity.setText(iquantity + "");
                    holder.mQuantity.setVisibility(View.VISIBLE);
                }
                boolean isLoadPDOrder = true;
                CF_Order.order(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                        holder.mID.getText().toString(), "1", "", "true", "false",
                        String.valueOf(Common._ROOM_SELECTED_OBJ.getID()), "-1",
                        Common._ROOM_STATUS_SELECTED, isLoadPDOrder,
                        "false");


            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //Common.hideSoftKeyboard(getActivity());
        //setupUI(mRootview);
        return mRootview;
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Common.hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
