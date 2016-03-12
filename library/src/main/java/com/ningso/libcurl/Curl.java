package com.ningso.libcurl;

import android.util.Log;

import com.ningso.libcurl.generates.CurlCode;
import com.ningso.libcurl.generates.CurlConstant;
import com.ningso.libcurl.generates.CurlFormadd;
import com.ningso.libcurl.generates.CurlOpt.OptLong;
import com.ningso.libcurl.generates.CurlOpt.OptFunctionPoint;
import com.ningso.libcurl.generates.CurlOpt.OptObjectPoint;
import com.ningso.libcurl.params.MultiPart;

import java.util.List;

/**
 * Curl的jni封装
 * 使用方法参考测试代码
 * Curl对象并非线程安全，请勿在多线程环境下共用一个Curl对象
 *
 * @author walexy
 * @createTime 2015-01-29 12:39:39
 * @see http://curl.haxx.se/libcurl/c/
 */
public class Curl {

    private static final String TAG = Curl.class.getSimpleName();

    private long handle;

    public interface Callback {
    }

    private static Object CLEANUP = new Object() {

        @Override
        protected void finalize() throws Throwable {
            if (INIT) {
                // Log.i(TAG, "curlGlobalCleanup");
                curlGlobalCleanupNative();
            }
        }

    };

    private static boolean INIT = false;

    public interface WriteCallback extends Callback {
        /**
         * 当接受到数据时被调用
         *
         * @param data
         * @return 实际接受的数据长度
         * @see http://curl.haxx.se/libcurl/c/CURLOPT_WRITEFUNCTION.html
         */
        public int readData(byte[] data);
    }

    public interface ReadCallback extends Callback {
        /**
         * 发送时调用
         *
         * @param data 待发送的数据
         * @return 实际再内存中存储的数据大小
         * @see http://curl.haxx.se/libcurl/c/CURLOPT_READFUNCTION.html
         */
        public int writeData(byte[] data);
    }

    public Curl() {
        if (!INIT) {
            curlGlobalInit(CurlConstant.CURL_GLOBAL_DEFAULT);
        }
    }

    public static void curlGlobalInit(int flags) {
        if (INIT) {
            return;
        }

        CurlCode code = CurlCode.fromValue(curlGlobalInitNative(flags));
        if (code != CurlCode.CURLE_OK) {
            throw new IllegalStateException("curlGlobalInit fail: " + code);
        }
        INIT = true;
    }

    private native static int curlGlobalInitNative(int flags);

    private native static void curlGlobalCleanupNative();

    public void curlEasyInit() throws CurlException {
        Log.v(TAG, "curlEastInit");
        handle = curlEasyInitNative();
        if (handle == 0) {
            throw new CurlException("curl init native fail");
        }
    }

    private native long curlEasyInitNative();

    public void curlEasyCleanup() {
        Log.v(TAG, "curlEastCleanup: " + handle);
        if (handle != 0) {
            curlEasyCleanupNative(handle);
        }
        handle = 0;
    }

    private native void curlEasyCleanupNative(long handle);

    /**
     * @param opt   {@link OptLong}
     * @param value
     * @return
     */
    public CurlCode curlEasySetopt(OptLong opt, long value) {
        Log.v(TAG, "curlEastSetopt: " + opt + "=" + value);
        return CurlCode.fromValue(curlEasySetoptLongNative(handle,
                opt.getValue(), value));
    }

    private native int curlEasySetoptLongNative(long handle, int opt, long value);

    public CurlCode curlEasySetopt(OptFunctionPoint opt, WriteCallback callback) {
        Log.v(TAG, "curlEastSetopt: " + opt + "=" + callback);
        return CurlCode.fromValue(curlEasySetoptFunctionNative(handle,
                opt.getValue(), callback));
    }

    private native int curlEasySetoptFunctionNative(long handle, int opt,
                                                    Callback callback);

    public CurlCode curlEasySetopt(OptObjectPoint opt, String value) {
        Log.v(TAG, "curlEastSetopt: " + opt + "=" + value);
        return CurlCode.fromValue(curlEasySetoptObjectPointNative(handle,
                opt.getValue(), value));
    }

    private native int curlEasySetoptObjectPointNative(long handle, int opt,
                                                       String value);

    public CurlCode curlEasySetopt(OptObjectPoint opt, byte[] value) {
        Log.v(TAG, "curlEastSetopt: " + opt + "=" + value);
        return CurlCode.fromValue(curlEasySetoptObjectPointBytesNative(handle,
                opt.getValue(), value));
    }

    private native int curlEasySetoptObjectPointBytesNative(long handle,
                                                            int opt, byte[] value);

    public CurlCode curlEasySetopt(OptObjectPoint opt, String[] values) {
        Log.v(TAG, "curlEastSetopt: " + opt + "=" + values);
        return CurlCode.fromValue(curlEasySetoptObjectPointArrayNative(handle,
                opt.getValue(), values));
    }

    private native int curlEasySetoptObjectPointArrayNative(long handle,
                                                            int opt, String[] value);

    /**
     * @param multiParts
     * @return
     */
    public CurlFormadd setFormdata(List<MultiPart> multiParts) {
        if (multiParts != null && multiParts.size() > 0) {
            return CurlFormadd.fromValue(setFormdataNative(handle,
                    multiParts.toArray(new MultiPart[multiParts.size()])));
        } else {
            return CurlFormadd.CURL_FORMADD_NULL;
        }
    }

    private native int setFormdataNative(long handle, MultiPart[] multiArray);

    public CurlCode curlEasyPerform() {
        Log.v(TAG, "curlEasyPerform");
        return CurlCode.fromValue(curlEasyPerformNavite(handle));
    }

    private native int curlEasyPerformNavite(long handle);

    static {
        System.loadLibrary("curl4java");
    }
}
