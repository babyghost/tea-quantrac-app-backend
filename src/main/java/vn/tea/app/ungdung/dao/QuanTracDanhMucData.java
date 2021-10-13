package vn.tea.app.ungdung.dao;

import lombok.Data;

@Data
public class QuanTracDanhMucData{
	private String ten;
	private String ma;
	private int trangThai;
	private String catCode;
	private Long diaDiemHanhChinhId;
	private String lat;
	private String lng;
}