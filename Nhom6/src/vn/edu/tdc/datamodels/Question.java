package vn.edu.tdc.datamodels;

public class Question {

	String TenCV, TenTP, TenCTy, DiaChi, ThoiGian, Luong, image, MoTaCV, YeuCauCV;

	public String getMoTaCV() {
		return MoTaCV;
	}

	public void setMoTaCV(String moTaCV) {
		MoTaCV = moTaCV;
	}

	public String getTenCV() {
		return TenCV;
	}

	public void setTenCV(String tenCV) {
		TenCV = tenCV;
	}

	public String getTenTP() {
		return TenTP;
	}

	public void setTenTP(String tenTP) {
		TenTP = tenTP;
	}

	public String getTenCTy() {
		return TenCTy;
	}

	public void setTenCTy(String tenCTy) {
		TenCTy = tenCTy;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getThoiGian() {
		return ThoiGian;
	}

	public void setThoiGian(String thoiGian) {
		ThoiGian = thoiGian;
	}

	public String getLuong() {
		return Luong;
	}

	public void setLuong(String luong) {
		Luong = luong;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Question [TenCV=" + TenCV + ", TenTP=" + TenTP + ", TenCTy="
				+ TenCTy + ", DiaChi=" + DiaChi + ", ThoiGian=" + ThoiGian
				+ ", Luong=" + Luong + ", image=" + image + ", MoTaCV="
				+ MoTaCV + ", YeuCauCV=" + YeuCauCV + "]";
	}

}
