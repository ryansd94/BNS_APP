package com.pro.admin.atssoft.APIResult;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.R;
import com.pro.admin.atssoft.SessionManager;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Adapter.OrderAdapter;
import Adapter.ProductPaidAdapter;
import Control.MyToast;
import Dialog.ChangeTableDialog;
import Dialog.PayDialog;
import model.CategoryClass;
import model.OrderClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CF_Order {
    static public ArrayList<OrderClass> mLstOrder = new ArrayList<OrderClass>();


    public static class GetProductPaid {
        @SerializedName("token")
        public String token;

        @SerializedName("table")
        public String table;

        /*
        @SerializedName("orderbytable")
        public String orderbytable;
        */

        public GetProductPaid(String token, String table) {
            this.token = token;
            this.table = table;
        }
    }

    public static class GetProductOrder {
        @SerializedName("token")
        public String token;

        @SerializedName("tableorder")
        public String tableorder;

        /*
        @SerializedName("orderbytable")
        public String orderbytable;
        */

        public GetProductOrder(String token, String tableorder) {
            this.token = token;
            this.tableorder = tableorder;
        }
    }

    public static class UpdateNote {

        @SerializedName("token")
        public String token;

        @SerializedName("index")
        public String index;

        @SerializedName("note")
        public String note;

        public UpdateNote(String token, String index, String note) {
            this.index = index;
            this.note = note;
            this.token = token;
        }
    }

    public static class Notify {

        @SerializedName("token")
        public String token;

        @SerializedName("table")
        public String table;

        public Notify(String token, String table) {
            this.table = table;
            this.token = token;
        }
    }

    public static class UpdatePrioritize {

        @SerializedName("token")
        public String token;

        @SerializedName("index")
        public String index;

        @SerializedName("prioritize")
        public String prioritize;

        public UpdatePrioritize(String token, String index, String prioritize) {
            this.index = index;
            this.prioritize = prioritize;
            this.token = token;
        }
    }

    public static class ChangeTable {

        @SerializedName("token")
        public String token;

        @SerializedName("table")
        public String table;

        @SerializedName("tablechange")
        public String tablechange;

        @SerializedName("key")
        public String key;


        public ChangeTable(String token, String tableOld, String tableNew, String key) {
            this.table = tableOld;
            this.tablechange = tableNew;
            this.token = token;
            this.key = key;
        }
    }

    public class productPaid
    {
        public int TotalQuantity;
        public int totalCost;
        public int TotalMoney;
        public int TotalPaid;
        public int TotalSale;
        public  ArrayList<Product> ListProduct;
        public productPaid()
        {
            ListProduct = new ArrayList<Product>();
        }
        public  class Product
        {
            public int OrderID;
            public int ProductID;
            public int _cost;
            public int _money;
            public int _sale;
            public int _price;
            public int _priceCost;

            public String Cost;
            public String Money;
            public String Sale;
            public String Price;
            public String PriceCost;

            public int Quantity;
            public String Name;
            public int BranchID;
            public String OrderDate;
            public String OrderUser;
            public String ID;
            public String ProductGroupID;
        }
    }


    public static class Order {
        @SerializedName("token")
        public String token;

        @SerializedName("table")
        public String table;

        @SerializedName("product")
        public String product;

        @SerializedName("quantity")
        public String quantity;


        @SerializedName("note")
        public String note;

        @SerializedName("add")
        public String add;


        @SerializedName("prioritize")
        public String prioritize;

        @SerializedName("tableorder")
        public String tableorder;

        @SerializedName("index")
        public String index;

        /*
        @SerializedName("orderbytable")
        public String orderbytable;
        */

        public Order(String token, String table, String product, String quantity, String note, String add, String prioritize, String tableorder, String index) {
            this.token = token;
            this.table = table;
            this.product = product;
            this.quantity = quantity;
            this.note = note;
            this.add = add;
            this.prioritize = prioritize;
            this.tableorder = tableorder;
            this.index = index;
        }
    }

    public static class PrintBill {
        @SerializedName("token")
        public String token;

        @SerializedName("table")
        public String table;

        public PrintBill(String token, String table) {
            this.token = token;
            this.table = table;
        }
    }

    public static class Paid {

        @SerializedName("token")
        public String token;

        @SerializedName("table")
        public String table;

        @SerializedName("qt")
        public String qt;

        @SerializedName("tm")
        public String tm;

        @SerializedName("s")
        public String s;

        @SerializedName("cmp")
        public String cmp;

        @SerializedName("cp")
        public String cp;

        @SerializedName("tablename")
        public String tablename;


        public Paid(String token, String table, String qt, String tm, String s, String cmp, String cp, String tablename) {
            this.table = table;
            this.qt = qt;
            this.token = token;
            this.tm = tm;
            this.s = s;
            this.cmp = cmp;
            this.cp = cp;
            this.tablename = tablename;
        }
    }


    public static void notify(final Context context, final String token, String table) {
        APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
        Call<Object> callArea = mApiInterface.doNotify(new CF_Order.Notify(token, table));
        callArea.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {


                DataResult result = new DataResult();
                DataResult.Data data = result.getDataResult(response);

                if (data.Status.contains("OK")) {
                    Common.showSuccessToast(context);
                } else
                    Common.showFailedToast(context, data.Message);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Common.errorWhenNotConnected(context);
                call.cancel();
            }
        });
    }

    public static void updateStatusPrintBill(final Context context, final String token, String table) {
        APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
        Call<Object> callArea = mApiInterface.doUpdateStatusPrintBill(new CF_Order.Notify(token, table));
        callArea.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {


                DataResult result = new DataResult();
                DataResult.Data data = result.getDataResult(response);

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Common.errorWhenNotConnected(context);
                call.cancel();
            }
        });
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static void printBill(final Context context, final String token, final String table, final String customerPaid) {

        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);

            Call<Object> callArea = mApiInterface.doGetProductPaid(new CF_Order.GetProductPaid(token, table));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {

                    DataResult result = new DataResult();
                    final DataResult.DataNew data = result.getDataResultNew(response);
                    if (data.Status.contains("OK")) {

                        Type listType = new TypeToken<CF_Order.productPaid>() {
                        }.getType();
                        Gson gson = new Gson();
                        final productPaid productPaid = gson.fromJson(data.Data.toString(), CF_Order.productPaid.class);


                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    InetSocketAddress sockAdr = new InetSocketAddress(Common._PRINTER_IP, Common._PRINTER_PORT);
                                    Socket sock = new Socket();
                                    sock.connect(sockAdr, 2000);
                                    //Socket sock = new Socket("192.168.1.201", 9100);
                                    String sTotalMoney = "";
                                    Integer dTotalMoney = 0;
                                    char[] normalSize = new char[]{0x1B, 0x21, 0x00};  // 0- normal size text
                                    char[] boldText = new char[]{0x1B, 0x21, 0x08};  // 1- only bold text
                                    char[] boldMediumText = new char[]{0x1B, 0x21, 0x10}; // 2- bold with medium text
                                    char[] boldLargeText = new char[]{0x1B, 0x21, 0x20}; // 3- bold with large text
                                    char newline = 0x0A;
                                    char[] SET_LINE_SPACING_24 = {0x1B, 0x33, 24};
                                    char[] SET_LINE_SPACING_30 = {0x1B, 0x33, 30};
                                    char[] SET_LINE_SPACING_0 = {0x1b ,0x33 ,0x10};

                                    PrintWriter oStream = new PrintWriter(sock.getOutputStream());

                                    String line = new String(new char[48]).replace('\0', '-');
                                    CategoryClass room = Common.getBranhByID(Common._ROOM_SELECTED_OBJ.getBranchID());

                                    byte[] arrayOfByte1 = {27, 33, 0};
                                    byte[] format = {27, 33, 0};

                                    // Bold
                                    format[2] = ((byte) (0x8 | arrayOfByte1[2]));
                                    char[] FONT_2X = {0x1D, 0x21, 0x11};
                                    char[] FONT_1X = {0x1D, 0x21, 0x00};
                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.BoldMediumText,
                                            PrintCommon.printPosition.Left, Common._SHOPNAME);


                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.NormalSize,
                                            PrintCommon.printPosition.Left, room.Address);
                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.NormalSize,
                                            PrintCommon.printPosition.Left, room.Phone);

                                    PrintCommon.printNewLine(oStream);

                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.FONT_2X,
                                            PrintCommon.printPosition.Center, "HÓA ĐƠN THANH TOÁN");

                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.NormalSize,
                                            PrintCommon.printPosition.Center, "HDBH00001");

                                    PrintCommon.printNewLine(oStream);

                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.NormalSize,
                                            PrintCommon.printPosition.Left, Common._ROOM_SELECTED_OBJ.TenBan);
                                    PrintCommon.printNewLine(oStream);
                                    String user = Common._USER_NAME.length() == 0 ? Common._USER_USERNAME : Common._USER_NAME;
                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.NormalSize,
                                            PrintCommon.printPosition.Left, "Người lập: " + user);


                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                                    String currentDateandTime = sdf.format(new Date());

                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.NormalSize,
                                            PrintCommon.printPosition.Right, currentDateandTime);
                                    PrintCommon.printNewLine(oStream);
                                    oStream.write(boldText);
                                    oStream.printf("%s %s %s %n",
                                            StringUtils.leftPad(PrintCommon.convertUnsigned("TÊN MÓN", true), 0),
                                            StringUtils.leftPad(PrintCommon.convertUnsigned("SL", true), 20),
                                            StringUtils.leftPad(PrintCommon.convertUnsigned("THÀNH TIỀN", true), 15));
                                    oStream.write(normalSize);
                                    oStream.println(line);
                                    PrintCommon.printNewLine(oStream);
                                    dTotalMoney = Integer.parseInt(Common.replaceCharMoney(String.valueOf(productPaid.TotalMoney)));
                                    String sSale = "";
                                    String sTotalPaid = "";
                                    sSale = Common.formatMoney(String.valueOf(productPaid.TotalSale));
                                    sTotalPaid = Common.formatMoney(String.valueOf(productPaid.TotalPaid));

                                    for (CF_Order.productPaid.Product item : productPaid.ListProduct) {

                                        PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.NormalSize,
                                                PrintCommon.printPosition.Left, item.Name);


                                        //oStream.write(SET_LINE_SPACING_24);
                                        oStream.write(normalSize);
                                        oStream.printf("%s %s %s %n",
                                                StringUtils.leftPad(PrintCommon.convertUnsigned(Common.formatMoney(String.valueOf(item.Price)), true), 0),
                                                StringUtils.leftPad(PrintCommon.convertUnsigned(String.valueOf(item.Quantity), true), 22),
                                                StringUtils.leftPad(PrintCommon.convertUnsigned(Common.formatMoney(String.valueOf(item.Money)), true), 17));
                                        PrintCommon.printNewLine(oStream);
                                        //PrintCommon.printNewLine(oStream);

/*

                                        PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.BoldText,
                                                PrintCommon.printPosition.Left, item.Name);



                                        oStream.write(normalSize);
                                        oStream.write(SET_LINE_SPACING_30);
                                        oStream.printf("%s %s %s %s %n",
                                                StringUtils.leftPad(PrintCommon.convertUnsigned(String.valueOf(item.Name), true), 0),
                                                StringUtils.left(PrintCommon.convertUnsigned(Common.formatMoney(String.valueOf(item.Price)), true), 0),
                                                StringUtils.left(PrintCommon.convertUnsigned(String.valueOf(item.Quantity), true), 6),
                                                StringUtils.rightPad(PrintCommon.convertUnsigned(Common.formatMoney(String.valueOf(item.Money)), true), 0));
                                        PrintCommon.printNewLine(oStream);
 */

                                    }
                                    if (dTotalMoney > 0) {
                                        DecimalFormat formatter = new DecimalFormat(Common._MONEY_FORMAT);
                                        sTotalMoney = formatter.format(dTotalMoney);

                                    }
                                    oStream.write(normalSize);

                                    oStream.println(line);
                                    if (sSale != "" && sSale != "0") {

                                        oStream.printf("%s %s %n",
                                                StringUtils.leftPad(PrintCommon.convertUnsigned("Tổng tiền", true), 0),
                                                StringUtils.leftPad(PrintCommon.convertUnsigned(sTotalMoney, true), 37));

                                        //PrintCommon.printNewLine(oStream);


                                        oStream.printf("%s %s %n",
                                                StringUtils.leftPad(PrintCommon.convertUnsigned("Giảm giá", true), 0),
                                                StringUtils.leftPad(PrintCommon.convertUnsigned(sSale, true), 38));
                                    }
                                    PrintCommon.printNewLine(oStream);
                                    oStream.write(boldText);
                                    oStream.printf("%s %s %n",
                                            StringUtils.leftPad(PrintCommon.convertUnsigned("TIỀN THANH TOÁN", true), 0),
                                            StringUtils.leftPad(PrintCommon.convertUnsigned(sTotalPaid, true), 31));

                                    double dcustomerPaid = Double.parseDouble(customerPaid);
                                    if (dcustomerPaid > 0) {
                                        oStream.write(normalSize);
                                        oStream.printf("%s %s %n",
                                                StringUtils.leftPad(PrintCommon.convertUnsigned("Khách đưa", true), 0),
                                                StringUtils.leftPad(PrintCommon.convertUnsigned(Common.formatMoney(customerPaid), true), 38));


                                        oStream.printf("%s %s %n",
                                                StringUtils.leftPad(PrintCommon.convertUnsigned("Trả khách", true), 0),
                                                StringUtils.leftPad(PrintCommon.convertUnsigned(Common.formatMoney((dcustomerPaid - dTotalMoney) + ""), true), 38));

                                    }

                                    PrintCommon.printAndNewLine(oStream, PrintCommon.printStyleText.NormalSize,
                                            PrintCommon.printPosition.Center, Common._PRINT_NOTE);
                                    oStream.println("\n\n\n");
                                    oStream.println(new char[]{0x1D, 0x56, 0x41, 0x10});
                                    oStream.flush();
                                    oStream.close();

                                    sock.close();


                                    updateStatusPrintBill(context,token,table);

                                } catch (IOException e) {
                                    if (e.getMessage().indexOf("failed to connect to") > -1) {
                                        Common.showFailedToastOnThread(context, context.getResources().getString(R.string.cannot_connect_bill_printer));
                                    }
                                }
                            }
                        });

                        thread.start();

                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            Common.errorWhenNotConnected(context);
                        }
                    }
                    //Common.errorWhenNotConnected(context);

                    //call.cancel();
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            MyToast myToast = new MyToast(context, Common._FALIED, ex.getMessage().toString());
        }
    }

    public static void changeTable(final Context context, final String token, String tableOld, String tableNew,
                                   String key, final int status,
                                   final ChangeTableDialog dialog) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doChangeTable(new CF_Order.ChangeTable(token, tableOld, tableNew, key));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);

                    if (data.Status.contains("OK")) {
                        CF_Room.getAll(context, token, status);
                        dialog.dismiss();
                        if (Common._CONTROL_OrderDialog != null)
                            Common._CONTROL_OrderDialog.dismiss();
                        if (Common._CONTROL_OrderDialogPhone != null)
                            Common._CONTROL_OrderDialogPhone.dismiss();
                        Common.showSuccessToast(context);
                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            Common.errorWhenNotConnected(context);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }


    public static void updatePrioritize(final Context context, final String token, final String prioritize, String orderindex, final String tableorderindex,
                                        final GridView grdProductOrder) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doUpdatePrioritize(new CF_Order.UpdatePrioritize(token, orderindex, prioritize));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);

                    if (data.Status.contains("OK")) {
                        Common.showSuccessToast(context);
                        if (tableorderindex != "0" && tableorderindex != "-1")
                            getProductOrder(context, token, tableorderindex, ""
                                    //        ,viewRoom
                            );

                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            Common.errorWhenNotConnected(context);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }

    public static void updateNote(final Context context, final String token, final String note, String orderindex, final String tableorderindex,
                                  final GridView grdProductOrder) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doUpdateNote(new CF_Order.UpdateNote(token, orderindex, note));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);

                    if (data.Status.contains("OK")) {
                        Common.showSuccessToast(context);
                        if (tableorderindex != "0" && tableorderindex != "-1")
                            getProductOrder(context, token, tableorderindex, ""
                                    //,viewRoom
                            );

                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            Common.errorWhenNotConnected(context);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }

    public static void order(final Context context, final String token, final String table, final String product,
                             final String quantity, String note, final String add, String prioritize,
                             final String tableorder, String orderindex,
                             final int status,
                             final boolean isLoadPDOrder, final String orderbytable) {
        try {
            //Toast.makeText(context,"table: " + table +", product: "+product+", orderindex: "+orderindex,Toast.LENGTH_SHORT).show();
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doOrder(new CF_Order.Order(token, table, product, quantity, note, add, prioritize, table, orderindex));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);

                    if (data.Status.contains("OK")) {
                        if (table != "0" && table != "-1") {

                            boolean bNew = true;
                            Integer totalMoney = 0;
                            Integer totalQuantity=0;
                            for (int i = 0; i < Common._ORDER_DATA.size(); i++) {

                                if (bNew && Integer.parseInt(Common._ORDER_DATA.get(i).getProductID()) == Integer.parseInt(product)) {
                                    if (Integer.parseInt(quantity) > 0) {
                                        if (Boolean.parseBoolean(add)) {
                                            Common._ORDER_DATA.get(i).setQuantity(String.valueOf(Integer.parseInt(Common._ORDER_DATA.get(i).getQuantity()) + 1));
                                        } else {
                                            Common._ORDER_DATA.get(i).setQuantity(quantity);
                                        }
                                        Integer quantity = Integer.parseInt(Common._ORDER_DATA.get(i).getQuantity());
                                        Integer price = Integer.parseInt(Common._ORDER_DATA.get(i).getPrice().replace(",", ""));
                                        Integer money = quantity * price;

                                        Common._ORDER_DATA.get(i).setTotalMoney(Common.formatMoney(String.valueOf(money)));

                                    } else {

                                        Common._ORDER_DATA.remove(i);
                                    }

                                    bNew = false;
                                }

                            }
                            for (int i = 0; i < Common._ORDER_DATA.size(); i++) {
                                totalQuantity += Integer.parseInt(Common._ORDER_DATA.get(i).getQuantity());
                                totalMoney += Integer.parseInt(Common._ORDER_DATA.get(i).getTotalMoney().replace(",", ""));
                            }
                            Common._CONTROL_TEXT_TextMoney.setText(Common.formatMoney(String.valueOf(totalMoney)) + Common._MONEY_UNIT);
                            Common._CONTROL_TEXT_TextMoney.invalidate();
                            Common._CONTROL_TEXT_TextQuantity.setText(totalQuantity + "");
                            if (Integer.parseInt(quantity) > 0 && bNew) {
                                getProductOrder(context, token, table, "");
                            } else {

                                OrderAdapter orderAdapter = new OrderAdapter(context, Common._ORDER_DATA);
                                Common._CONTROL_GRD_ProductOrder.setAdapter(orderAdapter);
                                Common._CONTROL_GRD_ProductOrder.invalidateViews();
                            }
                            CF_Room.getRoomOrder(context, token, table);
 /*
                        txtMoney.setText(Common.formatMoney(String.valueOf(totalMoney)) + Common._MONEY_UNIT);


                        */
                        }
                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                            //getProductOrder(context, token, table, "", grdProductOrder, txtMoney);
                        } else {
                            Common.errorWhenNotConnected(context);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }

    public static void getProductOrder(final Context context, String token, String table, String orderbytable
                                       //, RoomAdapter.ViewImageTextHolder viewRoom
    ) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doGetProductOrder(new CF_Order.GetProductOrder(token, table));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        String sTotalMoney = "";
                        Integer dTotalMoney = 0;
                        Integer quantity = 0;
                        ArrayList<OrderClass> mLstOrder = new ArrayList<OrderClass>();
                        for (ArrayList<String> item : data.Data) {
                            OrderClass orderClass = new OrderClass();
                            orderClass.setProductID(item.get(0));
                            orderClass.setProductName(item.get(1));
                            orderClass.setQuantity(item.get(2));
                            orderClass.setPrice(item.get(3));
                            orderClass.setTotalMoney(item.get(4));
                            orderClass.setID(item.get(5));
                            orderClass.setIsAnnounced(item.get(6));
                            orderClass.setNote(item.get(7));
                            orderClass.setIsPrioritize(item.get(8));
                            //mLstOrder.add(area);
                            mLstOrder.add(orderClass);
                            quantity += Integer.parseInt(item.get(2));

                            try {
                                dTotalMoney += Integer.parseInt(item.get(4).toString().replace(",", ""));
                            } catch (Exception e) {
                            }

                        }
                        Common._ORDER_DATA = mLstOrder;

                        Common._CONTROL_TEXT_TextMoney.setText(Common.formatMoney(String.valueOf(dTotalMoney)) + Common._MONEY_UNIT);
                        Common._CONTROL_TEXT_TextMoney.invalidate();
                        Common._CONTROL_TEXT_TextQuantity.setText(quantity + "");

                        OrderAdapter orderAdapter = new OrderAdapter(context, mLstOrder);
                        Common._CONTROL_GRD_ProductOrder.setAdapter(orderAdapter);
                        Common._CONTROL_GRD_ProductOrder.invalidateViews();

                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            Common.errorWhenNotConnected(context);
                        }
                    }
                    //call.cancel();
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }


    public static void getProductPaid(final Context context, String token, final String table) {

        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);

            Call<Object> callArea = mApiInterface.doGetProductPaid(new CF_Order.GetProductPaid(token, table));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {

                    DataResult result = new DataResult();
                    DataResult.DataNew data = result.getDataResultNew(response);

                    DecimalFormat formatter = new DecimalFormat(Common._MONEY_FORMAT);
                    if (data.Status.contains("OK")) {
                        String sTotalMoney = "";
                        String sSale = "";
                        String sTotalPaid="";
                        Integer dTotalMoney = 0;
                        ArrayList<OrderClass> lstData = new ArrayList<OrderClass>();


                        Type listType = new TypeToken<CF_Order.productPaid>() {}.getType();
                        Gson gson = new Gson();
                        productPaid productPaid=gson.fromJson(data.Data.toString(), CF_Order.productPaid.class);


                        for ( CF_Order.productPaid.Product item : productPaid.ListProduct) {
                            OrderClass orderClass = new OrderClass();
                            orderClass.setProductID(String.valueOf( item.ProductID));
                            orderClass.setProductName(item.Name);
                            orderClass.setQuantity(String.valueOf( item.Quantity));
                            orderClass.setPrice(Common.formatMoney(String.valueOf( item.Price)));
                            orderClass.setTotalMoney(Common.formatMoney(String.valueOf( item.Money)));
                            lstData.add(orderClass);



                        }
                        try {
                            dTotalMoney = Integer.parseInt(Common.replaceCharMoney(String.valueOf( productPaid.TotalMoney)));
                            sSale=Common.formatMoney(String.valueOf( productPaid.TotalSale));
                            sTotalPaid=Common.formatMoney(String.valueOf( productPaid.TotalPaid));
                        } catch (Exception e) {
                        }
                        if (dTotalMoney > 0) {

                            sTotalMoney = formatter.format(dTotalMoney);

                        }

                        ProductPaidAdapter productPaidAdapter = new ProductPaidAdapter(context, lstData);

                        Common._CONTROL_PayDialog.setValue(productPaidAdapter, sTotalMoney, sTotalPaid, sSale);
                        Common._CONTROL_PayDialog.show(Common._FRAGMENTMANAGER, "");
                        //Common._CONTROL_PayDialog.hideSoftKeyboard();


                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            Common.errorWhenNotConnected(context);
                        }
                    }
                    //Common.errorWhenNotConnected(context);

                    //call.cancel();
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            MyToast myToast = new MyToast(context, Common._FALIED, ex.getMessage().toString());
        }
    }


    public static void paid(final Context context, final String token, final String table, String qt, String tm, String s,
                            String cmp, String cp, String tablename,
                            final PayDialog dlgPay) {
        try {
            APIInterface mApiInterface = APIClient.getClient(new SessionManager(context).getAPILink()).create(APIInterface.class);
            Call<Object> callArea = mApiInterface.doPaid(new CF_Order.Paid(token, table, qt, tm, s, cmp, cp, tablename));
            callArea.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {


                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        CF_Room.getRoomOrder(context, token, table);
                        Common.showSuccessToast(context);
                        if (Common._CONTROL_OrderDialog != null)
                            Common._CONTROL_OrderDialog.dismiss();
                        if (Common._CONTROL_OrderDialogPhone != null)
                            Common._CONTROL_OrderDialogPhone.dismiss();
                        dlgPay.dismiss();

                    } else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            Common.errorWhenNotConnected(context);
                        }
                    }
                    //call.cancel();
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Common.errorWhenNotConnected(context);
                    call.cancel();
                }
            });
        } catch (Exception ex) {
            Common.showFailedToast(context, context.getString(R.string.error_not_connect_api));
        }
    }
}
