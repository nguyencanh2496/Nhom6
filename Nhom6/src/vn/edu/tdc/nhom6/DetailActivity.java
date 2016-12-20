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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class DetailActivity extends Activity {

	ImageButton imgHome;
	ImageButton imgBack;
	WebView webview = null;
	MyAdapter adapter;

	String link = "";
	String detail = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail_layout);

		imgHome = (ImageButton) findViewById(R.id.imgHome);
		imgBack = (ImageButton) findViewById(R.id.imgBack);

		webview = (WebView) findViewById(R.id.webview);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getBundleExtra("dataLink");
			if (bundle != null) {
				link = bundle.getString("link");
				// Toast.makeText(getApplicationContext(), link,
				// Toast.LENGTH_LONG).show();
			}
		}

		Button btnLuuCV = (Button) findViewById(R.id.btnLuuCV);

		WebSettings webSettings = webview.getSettings();
		webSettings.setSupportZoom(true);

		btnLuuCV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(DetailActivity.this, "Save success!",
						Toast.LENGTH_LONG).show();

			}
		});

		imgHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DetailActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		imgBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) { // TODO Auto-generated method stub
				onBackPressed();
			}
		});
		new GetData().execute();
	}

	public class GetData extends AsyncTask<Void, Void, Void> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(DetailActivity.this);
			dialog.setMessage("Đang lấy nội dung chi tiết. Vui lòng chờ trong giây lát...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			try {
				Document doccument = Jsoup.connect(link).get();

				Elements pubTenVL = doccument.select("div.top-job-info h1");
				Elements pubTenCty = doccument.select("div.tit_company");
				Elements pubNgay = doccument.select("div.datepost");
				Elements pubTT = doccument
						.select("div.box2Detail h4.TitleJobNew");
				Elements pubNoiLV = doccument.select("li.bgLine1 p.fl_left");
				Elements pubCapBac = doccument.select("li.bgLine1 p.fl_right");

				Elements pubMoTa = doccument
						.select("div.MarBot20 h4.TitleJobNew");
				Elements pubMoTact = doccument.select("div.content_fck ul li");

				detail += "<h3  style = \" color: red \">" + pubTenVL.text()
						+ "</h3>";
				detail += "<h3>" + pubTenCty.text() + "</h3>";
				detail += "<font size=\" 1.2em \" style = \" color: #005500 \"><em>"
						+ pubNgay.text() + "</em></font>";
				detail += "<h4 style = \" color: #f79d25\">" + pubTT.text()
						+ "</h4>";
				detail += "<p>" + pubNoiLV.text() + "</p>";
				detail += "<p>" + pubCapBac.text() + "</p>";

				detail += "<h4 style = \" color: #f79d25\">" + pubMoTa.text()
						+ "</h4>";
				detail += "<p>" + pubMoTact.text() + "</p>" + "\n";

				// Log.d("da vao toi day", pubMoTact + "" );

				// Log.d("da vao toi day", pubTenVL + "" );

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		private void getContent(Document doccument) {
			// TODO Auto-generated method stub

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			// Toast.makeText(ChitietActivity.this,
			// "Đã lấy nội dung chi tiết. Mời xem kết quả!",
			// Toast.LENGTH_LONG).show();
			webview.loadDataWithBaseURL("", detail, "text/html", "UTF-8", "");
			dialog.dismiss();
		}
	}

}
