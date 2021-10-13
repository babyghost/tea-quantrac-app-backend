package vn.tea.app.core.dto.bean;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DonVi {
	private Long id;
	private Long donViChaId;
	private Long capDonViId;
	private Long loaiDonViId;
	private String tenDonVi;
	private String tenVietTat;
	private String diaChi;
	private String soDienThoai;
	private String fax;
	private String email;
	private String nguoiCapNhat;
	private LocalDateTime ngayCapNhat;
	private String nguoiTao;
	private LocalDateTime ngayTao;
	private Boolean daXoa;
	private String appCode;
}
