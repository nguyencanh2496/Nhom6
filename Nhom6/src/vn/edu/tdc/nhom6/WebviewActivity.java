package vn.edu.tdc.nhom6;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import vn.edu.tdc.adapter.MyAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class WebviewActivity extends Activity {

	ImageButton imgHome;
	ImageButton imgBack;
	WebView webview = null;
	MyAdapter adapter;
	String link = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail_layout);

		imgHome = (ImageButton) findViewById(R.id.imgHome);
		imgBack = (ImageButton) findViewById(R.id.imgBack);

		Button btnLuuCV = (Button) findViewById(R.id.btnLuuCV);
		
		webview = (WebView) findViewById(R.id.webview);
		
		imgHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WebviewActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		imgBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) { 
				onBackPressed();
			}
		});

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getBundleExtra("dataLink");
			if (bundle != null) {
				link = bundle.getString("link");
				// Toast.makeText(getApplicationContext(), link,
				// Toast.LENGTH_LONG).show();
			}
		}

		if (savedInstanceState != null)
	    {
	        ((WebView)findViewById(R.id.webview)).restoreState(savedInstanceState);
	    }
		 else{
			 webview=(WebView)findViewById(R.id.webview);
			 webview.loadUrl(link);
			 webview.getSettings().getJavaScriptEnabled();
			 webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			 
			 webview.setWebViewClient(new WebViewClient()
			 {
				 @Override
		            public boolean shouldOverrideUrlLoading(WebView view,
		                    String url) {
		                view.loadUrl(url);
		                return true;
		            }
		        });
		 }		
	}
	
	protected void onSaveInstanceState(Bundle outState) {
		webview.saveState(outState);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
	    	webview.goBack();
	        return true;
	    }
	    
	    return super.onKeyDown(keyCode, event);
	}


}
