package vn.tea.app.ungdung.dao;

import java.util.List;

import lombok.Data;
import vn.tea.app.ungdung.entity.QuanTracDanhMuc;

@Data
public class QuanTracPhanTichMoiTruongData{
	private Long id;
	private String ten;
	private Long heSoTrungBinhId;
	private Integer trangThai;
//	private Long tacDongId;
	private List<QuanTracViTriLayMauData> listViTriLayMau;
	private List<QuanTracDanhMuc> listDmViTri;
}