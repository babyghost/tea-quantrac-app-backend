package vn.tea.app.core.dto.bean;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PhongBan {

	private Long id;
	private String ten;
	private String ma;
	private Long donViId;
	private Boolean trangThai;
	private String nguoiCapNhat;
	private LocalDateTime ngayCapNhat;
	private String nguoiTao;
	private LocalDateTime ngayTao;
	private Boolean daXoa;
	private String appCode;
}
