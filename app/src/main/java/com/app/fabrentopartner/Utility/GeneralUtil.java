package com.app.fabrentopartner.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class GeneralUtil {

    private static final String ANDROID_ID = "ANDROID_ID";
    private static final String KrooPrefences_IDENTIFIER = "FABRENTO";

    public static void SetUser_Token(Context activity, String tabusertoken) {
        final SharedPreferences prefs = activity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("tabusertoken", tabusertoken);
        editor.commit();
    }

    public static String GetUser_Token(Context iActivity) {
        String count = null;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("tabusertoken", "");
        return count;
    }

    public static void SetName(Context iActivity, String status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", status);
        editor.commit();
    }

    public static String GetName(Context iActivity) {
        String count = null;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("name", "");
        return count;
    }


    public static void SetCity(Context iActivity, String status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", status);
        editor.commit();
    }

    public static String GetCity(Context iActivity) {
        String count = null;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("name", "");
        return count;
    }

    public static void Setemailid(Context iActivity, String status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", status);
        editor.commit();
    }

    public static String Getemailid(Context iActivity) {
        String count = null;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("email", "");
        return count;
    }

    public static void Setpassword(Context iActivity, String status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("password", status);
        editor.commit();
    }

    public static String Getpassword(Context iActivity) {
        String count = null;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("password", "");
        return count;
    }

    public static void Setmobileno(Context iActivity, String status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("mobileno", status);
        editor.commit();
    }

    public static String getMobileno(Context iActivity) {
        String count = null;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("mobileno", "");
        return count;
    }

    public static void SetUser_id(Context iActivity, String status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userid", status);
        editor.commit();
    }


    public static String Get_UserId(Context context) {
        String count = null;
        final SharedPreferences prefs = context.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("userid", "");
        return count;
    }

    /*******************************************/
    public static void SetUserAffiliateCode_id(Context iActivity, String status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userAffiliateCode_id", status);
        editor.commit();
    }


    public static String Get_AffiliateCode_id(Context iActivity) {
        String count = null;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("userAffiliateCode_id", "");
        return count;
    }


    //**************************For stauts*******************//
    public static void SetStatus_id(Context iActivity, String status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("status", status);
        editor.apply();
    }


    public static String Get_StatusId(Context iActivity) {
        String count = null;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getString("status", "");
        return count;
    }


    public static boolean SetIsLogin(Context iActivity, boolean status) {
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("islogin", status);
        editor.commit();
        return false;
    }

    public static boolean GetIsLOgin(Context iActivity) {
        boolean count;
        final SharedPreferences prefs = iActivity.getSharedPreferences(KrooPrefences_IDENTIFIER, Context.MODE_PRIVATE);
        count = prefs.getBoolean("islogin", false);
        return count;
    }
}
