package vn.tea.app.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "core_message")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoreMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "noidung", length = 250, nullable = false)
	private String noiDung;

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaytao")
	private LocalDateTime ngayTao;

	@Column(name = "donvi_id")
	private Long donViId;

	@Column(name = "phongban_id")
	private Long phongBanId;

	@Column(name = "nguoidung", length = 100, nullable = false)
	private String nguoiDungId;

	// @Column(name = "type")
	// private int type;

	@Column(name = "is_xembaocao")
	private Integer isXemBaoCao = 1;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngayxemthongbao")
	private LocalDateTime ngayXemThongBao;

	@Column(name = "duongdanurl", length = 255, nullable = false)
	private String duongDanUrl;

	@Column(name = "tieude", length = 255, nullable = false)
	private String tieuDe;

	@Column(name = "mamodule", length = 255, nullable = false)
	private String maModule;

	@Column(name = "appcode", length = 255)
	private String appCode;

}
