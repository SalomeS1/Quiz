package com.example.homeworkquiz_extended.data;

import android.content.Context;

public interface Storage {

    void add(Context context, String key, Object obj);

    Object getObj(Context context, String key, Class clas);

    String getStr(Context context, String key);
}
