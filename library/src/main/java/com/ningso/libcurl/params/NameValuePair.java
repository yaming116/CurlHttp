package com.ningso.libcurl.params;

/**
 * Created by NingSo on 16/3/12.下午6:34
 *
 * @author: NingSo
 * @Email: ningso.ping@gmail.com
 */
public class NameValuePair {

    private final String name;
    private final String value;


    public NameValuePair(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    /**
     * @return "" if null value
     */
    public String getValue() {
        if (value == null) {
            return "";
        }
        return value;
    }
}
