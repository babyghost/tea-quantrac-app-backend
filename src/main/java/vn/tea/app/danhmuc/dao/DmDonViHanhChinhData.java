package vn.tea.app.danhmuc.dao;

import lombok.Data;

@Data
public class DmDonViHanhChinhData{
	private Long id;
	private String ten;
	private String ma;
	private Long idCha;
	private String tenDonViCha;
	private String catCode;
}