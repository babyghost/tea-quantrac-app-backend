package vn.tea.app.core.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RouterData {

	private String path;

	private Object component;

	private String redirect;

	private String name;

	private MetaData meta;

	private List<RouterData> children = new ArrayList<RouterData>();

	private Integer hidden;

	private Integer alwaysShow;

}
