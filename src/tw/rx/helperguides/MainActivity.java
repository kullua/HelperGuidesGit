package tw.rx.helperguides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set text
        Button boardbutton = (Button) findViewById(R.id.boardbutton);
        boardbutton.setOnClickListener((OnClickListener) new HelloButton());
        
        
    }
    
    private class HelloButton implements OnClickListener {
        public void onClick(View view) { 
    		Intent intent = new Intent();
    		intent.setClass(MainActivity.this, boardActivity.class);
    		startActivity(intent); 
    		MainActivity.this.finish();
       
        }
    }
}
