package vn.tea.app.core.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CoreNotificatinonInput {
	private Long id;

	@NotBlank(message = "Vui lòng nhập nội dung")
	@Size(max = 250, message = "Nhập mã quá ${max} ký tự")
	private String noiDung;

	private Long donViId;

	private Long phongBanId;

	private String nguoiSuDungId;

	private Boolean isXemBaoCao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayXemThongBao;

	private String duongDanUrl;

	private String tieuDe;

	private String maModule;

}