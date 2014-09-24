package tw.rx.helperguides;

import java.io.IOException;
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

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class searchActivity extends Activity {
	
    //private ArrayList<String> list = new ArrayList<String>();
    //private SimpleAdapter adapter = null;
    private List<HashMap<String, Object>> videos = null;
    private HashMap<String, Object> video = null;
    final String action = "1";
    final String type = "1";
    String searchText="";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隱藏標題
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.search_activity);
        
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
		searchText = bundle.getString("searchText");
        TextView textTitle = (TextView) findViewById(R.id.textView1);
        

        String data = sendPostDataToInternet(type,searchText,"");
        
        try {  
            JSONArray mArray = new JSONArray(data);
            videos = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < mArray.length(); i++) {
                JSONObject object = mArray.getJSONObject(i);
                
                String flag = object.getString("flag");
                String strf = "false";
                

                if (flag.equals(strf)) {
                	TextView text = (TextView) findViewById(R.id.textView2);
                	text.setText("尚無資料");
                } else {
	                String subject = object.getString("subject");
	                String id = object.getString("id");
	                String typename = object.getString("typename");
	                video = new HashMap<String, Object>();
	                video.put("id", id);
	                video.put("subject", subject);
	                video.put("typename", typename);
	                videos.add(video);
                }
            }
    	} catch (JSONException e) {  
            e.printStackTrace();  
        } 
        
        //BindData
        List<HashMap<String,Object>> ListData = videos;
        SimpleAdapter ListAdapter = new SimpleAdapter(this,ListData, R.layout.guides_list,     
                                        new String []{"id","subject","typename"},   
                                        new int []{R.id.id,R.id.subject,R.id.typename});  
          
        final ListView list = (ListView)findViewById(R.id.listView1);
        list.setAdapter(ListAdapter);
        
        //ItemClick
        list.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(boardActivity.this, "點擊第"+ (arg2+1) +"個項目", Toast.LENGTH_LONG).show();

				try{
				HashMap<String,String> map= (HashMap<String,String>) list.getItemAtPosition(arg2);
				String strid=map.get("id"); 
				
				Intent intent = new Intent(searchActivity.this,searchPage.class);
		        intent.putExtra("id", strid);
		        intent.putExtra("searchText", searchText);
		        
		        startActivity(intent);
		        searchActivity.this.finish();
				} catch(Exception e){
					e.getMessage();
				}
			}
        });

	}

    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
    	if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // Show home screen when pressing "back" button,
            //  so that this app won't be closed accidentally
			Intent intent = new Intent(searchActivity.this,MainActivity.class);
	        startActivity(intent);
            
            return true;
        }
    	
    	return super.onKeyDown(keyCode, event);

    }

	public String sendPostDataToInternet(String type,String searchText,String id){
		String uriAPI = "http://trx.loveu.tw/apiHelperSearch.php";
    	/* 建立http post 連線 */
    	HttpPost httpRequest = new HttpPost(uriAPI);
    	/* Post 運作傳送變數必須用NameValuePair[]陣列儲存 */
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("searchText",searchText));
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
