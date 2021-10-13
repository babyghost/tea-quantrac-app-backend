package vn.tea.app.ungdung.dao;

import lombok.Data;

@Data
public class DuLieuMoiTruongForMapData {
	private String tenViTri;
	private String ten; // ten vị 
	private String tenTacDong;
	private String tenBaoCao;
	private String lat;
	private String lng;
	private String ketQuaAQI;
	private String color;
	private String trangThaiMoiTruong;
	private String thangNam;
	private String thongBao;
	private String desThongBao;
	private Double chiTieuTsp;
	private Double chiTieuPm10;
	private Double chiTieuO3;
	private Double chiTieuNo2;
	private Double chiTieuSo2;
}