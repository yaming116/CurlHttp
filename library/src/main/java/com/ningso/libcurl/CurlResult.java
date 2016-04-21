package com.ningso.libcurl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by walexy on 15/1/31.下午5:39
 *
 * @author walexy
 */
public class CurlResult {

    private static final String TAG = CurlResult.class.getSimpleName();
    public static final String RESULT_FILE = "{\"R\":200,\"I\":\"下载成功\"}";
    private final int status;
    private final String statusLine;
    private final Map<String, String> headers;
    private final byte[] body;
    private final int type;//0 文本 1 文件
    private final int code;//curl code


    private transient String bodyString;
    private transient byte[] decodedBody;

    public CurlResult(int status, String statusLine, Map<String, String> headers, int type, byte[] body, int code) {
        super();
        this.status = status;
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
        this.type = type;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    //may contains \n at end
    public String getStatusLine() {
        return statusLine;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public int getCode() {
        return code;
    }

    //original body
    public byte[] getBody() {
        if (body == null && type == 1){
            return RESULT_FILE.getBytes();
        }
        return body;
    }

    //decoded if body gzipped
    public byte[] getDecodedBody() throws IOException {
        if (!"gzip".equalsIgnoreCase(getHeader("Content-Encoding"))) {
            return getBody();
        }
        if (decodedBody == null) {
//			Log.d(TAG, "uncompress gzipped content");
            GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(getBody()));
            ByteArrayOutputStream byos = new ByteArrayOutputStream(getBody().length * 3);
            byte[] buf = new byte[4096];
            int len;
            while ((len = gzis.read(buf, 0, buf.length)) != -1) {
                byos.write(buf, 0, len);
            }
            decodedBody = byos.toByteArray();
            gzis.close();
            byos.close();
        }

        return decodedBody;
    }

    //IOException
    public String getBodyAsString() throws IOException {
        if (body == null && type == 1){
            return RESULT_FILE;
        }
        if (bodyString == null) {
            bodyString = new String(getDecodedBody());
        }
        return bodyString;
    }
}
