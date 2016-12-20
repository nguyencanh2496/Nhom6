package vn.edu.tdc.nhom6;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import vn.edu.tdc.adapter.MyAdapter;
import vn.edu.tdc.datamodels.Link;
import vn.edu.tdc.datamodels.Question;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewDSActivity extends Activity {

	String url = "";
	static ArrayList<Question> questions = new ArrayList<Question>();
	ListView lvDanhSach;
	MyAdapter adapter;
	static ArrayList<Link> arrlink = new ArrayList<Link>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.listview_ds_layout);
        
        
        ImageButton imgBack = (ImageButton) findViewById(R.id.imgBack);
        ImageButton imgHome = (ImageButton) findViewById(R.id.imgHome);
        
        getAndUpdateLink();
		// Log.d("update link", "link");

		if (url != null) {
			new _JSOUP().execute();
		}
        
        
		lvDanhSach = (ListView) findViewById(R.id.lvdanhsach);

		imgHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ListViewDSActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		imgBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) { // TODO Auto-generated method stub
				Intent intent = new Intent(ListViewDSActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
    }
    void getAndUpdateLink() {
		Intent intent = getIntent();
		// Log.d("intent", "intent " + intent);
		if (intent != null) {
			Bundle bundle = intent.getBundleExtra("data");
			Log.d("aaa", bundle + "");
			if (bundle != null) {
				Link link = new Link(bundle.getString("link"));
				Log.d("du lieu da lay", link + "");
				url = link + "";
			} else {
				arrlink.clear();
			}
		}
	}

	private class _JSOUP extends AsyncTask<Void, Integer, ArrayList<Question>> {
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			dialog = new ProgressDialog(ListViewDSActivity.this);
			dialog.setMessage("loading...");
			dialog.show();

		}

		@Override
		protected ArrayList<Question> doInBackground(Void... arg0) {
			questions = new ArrayList<Question>();

			// TODO Auto-generated method stub
			try {
				// Log.d("da vao", "da vao toi day");
				Document doccument = Jsoup.connect(url).get();

				Elements job = doccument.select("h3.job");
				Elements namecom = doccument.select("p.namecom");
				Elements location = doccument.select("p.location");
				Elements salary = doccument.select("p.salary");
				Elements dateposted = doccument.select("div.dateposted");

				Elements url2 = doccument.select("h3.job");

				for (int i = 0; i < url2.size() && i < job.size()
						&& i < namecom.size() && i < location.size()
						&& i < salary.size() && i < dateposted.size(); i++) {

					Question question = new Question("\n" + job.get(i).text(),
							"\n" + namecom.get(i).text(), "\n"
									+ location.get(i).text(), "\n"
									+ salary.get(i).text(), "\n"
									+ dateposted.get(i).text(), "\n"
									+ url2.get(i).select("a").attr("href"));
					questions.add(question);
				}
			} catch (Exception e) {
				// TODO: handle exception
				// Log.d("da vao toi day 1", "da vao toi day 1");
			}
			return questions;
			// TODO Auto-generated method stub
		}

		@Override
		protected void onPostExecute(ArrayList<Question> result) {

			super.onPostExecute(result);

			lvDanhSach = (ListView) findViewById(R.id.lvdanhsach);
			adapter = new MyAdapter(ListViewDSActivity.this,
					R.layout.lisviewitem_ds, result);
			lvDanhSach.setAdapter(adapter);

			lvDanhSach.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(ListViewDSActivity.this,
							WebviewActivity.class);
					String link = questions.get(position).getLink();
					Bundle bundle = new Bundle();
					bundle.putString("link", link);
					intent.putExtra("dataLink", bundle);
					startActivity(intent);
				}
			});
			dialog.dismiss();
		}

	}
}
