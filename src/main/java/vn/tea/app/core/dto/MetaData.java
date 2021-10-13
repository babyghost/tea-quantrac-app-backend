package vn.tea.app.core.dto;

import lombok.Data;

@Data
public class MetaData {

	private String title;

	private String icon;

	private Integer noCache;

	private Integer affix;

	private Integer breadcrumb;

	private String activeMenu;

	private String component;

	private String[] roles;

}
