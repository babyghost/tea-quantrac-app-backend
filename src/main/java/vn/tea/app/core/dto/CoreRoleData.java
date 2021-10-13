package vn.tea.app.core.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CoreRoleData {
	private Long id;

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 250, message = "Nhập tên quá {max} ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 50, message = "Nhập mã quá {max} ký tự")
	private String ma;

	private String moTa;
	private String content;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;

	private Integer isDefault;

	private List<MenuData> menu = new ArrayList<>();
}
