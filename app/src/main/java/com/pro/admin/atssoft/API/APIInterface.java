package com.pro.admin.atssoft.API;

import com.pro.admin.atssoft.APIResult.CF_Category;
import com.pro.admin.atssoft.APIResult.CF_Kitchen;
import com.pro.admin.atssoft.APIResult.CF_Order;
import com.pro.admin.atssoft.APIResult.CF_Overview;
import com.pro.admin.atssoft.APIResult.CF_Product;
import com.pro.admin.atssoft.APIResult.CF_ProductGroup;
import com.pro.admin.atssoft.APIResult.CF_Report;
import com.pro.admin.atssoft.APIResult.CF_Room;
import com.pro.admin.atssoft.APIResult.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("/api/CF_Account/Login")
    Call<Object> doLogin(@Body UserLogin user);


    @POST("/api/CF_Area/GetAll")
    Call<Object> doGetArea(@Body CF_Category.GetAll token);

    @POST("/api/CF_Branch/GetAll")
    Call<Object> doGetBranch(@Body CF_Category.GetAll token);


    @POST("/api/CF_Order/GetRoomOrder")
    Call<Object> doGetRoom(@Body CF_Room.GetAll token);



    @POST("/api/CF_Product/GetProductGroup")
    Call<Object> doGetProductGroup(@Body CF_ProductGroup.GetAll token);


    @POST("/api/CF_Product/GetAll")
    Call<Object> doGetProduct(@Body CF_Product.GetAll token);

    @POST("/api/CF_Order/GetProductOrder")
    Call<Object> doGetProductOrder(@Body CF_Order.GetProductOrder token);

    @POST("/api/CF_Order/Order")
    Call<Object> doOrder(@Body CF_Order.Order token);

    @POST("/api/CF_Order/UpdateNote")
    Call<Object> doUpdateNote(@Body CF_Order.UpdateNote token);

    @POST("/api/CF_Order/UpdatePrioritize")
    Call<Object> doUpdatePrioritize(@Body CF_Order.UpdatePrioritize token);

    @POST("/api/CF_Order/ChangeTable")
    Call<Object> doChangeTable(@Body CF_Order.ChangeTable token);

    @POST("/api/CF_Order/GetProductPaidNew")
    Call<Object> doGetProductPaid(@Body CF_Order.GetProductPaid token);


    @POST("/api/CF_Order/GetRoomOrder")
    Call<Object> doGetRoomOrder(@Body CF_Room.GetAll token);

    @POST("/api/CF_Order/Paid")
    Call<Object> doPaid(@Body CF_Order.Paid token);

    @POST("/api/CF_Order/Notify")
    Call<Object> doNotify(@Body CF_Order.Notify token);


    @POST("/api/CF_Order/UpdateStatusPrintBill")
    Call<Object> doUpdateStatusPrintBill(@Body CF_Order.Notify token);



    @POST("/api/CF_kitchen/GetUnProcess")
    Call<Object> doGetUnProcess(@Body CF_Kitchen.getUnProcess token);


    @POST("/api/CF_kitchen/GetWaitProcess")
    Call<Object> doGetWaitProcess(@Body CF_Kitchen.getWaitProcess token);


    @POST("/api/CF_kitchen/GetFinished")
    Call<Object> doGetFinished(@Body CF_Kitchen.getFinished token);

    @POST("/api/CF_kitchen/WaitProcess")
    Call<Object> doWaitProcess(@Body CF_Kitchen.waitProcess token);

    @POST("/api/CF_kitchen/Done")
    Call<Object> doDone(@Body CF_Kitchen.done token);

    @POST("/api/CF_kitchen/Finished")
    Call<Object> doFinished(@Body CF_Kitchen.finished token);


    @POST("/api/CF_Config/GetAll")
    Call<Object> doGetConfig();

    @POST("/api/Print/PrintBillAndroid")
    Call<Object> doPrintBill(@Body CF_Order.PrintBill token);


    @POST("/api/CF_Report/GetBillWailtingPayment")
    Call<Object> doGetBillWailtingPayment(@Body CF_Overview.report token);


    @POST("/api/CF_Report/GetPaidBill")
    Call<Object> doGetPaidBill(@Body CF_Overview.report token);

    @POST("/api/CF_Report/GetRoomEmptyAndUsed")
    Call<Object> doGetRoomEmptyAndUsedt(@Body CF_Overview.report token);

    @POST("/api/CF_Report/GetRevenueToday")
    Call<Object> doGetRevenueToday(@Body CF_Overview.report token);

    @POST("/api/CF_Report/GetSales")
    Call<Object> doGetSales(@Body CF_Report.sales token);


    @POST("/api/CF_Report/GetRevenueDetail")
    Call<Object> doGetRevenueDetail(@Body CF_Report.sales token);

}
