package com.ningso.libcurl.callback;

/**
 * Created by Sun on 2016/4/16.
 */
public interface CurlDownloadCallback {
    /**
     *  下载回调
     * @param totalCount
     * @param count
     */
    public void process(long totalCount, long count);
}
