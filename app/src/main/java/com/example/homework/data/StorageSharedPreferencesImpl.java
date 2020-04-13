package com.example.homework.data;


import android.content.Context;
import android.content.SharedPreferences;

public class StorageSharedPreferencesImpl implements Storage {

    private static final String LOGIN_FILE = "last_score";

    @Override
    public void save(Context context, String key, String value) {
        SharedPreferences sharedPref = getInstance(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    @Override
    public String get(Context context, String key) {
        SharedPreferences sharedPref = getInstance(context);
        return sharedPref.getString(key, null);
    }

    @Override
    public boolean exists(Context context, String key) {
        return get(context, key) != null;
    }

    private SharedPreferences getInstance(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(LOGIN_FILE, Context.MODE_PRIVATE);
        return sharedPref;
    }

}
