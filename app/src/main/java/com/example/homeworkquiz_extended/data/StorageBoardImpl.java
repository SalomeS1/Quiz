package com.example.homeworkquiz_extended.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class StorageBoardImpl implements Storage {

    public static final String BOARD = "LeaderBoard";

    public void add(Context context, String key, Object obj)
    {
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, new Gson().toJson(obj));
        editor.commit();
    }

    public Object getObj(Context context, String key, Class clas)
    {
        SharedPreferences sharedPreferences = getInstance(context);
        String data = sharedPreferences.getString(key, null);
        return data == null ? null : new Gson().fromJson(data, clas);
    }

    public String getStr(Context context, String key)
    {
        SharedPreferences sharedPreferences = getInstance(context);
        return sharedPreferences.getString(key, null);
    }

    private SharedPreferences getInstance(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(BOARD, Context.MODE_PRIVATE);
        return sharedPref;
    }
}
