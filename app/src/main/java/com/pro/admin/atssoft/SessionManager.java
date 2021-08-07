package com.pro.admin.atssoft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.pro.admin.atssoft.APIResult.Common;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import model.CategoryClass;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String EmployeeATID = "EmployeeATID";
    public static final String EmployeeName = "EmployeeName";
    public static final String USER_TOKEN = "USER_TOKEN";
    public static final String API_SELECTED = "API_SELECTED";
    public static final String API_LIST = "API_LIST";
    public static final String USE_MOBILE = "USE_MOBILE";
public static final String SCREEN_OPTION="SCREEN_OPTION";

    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String USER_REMEMBER = "USER_REMEMBER";
    public static final String USER_SHOPCODE = "USER_SHOPCODE";
    public static final String USER_AREA_SELECTED_LIST = "_AREA_SELECTED_LIST";
    public static final String USER_AREA_ID_SELECTED_LIST = "_AREA_ID_SELECTED_LIST";

    public static final String USER_BRANCH_SELECTED_LIST = "_BRANCH_SELECTED_LIST";
    public static final String USER_BRANCH_ID_SELECTED_LIST = "_BRANCH_ID_SELECTED_LIST";
    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public String getKey()
    {
        return Common._USER_SHOPCODE+"_"+Common._USER_USERNAME+"_";
    }



    public void saveScreenSetting(int screenValue) {
        editor.putInt(SCREEN_OPTION, screenValue);
        editor.apply();

        //Common._USE_MOBILE = useMobile;
    }

    public  int getScreenSetting()
    {
        return sharedPreferences.getInt(SCREEN_OPTION,0);
    }
    public void saveListAPI(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor.putString(API_LIST, json);
        //editor.putBoolean(USE_MOBILE, useMobile);
        editor.apply();

        //Common._USE_MOBILE = useMobile;
    }


    public   ArrayList<String> getListAPI() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(API_LIST, "");
            if (json.length() == 0)
                return list;
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            list = gson.fromJson(json, type);
        } catch (Exception ex) {
        }
        return list;
    }

    public void saveAreaSelect( ArrayList<CategoryClass> listArea) {
        Gson gson = new Gson();
        String json = gson.toJson(listArea);

        editor.putString(getKey() + USER_AREA_SELECTED_LIST, json);
        editor.apply();

    }

    public void saveBranchSelect( ArrayList<CategoryClass> listArea) {
        Gson gson = new Gson();
        String json = gson.toJson(listArea);

        editor.putString(getKey() + USER_BRANCH_SELECTED_LIST, json);
        editor.apply();

    }



    public void saveAreaIDSelect( ArrayList<Integer> listArea) {
        Gson gson = new Gson();
        String json = gson.toJson(listArea);

        editor.putString(getKey() + USER_AREA_ID_SELECTED_LIST, json);
        editor.apply();

    }


    public void saveBranchIDSelect( ArrayList<Integer> listArea) {
        Gson gson = new Gson();
        String json = gson.toJson(listArea);

        editor.putString(getKey() + USER_BRANCH_ID_SELECTED_LIST, json);
        editor.apply();

    }



    public   ArrayList<CategoryClass> getCategorySelect(String key) {
        ArrayList<CategoryClass> areaList = new ArrayList<CategoryClass>();
        try {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(getKey() +key , "");
            if (json.length() == 0)
                return areaList;
            Type type = new TypeToken<ArrayList<CategoryClass>>() {
            }.getType();
            areaList = gson.fromJson(json, type);
        } catch (Exception ex) {
            String a=ex.toString();
        }
        return areaList;
    }

    public   ArrayList<Integer> getCategoryIDSelect(String key) {
        ArrayList<Integer> areaList = new ArrayList<Integer>();
        try {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(getKey() +key , "");
            if (json.length() == 0)
                return areaList;
            Type type = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            areaList = gson.fromJson(json, type);
        } catch (Exception ex) {
        }
        return areaList;
    }




    public void createSession( String EmployeeName, String token){

        editor.putBoolean(LOGIN, true);
        editor.putString(SessionManager.EmployeeName, EmployeeName);
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public void saveInfo(String userName,String passWord,Boolean isRemember,String shopCode)
    {
        editor.putString(USER_NAME, userName);
        editor.putString(USER_PASSWORD, passWord);
        editor.putBoolean(USER_REMEMBER, isRemember);
        editor.putString(USER_SHOPCODE, shopCode);
        editor.apply();
    }

    public void clearInfo()
    {
        editor.putString(USER_NAME, "");
        editor.putString(USER_PASSWORD, "");
        editor.putString(USER_SHOPCODE, "");
        editor.putBoolean(USER_REMEMBER, false);
        editor.apply();
    }
    public void createAPIList(String select)
    {
        editor.putString(API_SELECTED, select);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if (!this.isLoggin()){
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(EmployeeATID, sharedPreferences.getString(EmployeeATID, null));
        user.put(EmployeeName, sharedPreferences.getString(EmployeeName, null));
        user.put(USER_TOKEN, sharedPreferences.getString(USER_TOKEN, null));

        return user;
    }


    public HashMap<String, String> getInfoLogin() {

        HashMap<String, String> user = new HashMap<>();
        user.put(USER_NAME, sharedPreferences.getString(USER_NAME, null));
        user.put(USER_PASSWORD, sharedPreferences.getString(USER_PASSWORD, null));
        user.put(USER_REMEMBER, String.valueOf(sharedPreferences.getBoolean(USER_REMEMBER, false)));
        user.put(USER_SHOPCODE, sharedPreferences.getString(USER_SHOPCODE, null));
        return user;
    }


    public String getAPILink(){
        if(sharedPreferences.getString(API_SELECTED, Common._API_Link).length()==0)
            return Common._API_Link;
        return sharedPreferences.getString(API_SELECTED, Common._API_Link);
    }


    public void logout(){
        if(Common.__TimerLoadRoom != null)
        {
            Common.__TimerLoadRoom.cancel();
            Common.__TimerLoadRoom = null;
        }
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((MainActivity) context).finish();



    }
}
