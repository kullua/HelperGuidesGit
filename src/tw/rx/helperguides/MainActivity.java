package tw.rx.helperguides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set text
        ImageView boardbutton = (ImageView) findViewById(R.id.image);
        boardbutton.setOnClickListener((OnClickListener) new boardClick());
        
        ImageView imageview1 = (ImageView) findViewById(R.id.image1);
        imageview1.setOnClickListener((OnClickListener) new guides1Click());
        ImageView imageview2 = (ImageView) findViewById(R.id.image2);
        imageview2.setOnClickListener((OnClickListener) new guides2Click());
        ImageView imageview3 = (ImageView) findViewById(R.id.image3);
        imageview3.setOnClickListener((OnClickListener) new guides3Click());
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
