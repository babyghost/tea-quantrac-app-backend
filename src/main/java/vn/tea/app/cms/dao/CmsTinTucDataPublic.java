package vn.tea.app.cms.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CmsTinTucDataPublic{
	private Long id;
	private String anhThumbUrl;
	private String ten;
	private String moTa;
	private List<HashMap<String, String>> listDanhMuc;
	private String noiDung;
	private Integer trangThai;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayXuatBan;
}