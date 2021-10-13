package vn.tea.app.core.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DanhSachCoreChucNang {

	private Long id;

	private Long nhomChucNangChaId;

	private String ten;

	private String ma;

	private String icon;

	private String path;

	private Long chucNangId;

	private String chucNangTen;

	private String chucNangMa;
	
	private String chucNangPath;

	private Integer level;

	private Integer sapXep;

	private String component;

	private Integer hidden;

	private Integer alwaysShow;

	private Integer noCache;

	private Integer affix;

	private Integer breadcrumb;

	private String activeMenu;

	private Integer chucNangSapXep;

	private String pathInfo;

	private String[] roles;

}
