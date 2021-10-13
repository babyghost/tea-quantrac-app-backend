package vn.tea.app.core.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CoreModuleData {
	private Long id;

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 250, message = "Nhập tên quá {max} ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 50, message = "Nhập mã quá {max} ký tự")
	private String ma;
	
	private Long moduleChaId;

	private String moTa;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;

	@NotNull(message = "Vui lòng nhập sắp xếp")
	@Min(value = 0, message = "Nhập sắp xếp không nhỏ hơn {value}")
	@Max(value = 32767, message = "Nhập sắp xếp không lớn hơn {value}")
	private Integer sapXep;
}
