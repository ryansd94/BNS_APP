package Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.CF_Order;
import com.pro.admin.atssoft.APIResult.CF_ProductGroup;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.util.ArrayList;

import Adapter.OrderAdapter;
import Adapter.ProductAdapter;
import Control.MyButton;
import Control.MyGridView;
import Control.MyTextView;
import info.androidhive.fontawesome.FontTextView;
import model.ProductClass;

public class OrderDialog  extends DialogFragment {

    View mRootview;
    MyButton mClassButton;
    MyGridView myGridView;
    ArrayList<ProductClass> mDataProduct;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mRootview = inflater.inflate(R.layout.order_dialog, container, false);


        Button btnThanhToan = mRootview.findViewById(R.id.btnThanhToan);
        Button btnChangeRoom = mRootview.findViewById(R.id.btnChangeRoom);
        Button btnGhepBan = mRootview.findViewById(R.id.btnGhepBan);
        Button btnThongBao = mRootview.findViewById(R.id.btnThongBao);
        final Button btnInBill=mRootview.findViewById(R.id.btnInBill);
        if(Common._USE_MOBILE) {
            btnThanhToan.setLayoutParams(Common._paramsPhoneButtonHeight);
            btnChangeRoom.setLayoutParams(Common._paramsPhoneButtonHeight);
            btnGhepBan.setLayoutParams(Common._paramsPhoneButtonHeight);
            btnThongBao.setLayoutParams(Common._paramsPhoneButtonHeight);
            btnInBill.setLayoutParams(Common._paramsPhoneButtonHeight);
        }
        else {
            btnThanhToan.setLayoutParams(Common._paramsIpadButtonHeight);
            btnChangeRoom.setLayoutParams(Common._paramsIpadButtonHeight);
            btnGhepBan.setLayoutParams(Common._paramsIpadButtonHeight);
            btnThongBao.setLayoutParams(Common._paramsIpadButtonHeight);
            btnInBill.setLayoutParams(Common._paramsIpadButtonHeight);
        }


        final EditText edtSearch = mRootview.findViewById(R.id.edtProductSearch);
        TextView txtTitle = mRootview.findViewById(R.id.txtTitle);
        ImageView imgClosed = mRootview.findViewById(R.id.imgClosed);
        Common._CONTROL_TEXT_TextMoney = mRootview.findViewById(R.id.txtTotalmoney);
        Common._CONTROL_TEXT_TextQuantity=mRootview.findViewById(R.id.txtQuantity);
        Common._CONTROL_GRD_ProductOrder  = mRootview.findViewById(R.id.grdProductOrder);
        Common._CONTROL_OrderDialog=this;
        Common._CONTROL_GRD_ProductGroup = mRootview.findViewById(R.id.layoutProductGroup);
        Common._CONTROL_GRD_PRODUCT = mRootview.findViewById(R.id.grdProduct);


        edtSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return Common.clearEditText(event, edtSearch);
            }
        });

        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideSoftKeyboard(v);
                }
            }
        });


        Common._CONTROL_GRD_ProductOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final OrderAdapter.ViewImageTextHolder order = (OrderAdapter.ViewImageTextHolder) view.getTag();

                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                popupMenu.inflate(R.menu.menu_order);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        View view = getActivity().getLayoutInflater().inflate(R.layout.quantity_dialog, null, false);
                        final TextView edtQuantity = view.findViewById(R.id.edtQuantity);
                        final EditText edtNote = view.findViewById(R.id.edtNote);
                        FontTextView edtPlus=view.findViewById(R.id.btnPlus);
                        FontTextView edtMinus=view.findViewById(R.id.btnMinus);
                        Button btnAccept = view.findViewById(R.id.btnAcccept);
                        Button btnCancel = view.findViewById(R.id.btnCancel);
                        builder.setCancelable(false);
                        builder.setView(view);


                        edtMinus.setOnTouchListener( onIconTouch);
                        edtPlus.setOnTouchListener( onIconTouch);
                        final AlertDialog d;


                        switch (item.getItemId()) {
                            case R.id.delete:

                                //Toast.makeText(getActivity(), order.mOrderID.getText().toString(), Toast.LENGTH_SHORT).show();
                                CF_Order.order(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                                        order.mProductID.getText().toString(), "0", "", "false", "false",
                                        String.valueOf(Common._ROOM_SELECTED_OBJ.getTableOrderIndex()), order.mOrderID.getText().toString(),

                                        Common._ROOM_STATUS_SELECTED, false, "false");
                                return true;
                            case R.id.change_quantity:

                                edtNote.setVisibility(View.GONE);
                                edtQuantity.setVisibility(View.VISIBLE);
                                final int iQuantityOld = Integer.parseInt(order.mQuantity.getText().toString());
                                edtQuantity.setText(order.mQuantity.getText());
                                builder.setTitle(R.string.quantity_change);
                                d = builder.show();
                                d.show();

                                btnCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //d.dismiss();
                                        d.cancel();
                                    }
                                });
                                btnAccept.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (iQuantityOld != Integer.parseInt(edtQuantity.getText().toString())) {
                                            CF_Order.order(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                                                    order.mProductID.getText().toString(), edtQuantity.getText().toString(), "", "false", "false",
                                                    String.valueOf(Common._ROOM_SELECTED_OBJ.getTableOrderIndex()), order.mOrderID.getText().toString(),
                                                    Common._ROOM_STATUS_SELECTED, false, "false");
                                        }
                                        d.cancel();
                                    }
                                });
                                return true;
                            case R.id.add_note:
                                edtNote.setVisibility(View.VISIBLE);
                                edtQuantity.setVisibility(View.GONE);
                                final String sNoteOLD = order.mNote.getText().toString();
                                edtNote.append(sNoteOLD);
                                builder.setTitle(R.string.note_change);
                                d = builder.show();
                                d.show();
                                btnCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //d.dismiss();
                                        d.cancel();
                                    }
                                });

                                btnAccept.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (sNoteOLD != edtNote.getText().toString()) {
                                            CF_Order.updateNote(getActivity(), Common._USER_TOKEN, edtNote.getText().toString(),
                                                    order.mOrderID.getText().toString(), String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                                                    Common._CONTROL_GRD_ProductOrder );
                                        }
                                        d.cancel();
                                    }
                                });

                                return true;
                            case R.id.prioritize:
                                String prioritize = "true";
                                if (Boolean.parseBoolean(order.mPrioritize.getText().toString()))
                                    prioritize = "false";
                                CF_Order.updatePrioritize(getActivity(), Common._USER_TOKEN, prioritize,
                                        order.mOrderID.getText().toString(), String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                                        Common._CONTROL_GRD_ProductOrder );
                                return true;
                            default:
                                return false;
                        }


                    }
                });

                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        //Toast.makeText(getActivity(),"aaaa",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        btnGhepBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTableDialog dialog = new ChangeTableDialog();
                dialog.setCancelable(false);
                dialog.setValue(Common._ROOM_COLLECT, dialog);
                dialog.show(Common._FRAGMENTMANAGER, "");

            }
        });


        btnChangeRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTableDialog dialog = new ChangeTableDialog();
                dialog.setCancelable(false);
                dialog.setValue(Common._ROOM_CHANGE, dialog);
                dialog.show(Common._FRAGMENTMANAGER, "");


            }
        });

        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CF_Order.notify(getContext(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()));
            }
        });


        btnInBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CF_Order.printBill(getContext(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),"0");
            }
        });

        //  CF_Order.getProductOrder(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()), "1", mLstProductOrder, grdProductOrder,txtMoney,mViewRoom);
        CF_Order.getProductOrder(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                "1" );

        mClassButton = new MyButton(getActivity());
        //mProductGroupLayout = mRootview.findViewById(R.id.layoutProductGroup);
        myGridView = new MyGridView(getActivity());


        MyGridView myGridView=new MyGridView(getActivity());
        myGridView.setGridViewProductMobile(Common._CONTROL_GRD_PRODUCT);


        MyTextView.setDialogTextTitle(getActivity(), txtTitle);

        mDataProduct = new ArrayList<ProductClass>();
        if (Common._ROOM_SELECTED_OBJ != null) {
            txtTitle.setText(Common._ROOM_SELECTED_OBJ.getTenBan() + "/" + Common._ROOM_SELECTED_OBJ.getAreaName());
        }
        //loadProductGroup();

        Common.loadProduct(CF_ProductGroup.createEmpty(getActivity()), "",getContext());
        Common.loadProductGroup(getContext());



        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common._CONTROL_PayDialog = new PayDialog();
                CF_Order.getProductPaid(getActivity(), Common._USER_TOKEN, Common._ROOM_SELECTED_OBJ.getID() + "");
            }
        });




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
                    Common.loadProduct(Common._PRODUCT_GROUP_CLASS, s.toString(),getContext());
                } else
                    Common.loadProduct(Common._PRODUCT_GROUP_CLASS, "",getContext());
            }
        });


        Common._CONTROL_GRD_PRODUCT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductAdapter.ViewImageTextHolder holder = (ProductAdapter.ViewImageTextHolder) view.getTag();
                boolean isLoadPDOrder = true;

                CF_Order.order(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                        holder.mID.getText().toString(), "1", "", "true", "false",
                        String.valueOf(Common._ROOM_SELECTED_OBJ.getID()), "-1",
                       Common._ROOM_STATUS_SELECTED, isLoadPDOrder, "false");

                //CF_Room.getRoomOrder(getContext(),Common._USER_TOKEN,String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),mViewRoom);
            }
        });

        imgClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Common.hideSoftKeyboard(getActivity());

        return mRootview;
    }

    private View.OnTouchListener onIconTouch= new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            FontTextView img=(FontTextView)v;
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    img.setTextColor(getResources().getColor(R.color.color_icon_click));
                    return true; // if you want to handle the touch event
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    img.setTextColor(getResources().getColor(R.color.color_background_title));
                    return true; // if you want to handle the touch event
            }
            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_NoActionBar);
        Common.hideSoftKeyboard(getActivity());
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
