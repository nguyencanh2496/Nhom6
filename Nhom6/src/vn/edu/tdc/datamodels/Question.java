package vn.edu.tdc.datamodels;

public class Question {

	private String  TenCV, TenCTy, TenTP, Luong,  Date, Link;

	public String getTenCV() {
		return TenCV;
	}

	public void setTenCV(String tenCV) {
		TenCV = tenCV;
	}

	public String getTenCTy() {
		return TenCTy;
	}

	public void setTenCTy(String tenCTy) {
		TenCTy = tenCTy;
	}

	public String getTenTP() {
		return TenTP;
	}

	public void setTenTP(String tenTP) {
		TenTP = tenTP;
	}

	public String getLuong() {
		return Luong;
	}

	public void setLuong(String luong) {
		Luong = luong;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	@Override
	public String toString() {
		return "Question [TenCV=" + TenCV + ", TenCTy=" + TenCTy + ", TenTP="
				+ TenTP + ", Luong=" + Luong + ", Date=" + Date + ", Link="
				+ Link + "]";
	}

	public String getStrLink() {
		// TODO Auto-generated method stub
		return null;
	}

	public Question(String tenCV, String tenCTy, String tenTP, String luong,
			String date, String link) {
		super();
		this.TenCV = tenCV;
		this.TenCTy = tenCTy;
		this.TenTP = tenTP;
		this.Luong = luong;
		this.Date = date;
		this.Link = link;
	}

	public Question get(int position) {
		// TODO Auto-generated method stub
		return null;
	}

}
