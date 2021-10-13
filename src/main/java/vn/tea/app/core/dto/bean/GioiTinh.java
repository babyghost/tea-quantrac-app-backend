package vn.tea.app.core.dto.bean;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GioiTinh {
	private Long id;
	private String ten;
	private String ma;
	private Integer sapXep;
	private Boolean trangThai;
	private String nguoiCapNhat;
	private LocalDateTime ngayCapNhat;
	private String nguoiTao;
	private LocalDateTime ngayTao;
	private Boolean daXoa;
	private String appCode;
}
