package com.pro.admin.atssoft.APIResult;

import android.content.Context;
import android.widget.Spinner;

import com.pro.admin.atssoft.API.APIClient;
import com.pro.admin.atssoft.API.APIInterface;
import com.pro.admin.atssoft.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import Adapter.RoomAdapter;
import Adapter.SpinerAdapter;
import model.RoomClass;
import model.SpinerClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CF_Room {

    public static class GetAll {
        @SerializedName("token")
        public String token;


        @SerializedName("table")
        public String table;

        public GetAll(String token, String table) {

            this.token = token;
            this.table = table;
        }
    }

    public static void getRoomOrder(final Context context, final String token,
                                    final String table) {
        try {
            APIInterface mApiInterface = APIClient.getClient(Common._API_Link).create(APIInterface.class);
            Call<Object> callRoom = mApiInterface.doGetRoom(new CF_Room.GetAll(token, table));
            callRoom.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {
                        for (int i = data.Data.size() - 1; i >= 0; i--) {
                            ArrayList<String> item = data.Data.get(i);
                            if (table.length() > 0 && Integer.parseInt(table) == Integer.parseInt(item.get(1))) {
                                RoomClass room = new RoomClass();
                                room.setID(Integer.parseInt(item.get(1)));
                                room.setTenBan(item.get(0));
                                room.setAreaID(Integer.parseInt(item.get(3)));

                                room.setStatus(Integer.parseInt(item.get(5)));
                                room.setTableOrderIndex(Integer.parseInt(item.get(6)));
                                if (room.getStatus() == 0)
                                    room.setHinhAnh(Common._API_Link + Common._IMG_EMPTY);
                                else
                                    room.setHinhAnh(Common._API_Link + Common._IMG_USED);

                                room.setAreaName(item.get(4));

                                //Common._ROOM_SELECTED_OBJ=room;
                                if (Common._CONTROL_VIEW_ROOM != null)
                                    RoomAdapter.setViewHolder(Common._CONTROL_VIEW_ROOM, room);
                                return;
                            }
                        }
                    } else
                        Common.errorWhenNotConnected(context);

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


    public static void getAll(final Context context, final String mToken, final int status) {
        try {
            APIInterface mApiInterface = APIClient.getClient(Common._API_Link).create(APIInterface.class);
            Call<Object> callRoom = mApiInterface.doGetRoom(new CF_Room.GetAll(mToken, ""));
            callRoom.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {

                          ArrayList<RoomClass> mLstRoom = new ArrayList<RoomClass>();
                          boolean isFinishedLoadData = false;
                        String tableorder = "";
                        for (int i = data.Data.size() - 1; i >= 0; i--) {
                            ArrayList<String> item = data.Data.get(i);
                            if ((status == -1 || Integer.parseInt(item.get(5)) == status)) {
                                RoomClass room = new RoomClass();

                                room.setID(Integer.parseInt(item.get(1)));
                                room.setTenBan(item.get(0));
                                room.setAreaID(Integer.parseInt(item.get(3)));

                                room.setStatus(Integer.parseInt(item.get(5)));
                                room.setTableOrderIndex(Integer.parseInt(item.get(6)));
                                room.setBranchID(Integer.parseInt(item.get(8)));
                                if (room.getStatus() == 0)
                                    room.setHinhAnh(Common._API_Link + Common._IMG_EMPTY);
                                else
                                    room.setHinhAnh(Common._API_Link + Common._IMG_USED);

                                room.setAreaName(item.get(4));
                                mLstRoom.add(room);
                            }
                        }
                    /*
                    Collections.sort(mLstRoom, new Comparator<RoomClass>() {
                        public int compare(RoomClass o1, RoomClass o2) {
                            return o1.getTenBan().compareTo(o2.getTenBan());
                        }
                    });
                    */
                        Common.setRoom(context, mLstRoom);
                        isFinishedLoadData = true;
                    }
                    //call.cancel();
                    else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            //Common.errorWhenNotConnected(context);
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

    public static void getAllRealTime(final Context context, final String mToken, final int status) {
        try {
            APIInterface mApiInterface = APIClient.getClient(Common._API_Link).create(APIInterface.class);
            Call<Object> callRoom = mApiInterface.doGetRoom(new CF_Room.GetAll(mToken, ""));
            callRoom.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);
                    if (data.Status.contains("OK")) {

                        ArrayList<RoomClass> mLstRoom = new ArrayList<RoomClass>();
                        boolean isFinishedLoadData = false;
                        String tableorder = "";
                        for (int i = data.Data.size() - 1; i >= 0; i--) {
                            ArrayList<String> item = data.Data.get(i);
                            if ((status == -1 || Integer.parseInt(item.get(5)) == status)) {
                                RoomClass room = new RoomClass();

                                room.setID(Integer.parseInt(item.get(1)));
                                room.setTenBan(item.get(0));
                                room.setAreaID(Integer.parseInt(item.get(3)));

                                room.setStatus(Integer.parseInt(item.get(5)));
                                room.setTableOrderIndex(Integer.parseInt(item.get(6)));
                                room.setBranchID(Integer.parseInt(item.get(8)));
                                if (room.getStatus() == 0)
                                    room.setHinhAnh(Common._API_Link + Common._IMG_EMPTY);
                                else
                                    room.setHinhAnh(Common._API_Link + Common._IMG_USED);

                                room.setAreaName(item.get(4));
                                mLstRoom.add(room);
                            }
                        }
                    /*
                    Collections.sort(mLstRoom, new Comparator<RoomClass>() {
                        public int compare(RoomClass o1, RoomClass o2) {
                            return o1.getTenBan().compareTo(o2.getTenBan());
                        }
                    });
                    */
                        Common.setRoomReal(context, mLstRoom);
                        isFinishedLoadData = true;
                    }
                    //call.cancel();
                    else {
                        if (data.HttpStatusCode.equals(Common._HTTPSTATUS_ERROR_PROCESS)) {
                            Common.showFailedToast(context, data.Message);
                        } else {
                            //Common.errorWhenNotConnected(context);
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

    public static void getAllByAreaIDAndStatus(final Context context, final String mToken, final int AreaID, final int status, final Spinner grd) {
        try {
            APIInterface mApiInterface = APIClient.getClient(Common._API_Link).create(APIInterface.class);
            Call<Object> callRoom = mApiInterface.doGetRoom(new CF_Room.GetAll(mToken, ""));
            callRoom.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    DataResult result = new DataResult();
                    DataResult.Data data = result.getDataResult(response);

                    if (data.Status.contains("OK")) {

                        ArrayList<SpinerClass> lstSpinerClasses = new ArrayList<SpinerClass>();
                        for (int i = data.Data.size() - 1; i >= 0; i--) {
                            ArrayList<String> item = data.Data.get(i);
                            if ((AreaID == Integer.parseInt(item.get(3)) || AreaID == 0 || AreaID
                                    == -1) && (status == -1 || Integer.parseInt(item.get(5)) == status)) {
                                SpinerClass room = new SpinerClass();

                                room.setValue(item.get(1));
                                room.setName(item.get(0));
                                lstSpinerClasses.add(room);

                            }
                        }
                    /*
                    Collections.sort(mLstRoom, new Comparator<RoomClass>() {
                        public int compare(RoomClass o1, RoomClass o2) {
                            return o1.getTenBan().compareTo(o2.getTenBan());
                        }
                    });
                    */
                        SpinerAdapter adapter = new SpinerAdapter(context, lstSpinerClasses);
                        grd.setAdapter(adapter);
                    }
                    //call.cancel();
                    else {
                        if (data.HttpStatusCode == Common._HTTPSTATUS_ERROR_PROCESS) {
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
}
