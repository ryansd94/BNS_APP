package com.pro.admin.atssoft.APIResult;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.design.internal.FlowLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.pro.admin.atssoft.MainActivity;
import com.pro.admin.atssoft.R;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.pchmn.materialchips.ChipsInput;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

import Adapter.ProductAdapter;
import Adapter.ProductGroupAdapter;
import Adapter.RoomAdapter;
import Control.MyDatepicker;
import Control.MyGridView;
import Control.MyTextView;
import Control.MyToast;
import Dialog.OrderDialog;
import Dialog.OrderDialog_Phone;
import Dialog.PayDialog;
import Dialog.UpdateDialog;
import model.CategoryClass;
import model.ConfigClass;
import model.OrderClass;
import model.ProductClass;
import model.ProductGroupClass;
import model.RoomClass;

public class Common {

    public static String _IMG_USED = "/Images/tbused.png";
    public static String _IMG_EMPTY = "/Images/tbempty.png";

    public static String _IMG_PRODUCT = "/IMG_Product/";
    public static String _API_Link = "http://phanmem.giaiphapantam.com";
    public static String _SERVICE_PRINT_IP="";
    public static int _SERVICE_PRINT_PORT=0;
    public static String _PRINTER_IP="192.168.1.201";
    public static int _PRINTER_PORT=9100;
    public static String _USER_TOKEN = "";
    public static String _PACKAGENAME = "";
    public static String _USER_USERNAME = "";
    public static String _USER_NAME = "";
    public static String _PRINT_NOTE = "";
    public static String _USER_SHOPCODE = "";
    public static int _ROOM_STATUS_EMPTY = 0;
    public static int _ROOM_STATUS_USED = 1;
    public static RoomClass _ROOM_SELECTED_OBJ;
    public static ProductGroupClass _PRODUCT_GROUP_CLASS;
    public static int _ROOM_SELECTED = 0;
    public static int _ROOM_STATUS_SELECTED = -1;
    public static CategoryClass _AREA_CLASS=null;

    public static String _ROOM_CHANGE = "0";
    public static String _ROOM_COLLECT = "1";

    public static int _SUCCESS = 0;
    public static int _FALIED = 1;

    public static String _MONEY_FORMAT = "#,###,###,###";
    public static String _MONEY_UNIT = "";
    public static String _SHOPNAME="";

public static int _SHOP_PRINTTYPE=0;
    public static boolean _SHOP_PayAndPrintBill=false;


    /*
    public static boolean _USER_ORDER = false;
    public static boolean _USER_KITCHEN = false;



     */


   public static Timer __TimerLoadRoom =null;
    public static  ArrayList<String> _USER_PERMISSION=new ArrayList<>();
    public static  ArrayList<String> _USER_LINCESES=new ArrayList<>();

    public static boolean _USER_ADMIN = false;
    public static boolean _MSG_KhongSuDungTinhNangBep = false;

    public static void setApiLink(String APILink) {
        if (APILink.length() > 0)
            _API_Link = APILink;
    }

    static final Handler handler = new Handler();

    public static boolean CannotConnectSystem = false;
    public static ArrayList<ProductGroupClass> _PRODUCT_GROUP;
    public static ArrayList<OrderClass> _ORDER_DATA;
    public static  ArrayList<CategoryClass> _LIST_AREA=null;
    public static  ArrayList<CategoryClass> _LIST_BRANCH=null;
    public static  ArrayList<CategoryClass> _LIST_AREA_SELECTED=null;
    public static  ArrayList<CategoryClass> _LIST_BRANCH_SELECTED=null;
    public static  ArrayList<Integer> _LIST_AREA_SELECTED_ID=null;
    public static  ArrayList<Integer> _LIST_BRANCH_SELECTED_ID=null;
    public static ConfigClass _CONFIG=null;


    public static int[] _COLOR_REPORT={Color.RED,Color.YELLOW,Color.BLUE,Color.BLACK};


    public static CombinedChart _CHART_DOANHTHU=null;
    public static CombinedChart _CHART_SOLUONGKHACH=null;
    public static String _HTTPSTATUS_ERROR_USER = "401";
    public static String _HTTPSTATUS_ERROR_PROCESS = "400";


    public static  RecyclerView _CONTROL_GRD_ProductGroup=null;
    public static GridView _CONTROL_GRD_PRODUCT=null;
    public static GridView _CONTROL_GRD_WaitProcess = null;
    public static GridView _CONTROL_GRD_UnProcess = null;
    public static GridView _CONTROL_GRD_Finished = null;
    public static FlowLayout _CONTROL_GvArea = null;
    public static GridView _CONTROL_GRD_ProductOrder = null;
    public static TextView _CONTROL_TEXT_TextMoney = null;
    public static TextView _CONTROL_TEXT_TextQuantity = null;

    public static TextView _CONTROL_TEXT_txtSLDonDaXong = null;
    public static TextView _CONTROL_TEXT_txtSoTienDonDaXong = null;
    public static TextView _CONTROL_TEXT_txtSLChoThanhToan = null;
    public static TextView _CONTROL_TEXT_txtSoTienDonChoThanhToan = null;
    public static TextView _CONTROL_TEXT_txtSLBanTrong = null;
    public static TextView _CONTROL_TEXT_txtSLBanCoKhach = null;

    public static  RoomAdapter.ViewImageTextHolder _CONTROL_VIEW_ROOM=null;
    public static OrderDialog _CONTROL_OrderDialog = null;
    public static OrderDialog_Phone _CONTROL_OrderDialogPhone = null;
public static PayDialog _CONTROL_PayDialog;
    public static boolean _REFRESH_LAYOUT=false;

   // public static TextView _CONTROL_TxtAreaName = null;
    //public static MyGridView _CONTROL_GRD_Room = null;

    public static LinearLayout _LAYOUT_ROOM = null;

    public static ChipsInput _CHIPS_AREA=null;

    public static int _TIME_GET_UNPROCESS=30;
    public static int _TIME_GET_PROCESS=30;
    public static int _TIME_GET_FINISHED =15;

    public static int _NOTIFY_PRODUCT_DONE_COUNT=0;
    public static int _NOTIFY_PRODUCT_DONE_COUNT_NEW=0;
    public static Integer _NOTIFY_PRODUCT_DONE_TIMESTAMP=0;
    public static TextView _NOTIFY_PRODUCT_DONE_BUTTON=null;

    public static boolean _IS_FIRST_LOGON=true;
    public static boolean _USE_MOBILE=false;
    public static boolean _USE_VER=false;

    public static FragmentManager _FRAGMENTMANAGER=null;

    public static  int _IpadButtonHeight = 0;
    public static  int _PhoneButtonHeight =0;


    public static LinearLayout.LayoutParams _paramsPhoneButtonHeight=null;
    public static LinearLayout.LayoutParams _paramsIpadButtonHeight=null;

    public static  int _GRD_PRODUCT_MOBILE_COLUMN_NUMBER =3;
    public static  int _GRD_PRODUCT_IPAD_HOR_COLUMN_NUMBER =6;
    public static  int _GRD_ROOM_IPAD_HOR_COLUMN_NUMBER =6;

    public static  int _GRD_PRODUCT_IPAD_VER_COLUMN_NUMBER =4;
    public static  int _GRD_ROOM_IPAD_VER_COLUMN_NUMBER =4;

    public static  int _GRD_ROOM_MOBILE_COLUMN_NUMBER=4;






    public static float _VERSION_CURRENT=0;
    public static float _VERSION_SERVER=0;

    public static void setButtonHeight(Context context)
    {
        Common._IpadButtonHeight=context.getResources().getDimensionPixelSize(R.dimen.buttonHeight_Large);
        Common._PhoneButtonHeight= context.getResources().getDimensionPixelSize(R.dimen.buttonHeight_Medium);

    }

    public static String formatAPIURL(String api) {
        if (api.length() == 0)
            return api;
        String v=api.substring(api.length() - 1,api.length() );
        if (v.equals("/"))
            api = api.substring(0, api.length() - 1);
        return api;
    }


public static LinearLayout.LayoutParams getParamButton() {
    if (Common._USE_MOBILE)
        return Common._paramsPhoneButtonHeight;
    else
        return Common._paramsIpadButtonHeight;
}

    public static void setParamButtonHeight(Context context) {
        Common._paramsPhoneButtonHeight = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, context.getResources().getDimensionPixelSize(R.dimen.buttonHeight_Medium));
        Common._paramsIpadButtonHeight = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }
    public static void resetValue() {
        _ROOM_STATUS_SELECTED = -1;
        /*
        _USER_ORDER = false;
        _USER_KITCHEN = false;

         */
        _CONTROL_GRD_WaitProcess = null;
        _CONTROL_GRD_UnProcess = null;
        _CONTROL_GvArea = null;
        //_CONTROL_TxtAreaName = null;
        //_CONTROL_GRD_Room = null;
        _AREA_CLASS = null;
        _CONTROL_GRD_Finished = null;
        _LIST_AREA = new ArrayList<CategoryClass>();
        _LIST_AREA_SELECTED = new ArrayList<CategoryClass>();
        _LIST_BRANCH=new ArrayList<>();
        _LIST_BRANCH_SELECTED=new ArrayList<>();
        _USER_USERNAME = "";
        _USER_SHOPCODE = "";
        _FRAGMENTMANAGER = null;
        _LIST_AREA_SELECTED_ID = null;
        _NOTIFY_PRODUCT_DONE_COUNT = 0;
        _NOTIFY_PRODUCT_DONE_BUTTON = null;
        _NOTIFY_PRODUCT_DONE_COUNT_NEW = 0;
        _NOTIFY_PRODUCT_DONE_TIMESTAMP = 0;
        _IS_FIRST_LOGON = true;
        //_USE_MOBILE = false;
        _CONTROL_GRD_ProductOrder = null;
        _CONTROL_TEXT_TextMoney = null;
        _CONTROL_TEXT_TextQuantity = null;
        _CONTROL_VIEW_ROOM = null;
        _REFRESH_LAYOUT = false;
        _CONFIG = null;
        _VERSION_CURRENT = 0;
        _VERSION_SERVER = 0;
        _PRODUCT_GROUP = null;
        _CONTROL_GRD_PRODUCT = null;
        _CONTROL_GRD_ProductGroup = null;

        _CONTROL_TEXT_txtSLDonDaXong = null;
        _CONTROL_TEXT_txtSoTienDonDaXong = null;
        _CONTROL_TEXT_txtSLChoThanhToan = null;
        _CONTROL_TEXT_txtSoTienDonChoThanhToan = null;
        _CONTROL_TEXT_txtSLBanTrong = null;
        _CONTROL_TEXT_txtSLBanCoKhach = null;
        __TimerLoadRoom=new Timer();
        // _CHIPS_AREA=null;
    }

    public static boolean checkVersion()
    {
        if (Common._VERSION_SERVER > Common._VERSION_CURRENT) {
            UpdateDialog dlg = new UpdateDialog();
            dlg.show(Common._FRAGMENTMANAGER, "");
            return true;
        }
        return false;
    }


    public static void errorWhenNotConnected(Context context) {
        if (!CannotConnectSystem) {
            return;
            //MyToast myToast = new MyToast(context, Common._FALIED, context.getString(R.string.error_when_connection));
            //myToast.show();

            /*
            CannotConnectSystem = true;
            Intent mIntent = new Intent(context, LoginActivity.class);
            context.startActivity(mIntent);

             */
        }

    }

    public static void showSuccessToast(Context context) {
        MyToast myToast = new MyToast(context, Common._SUCCESS, context.getString(R.string.success));
        myToast.show();
    }


    public static void showFailedToast(Context context, String message) {
        MyToast myToast = new MyToast(context, Common._FALIED, message);
        myToast.show();
    }

    public static String replaceCharMoney(String value) {
        return value.replace(".", "").replace(",", "");
    }

    public static String formatMoney(String value) {

        value = value.replace(".", "").replace(",", "");
        if (Integer.parseInt(value) == 0)
            return "0";
        DecimalFormat formatter = new DecimalFormat(Common._MONEY_FORMAT);
        value = formatter.format(Double.parseDouble(value));
        value = value.replace(".", ",");
        return value;
    }

    public static void loadRoom(Context context) {
        CF_Room.getAll(context, Common._USER_TOKEN,  Common._ROOM_STATUS_SELECTED);
    }

    public static void loadRoomReal(Context context) {
        CF_Room.getAllRealTime(context, Common._USER_TOKEN,  Common._ROOM_STATUS_SELECTED);
    }
    public static void setRoom(final Context context, ArrayList<RoomClass> mLstRoom) {
        /*
        if (Common._CONTROL_GRD_Room == null)
            return;
            */
        try {
            if (Common._LAYOUT_ROOM != null)
                Common._LAYOUT_ROOM.removeAllViews();

            ArrayList<CategoryClass> _ListArea=new ArrayList<CategoryClass>();
            CategoryClass areaEmpy = new CategoryClass();
            areaEmpy.setID(0);
            areaEmpy.setName("Không có khu vực");
            _ListArea.add(areaEmpy);
            if (Common._LIST_AREA_SELECTED != null && Common._LIST_AREA_SELECTED.size() > 0)
                _ListArea=Common._LIST_AREA_SELECTED;
            else if (Common._LIST_AREA != null&& Common._LIST_AREA.size() > 0)
                _ListArea=Common._LIST_AREA;
            ArrayList<RoomClass> roomsNotArea = new ArrayList<>();
            if (_ListArea.size() >0 ) {
                for (CategoryClass area : _ListArea) {

                    ArrayList<RoomClass> rooms = new ArrayList<>();
                    for (int i = mLstRoom.size() - 1; i >= 0; i--) {

                        RoomClass room = mLstRoom.get(i);
                        if (room.getAreaID() == area.getID()) {
                            rooms.add(room);
                            mLstRoom.remove(i);
                        }
                        else if (room.getAreaID() == 0) {
                            roomsNotArea.add(room);
                            mLstRoom.remove(i);
                        }
                    }
                    if (rooms.size() == 0)
                        break;
                    final MyGridView mGvRoom = new MyGridView(context);

                    mGvRoom.setGridViewRoom(mGvRoom);
                    mGvRoom.setTag("kv_" + area.getID());
                    RoomAdapter adapter = new RoomAdapter(context, rooms);
                    mGvRoom.setAdapter(adapter);


                    mGvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            RoomAdapter.ViewImageTextHolder roomClass = (RoomAdapter.ViewImageTextHolder) view.getTag();
                            Common._CONTROL_VIEW_ROOM = roomClass;
                            Common._REFRESH_LAYOUT = false;
                            //lấy ra thông tin bàn đang chọn
                            RoomClass room = new RoomClass();
                            room.setTableOrderIndex(Integer.parseInt(roomClass.mTableOrderID.getText().toString()));
                            room.setAreaName(roomClass.mAreaName.getText().toString());
                            room.setTenBan(roomClass.mTenBan.getText().toString());
                            room.setID(Integer.parseInt(roomClass.mID.getText().toString()));
                            room.setAreaID(Integer.parseInt(roomClass.mAreaID.getText().toString()));
                            room.setStatus(roomClass.mStatus);
                            room.setBranchID(roomClass.mBranchID);
                            Common._PRODUCT_GROUP_CLASS = null;
                            Common._ROOM_SELECTED_OBJ = room;

                            //CF_Room.getRoomOrder(getContext(),Common._USER_TOKEN,roomClass.mID.getText().toString(),roomClass);
                            //open  dialog order

                    /*
                    if(!Common._USE_MOBILE) {
                        OrderDialog dlg = new OrderDialog();
                        dlg.show(Common._FRAGMENTMANAGER, "");
                    }
                    else
                    {
                        OrderDialog_Phone dlg = new OrderDialog_Phone();
                        dlg.show(Common._FRAGMENTMANAGER, "");
                    }

                     */
                            OrderDialog_Phone dlg = new OrderDialog_Phone();
                            dlg.show(Common._FRAGMENTMANAGER, "");

                        }
                    });
                    TextView tv = new TextView(context);
                    MyTextView.setTextAreaTitle(context, tv);
                    tv.setText(area.getName());
                    Common._LAYOUT_ROOM.addView(tv);
                    Common._LAYOUT_ROOM.addView(mGvRoom);
                }
            }
            if (roomsNotArea.size() > 0)
            {
                ArrayList<RoomClass> rooms = new ArrayList<>();
                for (int i = roomsNotArea.size() - 1; i >= 0; i--) {

                    RoomClass room = roomsNotArea.get(i);
                    rooms.add(room);


                }
                if (rooms.size() == 0)
                    return;
                final MyGridView mGvRoom = new MyGridView(context);

                mGvRoom.setGridViewRoom(mGvRoom);
                mGvRoom.setTag("kv_0");
                RoomAdapter adapter = new RoomAdapter(context, rooms);
                mGvRoom.setAdapter(adapter);


                mGvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        RoomAdapter.ViewImageTextHolder roomClass = (RoomAdapter.ViewImageTextHolder) view.getTag();
                        Common._CONTROL_VIEW_ROOM = roomClass;
                        Common._REFRESH_LAYOUT = false;
                        //lấy ra thông tin bàn đang chọn
                        RoomClass room = new RoomClass();
                        room.setTableOrderIndex(Integer.parseInt(roomClass.mTableOrderID.getText().toString()));
                        room.setAreaName(roomClass.mAreaName.getText().toString());
                        room.setTenBan(roomClass.mTenBan.getText().toString());
                        room.setID(Integer.parseInt(roomClass.mID.getText().toString()));
                        room.setAreaID(Integer.parseInt(roomClass.mAreaID.getText().toString()));
                        room.setStatus(roomClass.mStatus);
                        room.setBranchID(roomClass.mBranchID);
                        Common._PRODUCT_GROUP_CLASS = null;
                        Common._ROOM_SELECTED_OBJ = room;

                        OrderDialog_Phone dlg = new OrderDialog_Phone();
                        dlg.show(Common._FRAGMENTMANAGER, "");

                    }
                });
                TextView tv = new TextView(context);
                MyTextView.setTextAreaTitle(context, tv);
                tv.setText("Không có khu vực");
                Common._LAYOUT_ROOM.addView(tv);
                Common._LAYOUT_ROOM.addView(mGvRoom);
            }
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public static void setRoomReal(final Context context, ArrayList<RoomClass> mLstRoom) {

        if (context == null)
            return;

        try {
            ArrayList<CategoryClass> _ListArea=new ArrayList<CategoryClass>();
            CategoryClass areaEmpy = new CategoryClass();
            areaEmpy.setID(0);
            areaEmpy.setName("Không có khu vực");
            _ListArea.add(areaEmpy);
            if (Common._LIST_AREA_SELECTED != null && Common._LIST_AREA_SELECTED.size() > 0)
                _ListArea=Common._LIST_AREA_SELECTED;
            else if (Common._LIST_AREA != null&& Common._LIST_AREA.size() > 0)
                _ListArea=Common._LIST_AREA;
            if (_ListArea.size() >0 ) {
                for (CategoryClass area : _ListArea) {

                    MyGridView kvView = Common._LAYOUT_ROOM.findViewWithTag("kv_" + area.getID());
                    if (kvView == null) {
                        ArrayList<RoomClass> rooms = new ArrayList<>();
                        for (int i = mLstRoom.size() - 1; i >= 0; i--) {

                            RoomClass room = mLstRoom.get(i);
                            if (room.getAreaID() == area.getID()) {
                                rooms.add(room);
                                mLstRoom.remove(i);
                            }

                        }
                        if (rooms.size() == 0)
                            break;
                        final MyGridView mGvRoom = new MyGridView(context);

                        mGvRoom.setGridViewRoom(mGvRoom);

                        RoomAdapter adapter = new RoomAdapter(context, rooms);
                        mGvRoom.setAdapter(adapter);


                        mGvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                RoomAdapter.ViewImageTextHolder roomClass = (RoomAdapter.ViewImageTextHolder) view.getTag();
                                Common._CONTROL_VIEW_ROOM = roomClass;
                                Common._REFRESH_LAYOUT = false;
                                //lấy ra thông tin bàn đang chọn
                                RoomClass room = new RoomClass();
                                room.setTableOrderIndex(Integer.parseInt(roomClass.mTableOrderID.getText().toString()));
                                room.setAreaName(roomClass.mAreaName.getText().toString());
                                room.setTenBan(roomClass.mTenBan.getText().toString());
                                room.setID(Integer.parseInt(roomClass.mID.getText().toString()));
                                room.setAreaID(Integer.parseInt(roomClass.mAreaID.getText().toString()));
                                room.setStatus(roomClass.mStatus);
                                room.setBranchID(roomClass.mBranchID);
                                Common._PRODUCT_GROUP_CLASS = null;
                                Common._ROOM_SELECTED_OBJ = room;


                                OrderDialog_Phone dlg = new OrderDialog_Phone();
                                dlg.show(Common._FRAGMENTMANAGER, "");

                            }
                        });
                        TextView tv = new TextView(context);
                        MyTextView.setTextAreaTitle(context, tv);
                        tv.setText(area.getName());
                        Common._LAYOUT_ROOM.addView(tv);
                        Common._LAYOUT_ROOM.addView(mGvRoom);
                    }

                    else {
                        ArrayList<RoomClass> rooms = new ArrayList<>();
                        for (int i = mLstRoom.size() - 1; i >= 0; i--) {

                            RoomClass room = mLstRoom.get(i);
                            if (room.getAreaID() == area.getID()) {
                                rooms.add(room);
                                mLstRoom.remove(i);
                            }

                        }
                        if (rooms.size() == 0)
                            break;
                        RoomAdapter adapter = new RoomAdapter(context, rooms);
                        kvView.setAdapter(adapter);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public static String getStrBranchSelected() {
        String value = "";
        if (Common._LIST_BRANCH_SELECTED_ID != null && Common._LIST_BRANCH_SELECTED_ID.size() > 0) {
            for (Integer i : Common._LIST_BRANCH_SELECTED_ID)
                value = value + i.toString() + ",";
        }
        else
        {
            if(Common._LIST_BRANCH != null) {
                for (CategoryClass i : Common._LIST_BRANCH)
                    value = value + i.getID() +"" + ",";
            }
        }
        if (value.length() > 0)
            value = value.substring(0, value.length() - 1);
        return value;
    }

    public static boolean clearEditText(MotionEvent event, EditText editText)
    {
        final int DRAWABLE_RIGHT = 2;
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                // your action here
                editText.setText("");
                editText.requestFocus();
                return true;
            }
        }
        return false;
    }


    public static void sendNotifyProductDoneNew(Context context,Integer quantity)
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(android.R.drawable.btn_star_big_on);
        mBuilder.setContentTitle("Thông báo mới từ bếp");
        mBuilder.setContentText(quantity+" sản phẩm vừa làm xong!!!!");
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("notify",1);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);


        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());
    }


    public static void loadProduct(ProductGroupClass productGroup, String name,Context context) {

        if(Common._CONTROL_GRD_PRODUCT !=null) {
            Common._PRODUCT_GROUP_CLASS = productGroup;
            ArrayList<ProductClass> data = CF_Product.getByGroupID(productGroup.getID(), name);
            ProductAdapter adapter = new ProductAdapter(context, data);
            Common._CONTROL_GRD_PRODUCT.setAdapter(adapter);
        }
    }


    public static void loadProductGroup(Context context) {
        if (Common._PRODUCT_GROUP != null) {
            // Create the FlexboxLayoutMananger, only flexbox library version 0.3.0 or higher support.
            FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
            // Set flex direction.
            flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
            // Set JustifyContent.
            flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);

            Common._CONTROL_GRD_ProductGroup.setLayoutManager(flexboxLayoutManager);
            ProductGroupAdapter adapter = new ProductGroupAdapter(context, Common._PRODUCT_GROUP);
            Common._CONTROL_GRD_ProductGroup.setAdapter(adapter);
        }
    }



    public static void hideSoftKeyboard(Activity activity) {


        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

    }


    public enum categoryType
    {
        AREA,
        BRANCH
    }



    public static void updateLabelDate(MyDatepicker edittext, Calendar myCalendar) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
    public static String getDateFromCalendar(Calendar myCalendar)
    {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        return sdf.format(myCalendar.getTime());
    }
    public static Calendar getCalendarFromString(String strDate)
    {
        Calendar calendar = Calendar.getInstance();
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        try {
            Date date = sdf.parse(strDate);

            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  calendar;
    }
    public static String getDateFromString(String strDate)
    {
        String value="";
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        try {
            Date date = sdf.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            value=getDateFromCalendar(calendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  value;
    }

    public static CategoryClass getBranhByID(int id) {
        CategoryClass value = new CategoryClass();
        if (Common._LIST_BRANCH != null) {
            for (CategoryClass item : Common._LIST_BRANCH) {
                if (item.getID() == id)
                    return item;
            }
        }
        return value;
    }

    public static class IntValueFormatter implements IValueFormatter {

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return String.valueOf((int) value);
        }
    }

    public static void showFailedToastOnThread(final Context context, final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                showFailedToast(context,text);
            }
        });
    }
}
