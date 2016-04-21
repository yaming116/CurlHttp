package com.ningso.curlhttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ningso.libcurl.CurlHttp;
import com.ningso.libcurl.CurlResult;
import com.ningso.libcurl.callback.CurlDownloadCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTextView = (TextView) findViewById(R.id.tv_result);
        setSupportActionBar(toolbar);

        Log.d("test", getExternalCacheDir() + "/test.apk");

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

//                CurlResult curlResult = CurlHttp.newInstance()
//                        .addHeader("Accept-Encoding", "gzip, deflate, sdch") //
//                        .setHttpProxy("10.0.1.2", 8888) //
//                        .addParam("hello", "World!") //
//                        // passing array like jQuery
//                        .addParam("foo[]", Arrays.asList("Bar!", "Bar!")) //
//                        // multipart form(post only)
//                        .addMultiPartPostParam("multi_a", null, null, bytes) //
//                        .addMultiPartPostParam("multi_b", null, "text/html", bytes) //
//                        .addMultiPartPostParam("multi_c", "c.html", null, bytes) //
//                        .addMultiPartPostParam("multi_d", "d.html", "text/plain", bytes) //
//                        .postUrl("http://your-host/cgi-bin/t.cgi") // or .getUrl(String url)
//                        .execute();
//                try {
//                    int status = curlResult.getStatus();
//                    String statusLine = curlResult.getStatusLine();
//                    String body = curlResult.getBodyAsString();
//                    byte[] binaryData = curlResult.getBody();
//                    byte[] binaryDecodedDate = curlResult.getDecodedBody(); // if gzipped
//                    String header = curlResult.getHeader("ContentType"); // ignore header
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


                new AsyncTask<Void, Void, CurlResult>(){
                    @Override
                    protected CurlResult doInBackground(Void... params) {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("type", "grade");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        File f = new File(getExternalCacheDir() , "test.apk");
                        CurlResult result = CurlHttp.newInstance()
//                        .setHttpProxy("192.168.0.6",8888)
//                                .addHeader("Accept", "*/*")
                                .setFollowLocation(true)
//                                .addHeader("Accept-Encoding", "gzip,deflate")
//                                .setBody("text/xml", "<?xml version=\"1.0\" encoding=\"utf-8\"?><SOAP-ENV:Envelope><SOAP-ENV:Body><ns:GetClientInterfacesUrl><J><Z>1</Z><K xsi:type=\"xsd:string\">eJwtitsKwyAQRP9lnyVEYy76FX0vfTDuiqFpBI2lQfLvtTQwMMyZU+AGGmx4NZ9M3izNavJmPUVgkKwPYbUBqSpcdLIfKn3ScaHRicnZURIfpGqVQiRDqAzN0iFNP/lNMS1hq/KclxVFy/u2F0rUa6e0gy7AQfOTgYnRHKDvdTPOuo6Bw1QDrFzqv87H+QWEgTZ4</K></J></ns:GetClientInterfacesUrl></SOAP-ENV:Body></SOAP-ENV:Envelope>".getBytes())
//                                .addHeader("charset", "utf-8")
//                                .postUrl("http://data.yunzuoye.net/interfaces_launcher/userlogin_2_interfaces.php")
//                                .addParam("Z", "0")
//                                .addParam("K", json.toString())
//                                .postUrl("http://192.168.1.163/api/course/classify")
                                .asFile(f.getAbsolutePath() )
                                .asRange(f.length())
                                .setDownloadCallback(new CurlDownloadCallback() {
                                    @Override
                                    public void process(long totalCount, long count) {
                                        Log.d("test", "start: " + totalCount + " count:" + count);
                                    }
                                })
                                .getUrl("http://m5.apk.67mo.com/apk/PPAssistant_41123_PM_3346.apk")
                                .execute();
                        return result;
                    }

                    @Override
                    protected void onPostExecute(CurlResult result) {
                        super.onPostExecute(result);
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

//                CurlResult result = CurlHttp.newInstance().addParam("ip", "202.108.67.57")
//                        .getUrl("http://ip.taobao.com/service/getIpInfo.php").execute();


                        int status = result.getStatus();
                        String statusLine = result.getStatusLine();

                        try {

                            String body = result.getBodyAsString();
                            byte[] binaryData = result.getBody();
//                            String binaryStr = new String(binaryData, "UTF-8");
                            // byte[] binaryDecodedDate = result.getDecodedBody(); // if gzipped
                            String header = result.getHeader("ContentType"); // ignore header
                            StringBuilder stringBuffer = new StringBuilder();
                            stringBuffer.append("\nstatus = ").append(status)
                                    .append("\nstatusLine=").append(statusLine)
                                    .append("\nbody=").append(body)
                                    .append("\nheader = ").append(header)
                                    .append("\nbinaryStr = ").append("");
                            mTextView.setText(stringBuffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Map<String, String> map = new HashMap<>();
//            map.put("version", "332");
//            map.put("type", "subject");
//            map.put("channel_mark", "豌豆荚");
//            map.put("attr", "1");
//            map.put("lang", "zh");
//            map.put("ftType", "2");
//            String result = CurlHttpUtils.getUrl("http://cdn6.xinmei365.com/cdndata/banner/banner", map);
//            System.out.println("result=" + result);
//            mTextView.setText("result = " + result);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}
