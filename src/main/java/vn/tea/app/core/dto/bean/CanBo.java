package vn.tea.app.core.dto.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CanBo {
	private Long id;
	private String hoTen;
	private PhongBan phongBan;
	private DonVi donVi;
	private GioiTinh gioiTinh;
	private ChucVu chucVu;
	private String email;
	private LocalDate ngaySinh;
	private String diaChi;
	private String dienThoai;
	private String fax;
	private String cmndSo;
	private LocalDate cmndNgayCap;
	private String cmndNoiCap;
	private String nguoiCapNhat;
	private LocalDateTime ngayCapNhat;
	private String nguoiTao;
	private LocalDateTime ngayTao;
	private Boolean daXoa;
	private String appCode;
}