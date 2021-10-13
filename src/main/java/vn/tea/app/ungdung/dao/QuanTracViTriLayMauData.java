package vn.tea.app.ungdung.dao;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class QuanTracViTriLayMauData {
//	private Long id;
	private String ten;
	private String viTriLayMau;
	private Long viTriQuanTracId;
//	private Long phanTichId;
	private Long donViHanhChinhId;
	private Long tacDongId;
	private Double chiTieuTsp;
	private Double chiTieuPm10;
	private Double chiTieuO3;
	private Double chiTieuNo2;
	private Double chiTieuSo2;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayLayMau;
	private String quanHuyen;	
	private String tacDong;
	private String lat;
	private String lng;
}