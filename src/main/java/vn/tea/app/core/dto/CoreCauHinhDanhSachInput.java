package vn.tea.app.core.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CoreCauHinhDanhSachInput {
	private Long id;
	
	@NotBlank(message="Vui lòng nhập mã danh sách")
	@Size(max=250, message="Nhập mã quá ${max} ký tự")
	private String madanhsach;
	
	@NotBlank(message="Vui lòng nhập tên cột")
	@Size(max=250, message="Nhập tên quá ${max} ký tự")
	private String tenCot;
	
	@NotBlank(message="Vui lòng nhập tên giá trị")
	@Size(max=250, message="Nhập tên quá ${max} ký tự")
	private String tenGiaTri;
	
	@NotNull(message = "Vui lòng nhập độ rộng cột")
	private Integer doRongCot;
	
	@NotNull(message = "Vui lòng nhập sắp xếp")
	private Integer sapXep;
	
	@NotNull(message = "Vui lòng chọn cấu hình danh sách id")
	private Long cauHinhDanhSachId;
	
	@NotNull(message = "Vui lòng chọn trạng thái")
	private Boolean trangThai;
	
}