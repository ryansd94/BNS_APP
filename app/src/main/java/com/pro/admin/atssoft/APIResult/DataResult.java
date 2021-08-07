package com.pro.admin.atssoft.APIResult;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DataResult {



    public class DataResultStatus
    {
        public String HttpStatusCode;
        public String Status;
        public String Message;
    }


    public class item{
        public List<String> Data;
    }

    public class Data
    {
        public String HttpStatusCode;
        public String Status;
        public String Message;

        public ArrayList<ArrayList<String>> Data;
    }

    public class DataNew
    {
        public String HttpStatusCode;
        public String Status;
        public String Message;

        public Object Data;
    }

    public Data getDataResult( Response<Object> response)
    {
        String resultJSON = "";
        Object resource = response.body();
        resultJSON = resource.toString();

        Data dt=new Data();
        Gson gson = new Gson();

        DataResultStatus result = gson.fromJson(resultJSON, DataResultStatus.class);
        dt.HttpStatusCode=result.HttpStatusCode;
        dt.Message=result.Message;
        dt.Status=result.Status;

        dt.Data=new ArrayList<ArrayList<String>>();
        JSONObject json = null;
        try {
            json = new JSONObject(resultJSON);
            if (json !=null) {
                try {
                    Object data2  = json.getString("Data");
                    JSONArray json2 = new JSONArray(data2.toString());
                    for(int i=0;i<=json2.length()-1;i++) {

                        Object item = json2.get(i);

                        JSONObject json3 = new JSONObject(item.toString());

                        Object data3 = json3.getString("Data");

                        JSONArray jsonarr = new JSONArray(data3.toString());


                        ArrayList<String> list = new Gson().fromJson(jsonarr.toString(), new TypeToken<ArrayList<String>>(){}.getType());
                        dt.Data.add(list);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return dt;

    }


    public DataNew getDataResultNew( Response<Object> response)
    {
        String resultJSON = "";
        Object resource = response.body();
        resultJSON = resource.toString();

        DataNew dt=new DataNew();
        Gson gson = new Gson();

        DataResultStatus result = gson.fromJson(resultJSON, DataResultStatus.class);
        dt.HttpStatusCode=result.HttpStatusCode;
        dt.Message=result.Message;
        dt.Status=result.Status;


        JSONObject json = null;
        try {
            json = new JSONObject(resultJSON);
            if (json !=null) {
                try {
                    Object data2  = json.getString("Data");
                    dt.Data=data2;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dt;

    }

}
