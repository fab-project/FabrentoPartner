package com.app.fabrentopartner.Utility;

import android.content.Context;
import android.content.SharedPreferences;

class MySharedPreferences {
    //instance field
    private static SharedPreferences mSharedPreference;
    private static MySharedPreferences mInstance = null;
    private static Context mContext;

    //Shared Preference key
    private String KEY_PREFERENCE_NAME = "OpeBo";
    //private keyS
    public String KEY_DEFAULT = null;

    SharedPreferences.Editor editor;

    public MySharedPreferences() {
        mSharedPreference = mContext.getSharedPreferences(KEY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreference.edit();
    }

    public static MySharedPreferences getInstance(Context context) {
        mContext = context;
        if (mInstance == null) {
            mInstance = new MySharedPreferences();
        }
        return mInstance;
    }

    //Method to store user Mobile number
    public boolean setKey(String keyname, String mobile) {
        mSharedPreference.edit().putString(keyname, mobile).apply();
        return false;
    }

    //Method to get User mobile number
    public String getKey(String keyname) {
        return mSharedPreference.getString(keyname, KEY_DEFAULT);
    }

    public Boolean chk(String key) {
        return mSharedPreference.contains(key);
    }

}
