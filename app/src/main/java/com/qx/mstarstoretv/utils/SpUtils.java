package com.qx.mstarstoretv.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePerference工具
 *
 * @author torah
 */
public class SpUtils {

    public static final String key_username = "user";
    public static final String key_password = "password";
    public static final String key_tokenKey = "tokenKey";
    public static final String key_wxId = "wxId";
    public static String LOGIN_USERNAME = "";
    public static String LOGIN_PASSWORD = "";
    public static SharedPreferences sharedPreferences;

    public static SpUtils spUtils;

    public static SpUtils getInstace(Context context) {
        if (spUtils == null) {
            spUtils = new SpUtils();
            spUtils.init(context);
        }
        return spUtils;
    }

    private SpUtils() {

    }

    public void init(Context context) {
        sharedPreferences = context
                .getSharedPreferences("config", 0);
    }

    /**
     * 得到配置文件
     *
     * @return
     */
    public SharedPreferences getSp() {
        return sharedPreferences;
    }

    /**
     * 保存string值到sp文件
     *
     * @param key
     * @param value
     */
    public void saveString(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 保存boolean键值到sp文件
     *
     * @param key
     * @param value
     */
    public void saveBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 得到boolean值
     *
     * @param key
     * @param defValue
     */
    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 取字符串，默认“”
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }


}
