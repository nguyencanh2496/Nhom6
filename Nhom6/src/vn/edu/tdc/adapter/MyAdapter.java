package vn.edu.tdc.adapter;

import java.util.ArrayList;
import java.util.List;
import vn.edu.tdc.datamodels.Question;
import vn.edu.tdc.nhom6.R;
import android.app.Activity;
import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Question> {

	Activity context = null;
	int itemlayout;
	ArrayList<Question> question = null;

	public MyAdapter(Activity context, int resource, ArrayList<Question> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		itemlayout = resource;
		question = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();

		convertView = inflater.inflate(itemlayout, null);

		TextView txtTenCV = (TextView) convertView
				.findViewById(R.id.txtTenViecLam);
		TextView txtTenCTy = (TextView) convertView
				.findViewById(R.id.txtTenCty);
		TextView txtTenTP = (TextView) convertView
				.findViewById(R.id.txtDiaChi);
		TextView txtLuong = (TextView) convertView
				.findViewById(R.id.txtLuong);
		TextView txtDate = (TextView) convertView
				.findViewById(R.id.txtThoigian);

		Question jobs = question.get(position);
		
		if (jobs != null) {
			
			txtTenCV.setText(jobs.getTenCV());
			txtTenCTy.setText(jobs.getTenCTy());
			txtTenTP.setText(jobs.getTenTP());
			txtLuong.setText(jobs.getLuong());
			txtDate.setText(jobs.getDate());
		}
		return convertView;
	}

}
