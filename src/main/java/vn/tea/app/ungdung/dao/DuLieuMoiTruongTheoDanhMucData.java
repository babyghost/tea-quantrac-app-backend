package vn.tea.app.ungdung.dao;

import java.util.List;

import lombok.Data;

@Data
public class DuLieuMoiTruongTheoDanhMucData {
	private Long dmViTriId;
	private String dmViTriString;
	private String lat;
	private String lng;
	private List<DuLieuMoiTruongForMapData> listDuLieuForViTri;
}