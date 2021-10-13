package vn.tea.app.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "core_cauhinhdanhsach")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoreCauHinhDanhSach {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "madanhsach", length = 250, nullable = false)
	private String maDanhSach;

	@Column(name = "tencot", length = 20, nullable = false)
	private String tenCot;

	@Column(name = "tengiatri")
	private String tenGiaiTri;

	@Column(name = "dorongcot")
	private int doRongCot;

	@Column(name = "sapxep")
	private int sapXep;

	@Column(name = "trangthai")
	private Boolean trangThai;

	@Column(name = "appcode", length = 255)
	private String appCode;

	@Column(name = "nguoicapnhat", length = 150, nullable = false)
	@LastModifiedBy
	private String nguoiCapNhat;

	@Column(name = "ngaycapnhat", nullable = false)
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;

	@Column(name = "nguoitao", length = 150, nullable = false)
	@CreatedBy
	private String nguoiTao;

	@Column(name = "ngaytao", nullable = false)
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;

	@Column(name = "daxoa", nullable = false)
	private Integer daXoa;

}
