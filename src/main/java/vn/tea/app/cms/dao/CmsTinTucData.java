package vn.tea.app.cms.dao;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CmsTinTucData{
	private Long id;
	private String anhThumbUrl;
	private String ten;
	private String moTa;
	private List<Long> listDanhMuc;
	private String noiDung;
	private Integer trangThai;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayXuatBan;
}