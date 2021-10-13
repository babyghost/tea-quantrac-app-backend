package vn.tea.app.core.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CoreNhomChucNangData {

	private Long id;

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 250, message = "Nhập tên quá {max} ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 50, message = "Nhập mã quá {max} ký tự")
	private String ma;

	private String moTa;

	private Long nhomChucNangChaId;

	private String nhomChucNangChaTen;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;

	@NotNull(message = "Vui lòng nhập sắp xếp")
	@Min(value = 0, message = "Nhập sắp xếp không nhỏ hơn {value}")
	@Max(value = 32767, message = "Nhập sắp xếp không lớn hơn {value}")
	private Integer sapXep;

	private String icon;

	private String path;

	private List<CoreChucNangData> coreChucNangDatas = new ArrayList<>();

}
