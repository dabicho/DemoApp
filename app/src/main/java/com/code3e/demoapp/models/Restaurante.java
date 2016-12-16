package com.code3e.demoapp.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 16/12/16.
 */

public class Restaurante {
    private int id;
    public String name;
    public String categories;
    public String logo;
    private final String TAG = "Restaurante";

    public int getId(){
        return this.id;
    }

    public Restaurante(JSONObject jsonObject){
        this.id=0;
        this.name="";
        this.categories="";
        this.logo="";

        try {
            this.id = jsonObject.getInt("id");
            this.name=jsonObject.getString("name");
            this.logo=jsonObject.getString("logo");
            JSONArray categories = jsonObject.getJSONArray("categories");
            for(int idx = 0; idx < categories.length(); idx++){
                this.categories = this.categories + ", " + categories.getString(idx);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Restaurante: ", e);
        }
    }
}

