package com.ningso.libcurl.generates;

import java.util.HashMap;

/**
 * Created by NingSo on 16/3/12.下午5:39
 * Auto generated from 'curl/curl.h', DO NOT EDIT!!!
 *
 * @author: NingSo
 */

public enum CurlFormadd {
    CURL_FORMADD_OK(0), //
    CURL_FORMADD_MEMORY(1), //
    CURL_FORMADD_OPTION_TWICE(2), //
    CURL_FORMADD_NULL(3), //
    CURL_FORMADD_UNKNOWN_OPTION(4), //
    CURL_FORMADD_INCOMPLETE(5), //
    CURL_FORMADD_ILLEGAL_ARRAY(6), //
    CURL_FORMADD_DISABLED(7);

    private final int value;

    private static HashMap<Integer, CurlFormadd> valuesMap = new HashMap<>();

    static {
        for (CurlFormadd e : values()) {
            valuesMap.put(e.getValue(), e);
        }
    }

    CurlFormadd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CurlFormadd fromValue(int value) {
        return valuesMap.get(value);
    }
}