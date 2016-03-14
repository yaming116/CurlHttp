package com.ningso.libcurl;

import com.ningso.libcurl.generates.CurlCode;

/**
 * * Created by walexy on 15/1/31.下午5:39
 *
 * @author walexy
 */
public class CurlException extends RuntimeException {

    private static final long serialVersionUID = -5532332305546682790L;

    private final CurlCode curlCode;

    public CurlException() {
        super();
        this.curlCode = null;
    }

    public CurlException(String detailMessage) {
        super(detailMessage);
        curlCode = null;
    }

    public CurlException(CurlCode curlCode) {
        super("curlCode: " + curlCode);
        this.curlCode = curlCode;
    }

    /**
     * @return may be null
     */
    public CurlCode getCurlCode() {
        return curlCode;
    }
}
