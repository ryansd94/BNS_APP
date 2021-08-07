package Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.CF_Order;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.text.DecimalFormat;

import Adapter.ProductPaidAdapter;
import Control.MyTextView;
import Control.MyToast;
import info.androidhive.fontawesome.FontTextView;

public class PayDialog  extends DialogFragment {

    View mRootview;
    ProductPaidAdapter mProductPaidAdapter;
    String mTotalmoneyProduct,mCustomerPaid,  mSale;
    public void setValue(ProductPaidAdapter productPaidAdapter, String TotalmoneyProduct,
                         String CustomerPaid, String Sale) {
        this.mProductPaidAdapter = productPaidAdapter;
        mTotalmoneyProduct=TotalmoneyProduct;
        mCustomerPaid=CustomerPaid;
        mSale=Sale;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        /*
        if (!Common._USE_MOBILE)
            mRootview = inflater.inflate(R.layout.dialog_paid, container, false);
        else
            */

            mRootview = inflater.inflate(R.layout.dialog_paid_phone, container, false);
        TextView txtTitle = mRootview.findViewById(R.id.txtTitle);

        final TextView txtTotalmoneyProduct = mRootview.findViewById(R.id.txtTotalmoneyProduct);
        final TextView txtCustomerPaid = mRootview.findViewById(R.id.txtCustomerPaid);
        final TextView txtSale = mRootview.findViewById(R.id.txtSale);
        final TextView txtExcessCash = mRootview.findViewById(R.id.txtExcessCash);
        final EditText edtCusPay = mRootview.findViewById(R.id.edtCusPay);
        Button btnPaid = mRootview.findViewById(R.id.btnPaid);
        edtCusPay.requestFocus();
        int pos = edtCusPay.getText().length();
        edtCusPay.setSelection(0);
        MyTextView.setDialogTextTitle(getActivity(), txtTitle);

        txtTotalmoneyProduct.setText(mTotalmoneyProduct);
        txtCustomerPaid.setText(mCustomerPaid);
        txtSale.setText(mSale);

        //edtCusPay.findFocus();
        FontTextView imgClosed = mRootview.findViewById(R.id.imgClosed);
        GridView grdProduct = mRootview.findViewById(R.id.grdProductPaid);
        grdProduct.setAdapter(mProductPaidAdapter);
        MyTextView.setDialogTextTitle(getActivity(), txtTitle);
        if (Common._ROOM_SELECTED_OBJ != null) {
            txtTitle.setText(Common._ROOM_SELECTED_OBJ.getTenBan() + "/" + Common._ROOM_SELECTED_OBJ.getAreaName());
        }
        imgClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



        edtCusPay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Integer money = Integer.parseInt(Common.replaceCharMoney(s.toString()))
                            - Integer.parseInt(Common.replaceCharMoney(txtCustomerPaid.getText().toString()));
                    DecimalFormat formatter = new DecimalFormat(Common._MONEY_FORMAT);
                    txtExcessCash.setText(formatter.format(money) + Common._MONEY_UNIT);
                    txtExcessCash.setTag(money + "");
                } catch (Exception ex) {
                }
            }
        });

        btnPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(txtExcessCash.getTag().toString()) < 0 || Integer.parseInt(edtCusPay.getText().toString()) <= 0) {
                    //hideSoftKeyboard();
                    MyToast myToast = new MyToast(getActivity(), Common._FALIED, getActivity().getString(R.string.error_when_cash_paid));
                    myToast.show();
                } else {

                    CF_Order.paid(getActivity(), Common._USER_TOKEN, Common._ROOM_SELECTED_OBJ.getID() + "",
                            "0", Common.replaceCharMoney(txtTotalmoneyProduct.getText().toString()),
                            Common.replaceCharMoney(txtSale.getText().toString()), Common.replaceCharMoney(txtCustomerPaid.getText().toString()),
                            edtCusPay.getText().toString(), Common._ROOM_SELECTED_OBJ.getTenBan(), Common._CONTROL_PayDialog);

                    if(Common._SHOP_PayAndPrintBill)
                    {
                        CF_Order.printBill(getContext(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),Common.replaceCharMoney(txtCustomerPaid.getText().toString()));
                    }
                }
            }
        });

        return mRootview;

    }

    public void hideSoftKeyboard() {
        if (mRootview != null) {

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mRootview.getWindowToken(), 0);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_NoActionBar);
        //hideSoftKeyboard();
    }
}
