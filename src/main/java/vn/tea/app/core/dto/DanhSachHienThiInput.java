package vn.tea.app.core.dto;

import java.util.List;

import lombok.Data;
import vn.tea.app.core.entity.DanhSachHienThi;

@Data
public class DanhSachHienThiInput {
	private String maDanhSach;
	private String nguoiSuDung;
	private List<DanhSachHienThi> listDanhSachHienThi;

}