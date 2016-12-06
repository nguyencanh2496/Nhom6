package vn.edu.tdc.nhom6;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class NewDSActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.listview_ds_layout);
        
        
        ImageButton imgBack = (ImageButton) findViewById(R.id.imgBack);
        ImageButton imgHome = (ImageButton) findViewById(R.id.imgHome);
        
        imgBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewDSActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
        imgHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewDSActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
    }
}
