package com.app.fabrentopartner.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    private static PrefUtils instance;

    private final String KeyLogin = "isLogin";
    private static final String KeyFcmToken = "fcmtoken";
    private static final String KeyDevice = "android_id";
    private static final String Key_DeviceId = "tagdeviceid";
    private static final String KeyCityID = "cityid";
    private static final String KeyStatus = "satatus";
    private static final String KeyUser = "userid";
    private static final String KeyCityName = "cityname";
    private static final String Keycartid = "cartid";
    private static final String KeyCartCount = "cartcount";
    private static final String KeyInvoiceToken = "invoice";
    private static final String KeyGenInvoiceToken = "invoicetoken";
    private SharedPreferences preferences;
    private String prefText = "com.app.fabrento";

    private PrefUtils(Context context) {
        // TODO Auto-generated constructor stub
        preferences = context.getSharedPreferences(prefText, Context.MODE_PRIVATE);
    }

    public synchronized static PrefUtils getInstance(Context context) {
        if (instance == null) {
            instance = new PrefUtils(context);
        }
        return instance;
    }

    public String getLogin() {
        return preferences.getString(KeyLogin, "");

    }

    public void setIsLogin(String login) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyLogin, login);
        editor.commit();
    }


    public String getKeyStatus() {
        return preferences.getString(KeyStatus, "");

    }

    public void setIsStatus(String satatus) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyStatus, satatus);
        editor.commit();
    }

    public String getKeyUser() {
        return preferences.getString(KeyUser, "");

    }

    public void setIsUser(String userid) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyUser, userid);
        editor.commit();
    }



    public String getFcmToken() {
        return preferences.getString(KeyFcmToken, "");
    }

    public void setFcmToken(String fcmtoken) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyFcmToken, fcmtoken);
        editor.commit();
    }

    /*your mobile Id save here*/
    public String getKeyDeviceId() {
        return preferences.getString(KeyDevice, "");
    }

    public void setDeviceId(String android_id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyDevice, android_id);
        editor.commit();
    }

    public String getCityId() {
        return preferences.getString(KeyCityID, "");
    }

    public void setCityId(String cityid) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyCityID, cityid);
        editor.commit();
    }

    public String getCityName() {
        return preferences.getString(KeyCityName, "");
    }

    public void setCityName(String cityname) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyCityName, cityname);
        editor.commit();
    }

    public void isFromDeepLink(Context context, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFromDeepLink", value);
        editor.apply();
    }

    public String getKeycartid() {
        return preferences.getString(Keycartid, "");
    }

    public void setKeycartid(String cartid) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Keycartid, cartid);
        editor.commit();
    }

    public String getKeyCartCount() {
        return preferences.getString(KeyCartCount, "");
    }

    public void setKeyCartCount(String cartcount) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyCartCount, cartcount);
        editor.commit();
    }

    public String getKeyInvoiceToken() {
        return preferences.getString(KeyInvoiceToken, "");
    }

    public void setKeyInvoiceToken(String invoice) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyInvoiceToken, invoice);
        editor.commit();
    }

    public String getKeyGenInvoiceToken() {
        return preferences.getString(KeyGenInvoiceToken, "");
    }


    public void setKeyGenInvoiceToken(String invoicetoken) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyGenInvoiceToken, invoicetoken);
        editor.commit();
    }
}


