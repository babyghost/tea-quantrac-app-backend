package vn.tea.app.cms.dao;

import lombok.Data;

@Data
public class CmsDanhMucData{
	private Long id;
	private String ten;
	private String ma;
	private Integer trangThai;
	private String catCode;
}