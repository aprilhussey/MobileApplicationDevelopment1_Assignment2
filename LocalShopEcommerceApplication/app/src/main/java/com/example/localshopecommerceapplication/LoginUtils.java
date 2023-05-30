package com.example.localshopecommerceapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginUtils {

    public static void setLoginStatus(Context context, boolean loggedIn, String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_logged_in", loggedIn);
        editor.putString("email", email);
        editor.apply();
    }

    public static boolean getLoginStatus(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_logged_in", false);
    }

    public static String getCurrentEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        return sharedPreferences.getString("email", null);
    }
}
