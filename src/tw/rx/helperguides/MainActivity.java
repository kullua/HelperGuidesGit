package tw.rx.helperguides;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

/*	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;*/
	public static final String URL = "http://trx.loveu.tw/images/Top.jpg";
	ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隱藏標題
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.activity_main);
        

        imageView = (ImageView) findViewById(R.id.imageBanner);
        //GetXMLTask task = new GetXMLTask();
        //task.execute(new String[] { URL });
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        ImageView boardbutton = (ImageView) findViewById(R.id.image);
        boardbutton.setOnClickListener((OnClickListener) new boardClick());
        
        ImageView imageview1 = (ImageView) findViewById(R.id.image1);
        imageview1.setOnClickListener((OnClickListener) new guides1Click());
        ImageView imageview2 = (ImageView) findViewById(R.id.image2);
        imageview2.setOnClickListener((OnClickListener) new guides2Click());
        ImageView imageview3 = (ImageView) findViewById(R.id.image3);
        imageview3.setOnClickListener((OnClickListener) new guides3Click());
        
        ImageView imageview4 = (ImageView) findViewById(R.id.image4);
        imageview4.setOnClickListener((OnClickListener) new fbClick());
        
        try {
            SearchView searchView = (SearchView) findViewById(R.id.sv_search);

            searchView.setSubmitButtonEnabled(false);
            searchView.setOnQueryTextListener(new OnQueryTextListener() {

				@Override
				public boolean onQueryTextChange(String arg0) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean onQueryTextSubmit(String arg0) {
					// TODO Auto-generated method stub
					
					searchlist(arg0.toString());
					
					//Toast.makeText(MainActivity.this, "您選擇的是:"+arg0.toString(), Toast.LENGTH_SHORT).show();
					return true;
				}

            });


        } catch (Exception ex) {
            ex.getMessage();
        }
             
    }
    
    public void searchlist(String text){
    	try {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, searchActivity.class);
		intent.putExtra("searchText", text);
		startActivity(intent); 
		MainActivity.this.finish();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
    	if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // Show home screen when pressing "back" button,
            //  so that this app won't be closed accidentally
            Intent intentHome = new Intent(Intent.ACTION_MAIN);
            intentHome.addCategory(Intent.CATEGORY_HOME);
            intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentHome);
            
            return true;
        }
    	
    	return super.onKeyDown(keyCode, event);

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
    
    private class fbClick implements OnClickListener {
        public void onClick(View view) { 
        	String facebookUrl = "https://www.facebook.com/rxloveutw";
        	String facebookPackageName = "com.facebook.katana";
        	Context context = null;
        	try {
        		int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));

            } catch (Exception e) {
            	launchFacebook();
            }
       
        }
    }
    
    
    public final void launchFacebook() {
        final String urlFb = "https://www.facebook.com/rxloveutw";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlFb));

        // If a Facebook app is installed, use it. Otherwise, launch
        // a browser
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list =
            packageManager.queryIntentActivities(intent,
            PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() == 0) {
            final String urlBrowser = "https://www.facebook.com/rxloveutw";
            intent.setData(Uri.parse(urlBrowser));
        }

        startActivity(intent);
    }
}
