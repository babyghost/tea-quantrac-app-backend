package vn.tea.app.core.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.tea.app.core.entity.CoreLog;

@Data
public class CoreLogOutput {
	private Long id;
	private String noiDung;
	private String noiDungDonViThucHien;
	private Long objectId;
	private Integer phienBan;
	private String nguoiCapNhat;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;
	private String nguoiTao;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;
	private Integer type;
	private Integer isPrimary;
	private String actionName;
	private String actionDonViThucHien;
	private String noiDungThayDoi;
	
	public CoreLogOutput() {}
	public CoreLogOutput(CoreLog coreLog) {
		this.id = coreLog.getId();
		this.noiDung = coreLog.getNoidung();
		this.noiDungDonViThucHien = coreLog.getNoiDungDonViThucHien();
		this.objectId = coreLog.getObjectId();
		this.phienBan = coreLog.getPhienBan();
		this.nguoiCapNhat = coreLog.getNguoiCapNhat();
		this.ngayCapNhat = coreLog.getNgayCapNhat();
		this.nguoiTao = coreLog.getNguoiTao();
		this.ngayTao = coreLog.getNgayTao();
		this.type = coreLog.getType();
		this.isPrimary = coreLog.getIsPrimary();
		this.actionName = coreLog.getActionName();
		this.actionDonViThucHien = coreLog.getActionDonViThucHien();
	}
}
