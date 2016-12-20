package vn.tdc.edu.dba;

import java.util.ArrayList;
import vn.edu.tdc.datamodels.Question;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabaseAccess extends SQLiteOpenHelper {

	static int DB_VERSION = 1;
	static String DB_NAME = "vieclam.db";
	static SQLiteDatabase db = null;
	Context context;

	public MyDatabaseAccess(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sqlQuery = "CREATE TABLE vieclam (TenCV TEXT PRIMARY KEY, TenCTy TEXT, DiaChi TEXT, ThoiGian TEXT, Luong TEXT, image TEXT)";
		db.execSQL(sqlQuery);
	}

	boolean isDatabaseExist() {
		return (db != null);
	}
	
	void createOrOpenDatabase() {
		db = getWritableDatabase();
	}
	
	long writeQuestion(Question question){
		
		ContentValues values = new ContentValues();
		values.put("TenCV", question.getTenCV());
		values.put("TenCTy", question.getTenCTy());
		values.put("Date", question.getDate());
		
		long ok = db.insert("question", null, values);
		if (ok == -1) {
			Toast.makeText(
					context,
					"The question: " + question.getTenCV()
							+ " is exist on the Database", Toast.LENGTH_LONG)
					.show();
		}
		return ok;
	}
	public void writeAQuestion(Question question) {
		createOrOpenDatabase();
		writeQuestion(question);
		this.close();
	}
	
	public void writeQuestions(ArrayList<Question> question){
		createOrOpenDatabase();
		boolean ok = true;
		
		for (Question aQuestion : question) {
			long position = writeQuestion(aQuestion);
			if (position == -1)
				ok = false;
		}
		if (ok) {
			Toast.makeText(context,
					"All of question are saved on the Database",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(
					context,
					"Some of question are saved on the Database, some are not!",
					Toast.LENGTH_LONG).show();
		}	
	}
	
	public long updateQuestion(Question old, Question newvalue) {
		createOrOpenDatabase();
		ContentValues values = new ContentValues();
		values.put("TenCV", newvalue.getTenCV());
		values.put("TenCTy", newvalue.getTenCTy());
		values.put("Date", newvalue.getDate());
		
		long ok = db.update("question", values, "tencv = ?",
				new String[] { old.getTenCV() });
		
		if (ok != -1) {
			Toast.makeText(context, "The question is updated on the Database",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context,
					"The question is NOT updated on the Database",
					Toast.LENGTH_LONG).show();
		}
		this.close();
		return ok;
	
	}
	
	public void deleteQuestion(String TenCV) {
		createOrOpenDatabase();
		db.delete("question", "tenCV = ?", new String[] { TenCV });
		this.close();
	}
	
	public Question findStudent(Question question) {
		Question mQuestion = new Question(null, null, null, null, null, null);
		createOrOpenDatabase();

		String query = "Select * from student where name =" + "\""
				+ question.getTenCV() + "\"";
		Cursor cur = db.rawQuery(query, null);
		if (cur.moveToFirst()) {
			mQuestion.setTenCV(cur.getString(cur.getColumnIndex("TenCV")));
			mQuestion.setTenCTy(cur.getString(cur.getColumnIndex("TenCTy")));
			mQuestion.setDate(cur.getString(cur.getColumnIndex("Date")));
		}

		this.close();
		return mQuestion;
	}
	
	public void readQuestion(ArrayList<Question> question) {
		createOrOpenDatabase();
		String query = "Select * from question";
		Cursor cur = db.rawQuery(query, null);
		if (cur.moveToFirst()) {
			do {
				Question mQuestion = new Question(query, query, query, query, query, query);
				mQuestion.setTenCV(cur.getString(cur.getColumnIndex("TenCV")));
				mQuestion.setTenCTy(cur.getString(cur.getColumnIndex("TenCTy")));
				mQuestion.setDate(cur.getString(cur.getColumnIndex("Date")));
				
				question.add(mQuestion);
			} while (cur.moveToNext());
		}

		this.close();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}


}
