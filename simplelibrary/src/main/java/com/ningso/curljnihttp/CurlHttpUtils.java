package com.ningso.curljnihttp;

import android.content.Context;

import java.util.Map;

/**
 * Created by NingSo on 16/3/12.下午7:02
 *
 * @author: NingSo
 * @Email: ningso.ping@gmail.com
 */
public class CurlHttpUtils {

    private static boolean libraryLoaded = false;

    static {
        try {
            System.loadLibrary("curlsimaplehttp");
            libraryLoaded = true;
        } catch (Throwable e) {
            libraryLoaded = false;
            e.printStackTrace();
        }
    }

    private static boolean loadAbsoluteLibrary(Context context) {
        if (libraryLoaded) {
            return true;
        } else {
            try {
                System.load("/data/data/" + context.getPackageName() + "/lib/libpulse.so");
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * get
     *
     * @param url
     * @param params
     * @return
     */
    public static String getUrl(String url, Map<String, String> params) {
        String[] keys = new String[params.size()];
        String[] values = new String[params.size()];
        params.size();
        int count = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            keys[count] = entry.getKey();
            values[count++] = entry.getValue();
        }
        return getUrl(url, keys, values);
    }


    /**
     * post
     *
     * @param url
     * @param params
     * @return
     */
    public static String postUrl(String url, Map<String, String> params) {
        String[] keys = new String[params.size()];
        String[] values = new String[params.size()];
        params.size();
        int count = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            keys[count] = entry.getKey();
            values[count++] = entry.getValue();
        }
        return postUrl(url, keys, values);
    }


    public static native String getUrl(String url, String[] keys, String[] values);

    public static native String postUrl(String url, String[] keys, String[] values);
}
