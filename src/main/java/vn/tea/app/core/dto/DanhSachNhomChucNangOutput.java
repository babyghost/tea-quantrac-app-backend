package vn.tea.app.core.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DanhSachNhomChucNangOutput {

	private Long id;

	private String nhomChucNangTen;

	private String nhomChucNangMa;
	
	private String icon;
	
	private String path;
	
	private List<DanhSachNhomChucNangOutput> danhSachNhomChucNangCons = new ArrayList<>();

	private List<DanhSachChucNangOutput> danhSachChucNangs = new ArrayList<>();
	
	private List<String> roles = new ArrayList<>();

}
