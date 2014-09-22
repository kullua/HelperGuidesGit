package tw.rx.helperguides;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {

/*	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;*/
	public static final String URL = "http://trx.loveu.tw/images/Top.jpg";
	ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set text
        //ImageView Bannerbutton = (ImageView) findViewById(R.id.imageBanner);
        imageView = (ImageView) findViewById(R.id.imageBanner);
        // Create an object for subclass of AsyncTask
        GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[] { URL });
        
        ImageView boardbutton = (ImageView) findViewById(R.id.image);
        boardbutton.setOnClickListener((OnClickListener) new boardClick());
        
        ImageView imageview1 = (ImageView) findViewById(R.id.image1);
        imageview1.setOnClickListener((OnClickListener) new guides1Click());
        ImageView imageview2 = (ImageView) findViewById(R.id.image2);
        imageview2.setOnClickListener((OnClickListener) new guides2Click());
        ImageView imageview3 = (ImageView) findViewById(R.id.image3);
        imageview3.setOnClickListener((OnClickListener) new guides3Click());
        
        //
        //initActionBar();
        //initDrawer();
        //initDrawerList();
        //mNavigationDrawerItemTitles= getResources().getStringArray(R.array.drawer_menu);
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mDrawerList = (ListView) findViewById(R.id.left_drawer);
    }
    
    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }
 
        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
 
        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;
 
            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }
 
        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
 
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
 
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
    
    private class guides1Click implements OnClickListener {
        public void onClick(View view) { 
        	try{
    		Intent intent = new Intent();
    		intent.setClass(MainActivity.this, guidesActivity.class);
    		intent.putExtra("id", "1");
    		startActivity(intent); 
    		MainActivity.this.finish();
        		
        	} catch(Exception e){
        		e.getMessage();
        	}
        }
    }
    
    private class guides2Click implements OnClickListener {
        public void onClick(View view) { 
        	try{
    		Intent intent = new Intent();
    		intent.setClass(MainActivity.this, guidesActivity.class);
    		intent.putExtra("id", "2");
    		startActivity(intent); 
    		MainActivity.this.finish();
        		
        	} catch(Exception e){
        		e.getMessage();
        	}
        }
    }
    
    private class guides3Click implements OnClickListener {
        public void onClick(View view) { 
        	try{
    		Intent intent = new Intent();
    		intent.setClass(MainActivity.this, guidesActivity.class);
    		intent.putExtra("id", "3");
    		startActivity(intent); 
    		MainActivity.this.finish();
        		
        	} catch(Exception e){
        		e.getMessage();
        	}
        }
    }
    
    private class boardClick implements OnClickListener {
        public void onClick(View view) { 
    		Intent intent = new Intent();
    		intent.setClass(MainActivity.this, boardActivity.class);
    		startActivity(intent); 
    		MainActivity.this.finish();
       
        }
    }
}
