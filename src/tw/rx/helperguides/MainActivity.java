package tw.rx.helperguides;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

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
        
        //��l�ư�������
        initMenu();
    }
    
    private void initMenu() {
    	SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        //�]�mĲ�N�̹����Ҧ�
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindWidth(getResources().getDimensionPixelSize(R.dimen.shadow_width));
        menu.setShadowDrawable(R.drawable.shadow);
        //�]�m�ưʵ����Ϫ��e��
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //�]�m���J���X�ĪG����
        menu.setFadeDegree(0.35f);
        
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //�����]�m����
        menu.setMenu(R.layout.menu_list);
        
        TextView text = (TextView) findViewById(R.id.textView1);
        text.setText("This is menu.");
        
        //ListView
        final ListView list = (ListView) findViewById(R.id.menu_listview);
        
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

        SimpleAdapter mSchedule = new SimpleAdapter(this,getData(),R.layout.menu_row,
        		new String[] {"Itemid","ItemTitle", "ItemText"},
        		new int[] {R.id.Itemid,R.id.ItemTitle,R.id.ItemText});
        list.setAdapter(mSchedule);
	      //ItemClick
	        list.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					//Toast.makeText(boardActivity.this, "�I����"+ (arg2+1) +"�Ӷ���", Toast.LENGTH_LONG).show();
	
					try{
					HashMap<String,String> map= (HashMap<String,String>) list.getItemAtPosition(arg2);
					String strid=map.get("Itemid"); 
					if(strid.equals("1")) {
						Intent intent = new Intent(MainActivity.this,MainActivity.class);
						startActivity(intent);
						MainActivity.this.finish();
					}
					if(strid.equals("2")) {
						Intent intent = new Intent(MainActivity.this,boardActivity.class);
						startActivity(intent);
						MainActivity.this.finish();
					}
					if(strid.equals("3")) {
						Intent intent = new Intent(MainActivity.this,guidesActivity.class);
						intent.putExtra("id", "1");
						startActivity(intent);
						MainActivity.this.finish();
					}
					if(strid.equals("4")) {
						Intent intent = new Intent(MainActivity.this,guidesActivity.class);
						intent.putExtra("id", "2");
						startActivity(intent);
						MainActivity.this.finish();
					}
					if(strid.equals("5")) {
						Intent intent = new Intent(MainActivity.this,guidesActivity.class);
						intent.putExtra("id", "3");
						startActivity(intent);
						MainActivity.this.finish();
					}
					
					
			        
			        
			        
					} catch(Exception e){
						e.getMessage();
					}
				}
	        });
        
        
        
        menu.setOnOpenedListener(new OnOpenedListener() {
            @Override
            public void onOpened() {
            }
        });

        menu.setOnClosedListener(new OnClosedListener() {
            @Override
            public void onClosed() {
            }
        });
         
        //menu.showMenu(true);
        //menu.toggle();
    }
    
    private List<Map<String, Object>> getData() {  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("Itemid", "1");
        map.put("ItemTitle", "����");
        list.add(map);
        
        map = new HashMap<String, Object>();  
        map.put("Itemid", "2");
        map.put("ItemTitle", "���i");
        list.add(map);
        
        map = new HashMap<String, Object>();  
        map.put("Itemid", "3");
        map.put("ItemTitle", "�s��о�");
        list.add(map);
        
        map = new HashMap<String, Object>();  
        map.put("Itemid", "4");
        map.put("ItemTitle", "�C����");
        list.add(map);
        
        map = new HashMap<String, Object>();  
        map.put("Itemid", "5");
        map.put("ItemTitle", "�d�P�ޯ�");
        list.add(map);
        
        map = new HashMap<String, Object>();  
        map.put("Itemid", "6");
        map.put("ItemTitle", "����ڭ�");
        list.add(map);
          
        return list;  
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
