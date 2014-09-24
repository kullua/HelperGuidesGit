package tw.rx.helperguides;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xml.sax.XMLReader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class searchPage extends Activity {
	
    final String type = "2";
    String typeid = "";
    String searchText="";
	
	@SuppressLint("SetJavaScriptEnabled") @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //���ü��D
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView(R.layout.webview);

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
		Bundle bundle = intent.getExtras();	//���oBundle
		String id= bundle.getString("id");
		searchText= bundle.getString("searchText");

	    WebView myWebView = (WebView) findViewById(R.id.webview);
	    myWebView.getSettings().setJavaScriptEnabled(true);
	    myWebView.requestFocus();
	    myWebView.setWebViewClient(new MyWebViewClient());
	    myWebView.loadUrl("HTTP://trx.loveu.tw/apiHelperSearch.php?type="+type+"&id="+id);
	    
	    //String data = sendPostDataToInternet(action,type,bundle.getString("id"));
	    //text.setText(data);
	}
	
	ImageGetter imgGetter = new Html.ImageGetter() { 
		@Override 
		public Drawable getDrawable(String source) { 
		Drawable drawable = null; 
		drawable = Drawable.createFromPath(source); // Or fetch it from the URL 
		// Important 
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable 
		.getIntrinsicHeight()); 
		return drawable; 
		} 
		};
		
		private class MyWebViewClient extends WebViewClient {
			
			        @Override
			
			        public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			            return super.shouldOverrideUrlLoading(view, url);
			
			        }
			
			    }
	
	
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	    	
	    	if (keyCode == KeyEvent.KEYCODE_BACK)
	        {
	            // Show home screen when pressing "back" button,
	            //  so that this app won't be closed accidentally
				Intent intent = new Intent(searchPage.this,searchActivity.class);
				intent.putExtra("searchText", searchText);
		        startActivity(intent);
		        searchPage.this.finish();
	            
	            return true;
	        }
	    	
	    	return super.onKeyDown(keyCode, event);

	    }
		
	public JSONObject getJSON(String sb) throws JSONException {  
        return new JSONObject(sb);  
    } 
	
	public String sendPostDataToInternet(String action,String type,String id){
		String uriAPI = "http://trx.loveu.tw/apiHelperGuide.php";
    	/* �إ�http post �s�u */
    	HttpPost httpRequest = new HttpPost(uriAPI);
    	/* Post �B�@�ǰe�ܼƥ�����NameValuePair[]�}�C�x�s */
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("action",action));
    	params.add(new BasicNameValuePair("type",type));
    	params.add(new BasicNameValuePair("id",id));
    	
    	try{
    		/* �o�XHttp Request */
    		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
    		httpRequest.setEntity(entity);
    		/* �o�XHttp Response */
    		HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
    		/* ���A�X�Y��200 ok */
    		if (httpResponse.getStatusLine().getStatusCode() == 200)
    		{
    			/* ���X�^���r�� */
    			String strResult = EntityUtils.toString(httpResponse.getEntity());
    			/* �^�Ǧ^���r�� */
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
