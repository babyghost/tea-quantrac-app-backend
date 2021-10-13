package vn.tea.app.cms.dao;

import lombok.Data;

@Data
public class CmsThongTinLienHeData{
	private Long id;
	private String ten;
	private String ma;
	private Integer trangThai;
	private String email;
	private String soDienThoai;
	private String noiDung;
}