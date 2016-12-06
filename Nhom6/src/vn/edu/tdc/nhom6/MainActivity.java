package vn.edu.tdc.nhom6;

import android.app.Activity;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        
        final EditText edtTenvl = (EditText) findViewById(R.id.edtTenvl);
        final EditText edtTenTP = (EditText) findViewById(R.id.edtTenTP);
        Button btnTimKiem = (Button) findViewById(R.id.btnTimKiem);
        
        btnTimKiem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				/*String TenViecLam = (edtTenvl).getText().toString();
				String TenThanhPho = (edtTenTP).getText().toString();*/
				
				Intent intent = new Intent(MainActivity.this, NewDSActivity.class);
				startActivity(intent);
				finish();
			}
		});
    }
}
