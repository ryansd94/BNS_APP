package fragment;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.pro.admin.atssoft.APIResult.CF_Order;
import com.pro.admin.atssoft.APIResult.Common;
import com.pro.admin.atssoft.R;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import Adapter.OrderAdapter;
import Dialog.ChangeTableDialog;
import Dialog.PayDialog;
import info.androidhive.fontawesome.FontTextView;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EncodingUtils;

public class Order_ProductOrderFragment extends Fragment {


    View mRootview;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootview = inflater.inflate(R.layout.order_product_order, container, false);

        if (!Common._REFRESH_LAYOUT) {
            Common._CONTROL_GRD_ProductOrder = mRootview.findViewById(R.id.grdProductOrder);
            Common._CONTROL_TEXT_TextMoney = mRootview.findViewById(R.id.txtTotalmoney);
            Common._CONTROL_TEXT_TextQuantity = mRootview.findViewById(R.id.txtQuantity);
        }
        Common._REFRESH_LAYOUT = true;


        Button btnThanhToan = mRootview.findViewById(R.id.btnThanhToan);
        Button btnChangeRoom = mRootview.findViewById(R.id.btnChangeRoom);
        Button btnGhepBan = mRootview.findViewById(R.id.btnGhepBan);
        Button btnThongBao = mRootview.findViewById(R.id.btnThongBao);
        final Button btnInBill = mRootview.findViewById(R.id.btnInBill);

        if (!Common._MSG_KhongSuDungTinhNangBep)
            if (Common._USER_PERMISSION.contains("orderpage") && Common._USER_LINCESES.contains("kitchenpage"))
                btnThongBao.setVisibility(View.VISIBLE);


        if (Common._USE_MOBILE) {
            btnThanhToan.setLayoutParams(Common._paramsPhoneButtonHeight);
            btnChangeRoom.setLayoutParams(Common._paramsPhoneButtonHeight);
            btnGhepBan.setLayoutParams(Common._paramsPhoneButtonHeight);
            btnThongBao.setLayoutParams(Common._paramsPhoneButtonHeight);
            btnInBill.setLayoutParams(Common._paramsPhoneButtonHeight);
        } else {
            btnThanhToan.setLayoutParams(Common._paramsIpadButtonHeight);
            btnChangeRoom.setLayoutParams(Common._paramsIpadButtonHeight);
            btnGhepBan.setLayoutParams(Common._paramsIpadButtonHeight);
            btnThongBao.setLayoutParams(Common._paramsIpadButtonHeight);
            btnInBill.setLayoutParams(Common._paramsIpadButtonHeight);
        }


        CF_Order.getProductOrder(getActivity(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),
                "1");

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


        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common._CONTROL_PayDialog = new PayDialog();
                CF_Order.getProductPaid(getActivity(), Common._USER_TOKEN, Common._ROOM_SELECTED_OBJ.getID() + "");
            }
        });

        btnInBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnInBill.setEnabled(false);
                CF_Order.printBill(getContext(), Common._USER_TOKEN, String.valueOf(Common._ROOM_SELECTED_OBJ.getID()),"0");


/*
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00-D7-9D-6F-72-46");
                            Method m = device.getClass().getMethod("createRfcommSocket", new Class[] { Integer.TYPE });
                            BluetoothSocket  bSocket = (BluetoothSocket)m.invoke(device, new Object[] { Integer.valueOf(1) });
                            bSocket.connect();
                            OutputStream outStream = bSocket.getOutputStream();


                            String cpclData = "Hóa đơn";
                            outStream.write(cpclData.getBytes());
                            outStream.flush();

                            //inStream = bSocket.getInputStream();


                            Socket sock = new Socket("192.168.1.201", 9100);
                            PrintWriter oStream = new PrintWriter(sock.getOutputStream());


                            byte[] arrayOfByte1 = { 27, 33, 0 };
                            byte[] format = { 27, 33, 0 };

                            // Bold
                            format[2] = ((byte)(0x8 | arrayOfByte1[2]));
                            char[] cc = new char[]{0x1B,0x21,0x00};  // 0- normal size text
                            char[] bb = new char[]{0x1B,0x21,0x08};  // 1- only bold text
                            char[] bb2 = new char[]{0x1B,0x21,0x20}; // 2- bold with medium text
                            char[] bb3 = new char[]{0x1B,0x21,0x10}; // 3- bold with large text

                            oStream.write(bb3);
                            oStream.write(Common._SHOPNAME);
                            oStream.write(cc);

                            String nameFormat = " %1$-20s ";
                            String dateFormat = " %2$ ";
                            String ageFormat = " %3$3s %n";
                            String line = new String(new char[48]).replace('\0', '-');

                            oStream.println(line);
                            oStream.printf("%1$-20s %2$-20s %3$3s %n",
                                    "Tên",
                                    "Ngày sinh",
                                    "Age");
                            //oStream.println(line);
                            oStream.print(EncodingUtils.getBytes("Hóa đơn", "ISO-8859-1"));


                            oStream.println("\n\n\n");
                            oStream.println(new char[]{0x1D, 0x56, 0x41, 0x10});
                            oStream.flush();
                            oStream.close();

                            sock.close();


                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
 */

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
                        final LinearLayout layNote=view.findViewById(R.id.layNote);
                        final LinearLayout layQuantity=view.findViewById(R.id.layQuantity);
                        FontTextView edtPlus=view.findViewById(R.id.btnPlus);
                        FontTextView edtMinus=view.findViewById(R.id.btnMinus);
                        Button btnAccept = view.findViewById(R.id.btnAcccept);
                        Button btnCancel = view.findViewById(R.id.btnCancel);


                        edtMinus.setOnTouchListener( setOnTouch(edtMinus.getTextColors()));
                        edtPlus.setOnTouchListener( setOnTouch(edtPlus.getTextColors()));


                        edtMinus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Integer value = Integer.parseInt(edtQuantity.getText().toString());
                                    if (value > 0)
                                        value = value - 1;
                                    edtQuantity.setText(value.toString());
                                } catch (Exception e) {
                                }
                            }
                        });

                        edtPlus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Integer value = Integer.parseInt(edtQuantity.getText().toString());
                                    value = value + 1;
                                    edtQuantity.setText(value.toString());
                                } catch (Exception e) {
                                }
                            }
                        });

                        if (Common._USE_MOBILE) {
                            btnAccept.setLayoutParams(Common._paramsPhoneButtonHeight);
                            btnCancel.setLayoutParams(Common._paramsPhoneButtonHeight);
                        } else {
                            btnAccept.setLayoutParams(Common._paramsIpadButtonHeight);
                            btnCancel.setLayoutParams(Common._paramsIpadButtonHeight);
                        }

                        builder.setCancelable(false);
                        builder.setView(view);
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

                                layNote.setVisibility(View.GONE);
                                layQuantity.setVisibility(View.VISIBLE);
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
                                layNote.setVisibility(View.VISIBLE);
                                layQuantity.setVisibility(View.GONE);
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
                                                    Common._CONTROL_GRD_ProductOrder);
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
                                        Common._CONTROL_GRD_ProductOrder);
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

        return mRootview;
    }
    private static Object[][] people = {
            {"Alice", 123},
            {"Bob", 456},
            {"Carol", 1111},
            {"Ted", 2222},
    };
    private View.OnTouchListener setOnTouch(final ColorStateList coloOld)
    {
        View.OnTouchListener onIconTouch= new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FontTextView img = (FontTextView) v;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        img.setTextColor(getResources().getColor(R.color.color_icon_click));
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        v.performClick();
                        img.setTextColor(coloOld);
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        };
        return onIconTouch;
    }
}
