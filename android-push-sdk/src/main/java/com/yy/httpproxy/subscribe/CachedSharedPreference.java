package com.yy.httpproxy.subscribe;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.HashMap;
import java.util.Map;


public class CachedSharedPreference {

    private SharedPreferences preferences;
    private Map<String, String> cache = new HashMap<>();

    public CachedSharedPreference(Context context) {
        preferences = context.getSharedPreferences("SharedPreferencePushGenerator", Context.MODE_PRIVATE);
    }

    public void save(String key, String value) {
        cache.put(key, value);
        SharedPreferences.Editor editor = preferences.edit().putString(key, value);
        if (Build.VERSION.SDK_INT > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public String get(String key) {
        String value = cache.get(key);
        if (value == null) {
            value = preferences.getString(key, null);
        }
        return value;
    }
}
