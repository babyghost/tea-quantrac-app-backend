package vn.tea.app.core.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DanhSachChucNangOutput {

	private Long id;

	private String chucNangTen;

	private String chucNangMa;

	private Integer sapXep;

	private String path;

	private String component;

	private Integer hidden;

	private Integer alwaysShow;

	private String title;

	private String icon;

	private Integer noCache;

	private Integer affix;

	private Integer breadcrumb;

	private String activeMenu;
	
	private List<String> roles = new ArrayList<>();

}
