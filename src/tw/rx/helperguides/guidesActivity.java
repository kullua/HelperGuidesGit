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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class guidesActivity extends Activity {
	
    //private ArrayList<String> list = new ArrayList<String>();
    //private SimpleAdapter adapter = null;
    private List<HashMap<String, Object>> videos = null;
    private HashMap<String, Object> video = null;
    final String action = "1";
    final String type = "1";
    String typeid="";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guides_activity);
        
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
        typeid = bundle.getString("id");
        TextView textTitle = (TextView) findViewById(R.id.textView1);
        if (typeid.equals("1") ) {
        	textTitle.setText(R.string.gameguideslistTitle1);
        }else if (typeid.equals("2") ) {
        	textTitle.setText(R.string.gameguideslistTitle2);
        }else if (typeid.equals("3") ) {
        	textTitle.setText(R.string.gameguideslistTitle3);
        }

        String data = sendPostDataToInternet(action,type,typeid);
        
        try {  
            JSONArray mArray = new JSONArray(data);
            videos = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < mArray.length(); i++) {
                JSONObject object = mArray.getJSONObject(i);
                
                String flag = object.getString("flag");
                String strf = "false";

                if (flag.equals(strf)) {
                	TextView text = (TextView) findViewById(R.id.textView2);
                	text.setText("�|�L���");
                } else {
	                String subject = object.getString("subject");
	                String id = object.getString("id");
	                video = new HashMap<String, Object>();
	                video.put("id", id);
	                video.put("subject", subject);
	                videos.add(video);
                }
            }
    	} catch (JSONException e) {  
            e.printStackTrace();  
        } 
        
        //BindData
        List<HashMap<String,Object>> ListData = videos;
        SimpleAdapter ListAdapter = new SimpleAdapter(this,ListData, R.layout.guides_list,     
                                        new String []{"id","subject"},   
                                        new int []{R.id.id,R.id.subject});  
          
        final ListView list = (ListView)findViewById(R.id.listView1);
        list.setAdapter(ListAdapter);
        
        //ItemClick
        list.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(boardActivity.this, "�I����"+ (arg2+1) +"�Ӷ���", Toast.LENGTH_LONG).show();

				try{
				HashMap<String,String> map= (HashMap<String,String>) list.getItemAtPosition(arg2);
				String strid=map.get("id"); 
				
				Intent intent = new Intent(guidesActivity.this,guidesPage.class);
		        intent.putExtra("id", strid);
		        intent.putExtra("typeid", typeid);
		        startActivity(intent);
		        guidesActivity.this.finish();
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
			Intent intent = new Intent(guidesActivity.this,MainActivity.class);
	        startActivity(intent);
            
            return true;
        }
    	
    	return super.onKeyDown(keyCode, event);

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
