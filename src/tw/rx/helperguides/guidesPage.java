package tw.rx.helperguides;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.widget.TextView;
import android.widget.Toast;

public class guidesPage extends Activity {
	
    final String action = "1";
    final String type = "2";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guides_page);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        //
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD)
        {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()
        .detectDiskWrites()
        .detectNetwork()
        .penaltyLog()
        .build());
        }
		
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();	//取得Bundle
		
	    TextView text = (TextView) findViewById(R.id.textView1);
	    TextView errortext = (TextView) findViewById(R.id.errortext);
	    //text.setText(bundle.getString("id"));
	    String data = sendPostDataToInternet(action,type,bundle.getString("id"));
	    //text.setText(data);
	   
        try {  
            JSONArray mArray = new JSONArray(data);
            
            for (int i = 0; i < mArray.length(); i++) {
                JSONObject object = mArray.getJSONObject(i);
                
                String flag = object.getString("flag");
                if (flag.equals("false")) {
                	text.setText(Html.fromHtml("無資料"));
                } else {
                String subject = object.getString("subject");
                String content = object.getString("content");
                
                setTitle(subject);
                text.setText(Html.fromHtml(content));
                }
            }
    	} catch (JSONException e) {  
            e.printStackTrace();  
        } 
    	
    	//text.setText(Html.fromHtml(content));
	}
	
	public JSONObject getJSON(String sb) throws JSONException {  
        return new JSONObject(sb);  
    } 
	
	public String sendPostDataToInternet(String action,String type,String id){
		String uriAPI = "http://trx.loveu.tw/apiHelperGuide.php";
    	/* 建立http post 連線 */
    	HttpPost httpRequest = new HttpPost(uriAPI);
    	/* Post 運作傳送變數必須用NameValuePair[]陣列儲存 */
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("action",action));
    	params.add(new BasicNameValuePair("type",type));
    	params.add(new BasicNameValuePair("id",id));
    	
    	try{
    		/* 發出Http Request */
    		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
    		httpRequest.setEntity(entity);
    		/* 發出Http Response */
    		HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
    		/* 狀態碼若為200 ok */
    		if (httpResponse.getStatusLine().getStatusCode() == 200)
    		{
    			/* 取出回應字串 */
    			String strResult = EntityUtils.toString(httpResponse.getEntity());
    			/* 回傳回應字串 */
    			return strResult;
    		}
    	} catch (ClientProtocolException e)
    	{
    		Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
    		e.printStackTrace();
    	} catch (IOException e)
    	{
    		Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
    		e.printStackTrace();
    	} catch (Exception e)
    	{
    		Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
    		e.printStackTrace();
    	}
    	return null;
    }
}
