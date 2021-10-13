package vn.tea.app.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "core_chucnang")
@EntityListeners(AuditingEntityListener.class)
@Data
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "DanhSachCoreChucNang", classes = @ConstructorResult(targetClass = DanhSachCoreChucNang.class, columns = {
				@ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "nhomchucnangcha_id", type = Long.class),
				@ColumnResult(name = "ten", type = String.class), @ColumnResult(name = "ma", type = String.class),
				@ColumnResult(name = "icon", type = String.class), @ColumnResult(name = "path", type = String.class),
				@ColumnResult(name = "chucnang_id", type = Long.class),
				@ColumnResult(name = "chucnang_ten", type = String.class),
				@ColumnResult(name = "chucnang_ma", type = String.class),
				@ColumnResult(name = "chucnang_path", type = String.class),
				@ColumnResult(name = "level", type = Integer.class),
				@ColumnResult(name = "sapxep", type = Integer.class),
				@ColumnResult(name = "component", type = String.class),
				@ColumnResult(name = "hidden", type = Boolean.class),
				@ColumnResult(name = "alwaysshow", type = Boolean.class),
				@ColumnResult(name = "nocache", type = Boolean.class),
				@ColumnResult(name = "affix", type = Boolean.class),
				@ColumnResult(name = "breadcrumb", type = Boolean.class),
				@ColumnResult(name = "activemenu", type = String.class),
				@ColumnResult(name = "chucnang_sapxep", type = Integer.class),
				@ColumnResult(name = "path_info", type = String.class),
				@ColumnResult(name = "roles", type = String[].class) })) })

public class CoreChucNang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ten", length = 250, nullable = false)
	private String ten;

	@Column(name = "ma", length = 50, nullable = false, unique = true)
	private String ma;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "nhomchucnang_id", nullable = false)
	private CoreNhomChucNang coreNhomChucNang;

	@Column(name = "mota")
	private String moTa;

	@Column(name = "sapxep")
	private Integer sapXep;

	@Column(name = "nguoicapnhat", nullable = false)
	@LastModifiedBy
	private String nguoiCapNhat;

	@Column(name = "ngaycapnhat", nullable = false)
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;

	@Column(name = "nguoitao", nullable = false)
	@CreatedBy
	private String nguoiTao;

	@Column(name = "ngaytao", nullable = false)
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;

	@Column(name = "trangthai")
	private Integer trangThai;

	@Column(name = "daxoa", nullable = false)
	@JsonIgnore
	private Integer daXoa;

	@Column(name = "path", length = 500)
	private String path;

	@Column(name = "component", length = 1000)
	private String component;

	@Column(name = "hidden")
	private Integer hidden;

	@Column(name = "alwaysshow")
	private Integer alwaysShow;

	@Column(name = "title", length = 1000)
	private String title;

	@Column(name = "icon", length = 250)
	private String icon;

	@Column(name = "nocache")
	private Integer noCache;

	@Column(name = "affix")
	private Integer affix;

	@Column(name = "breadcrumb")
	private Integer breadcrumb;

	@Column(name = "activemenu", length = 500)
	private String activeMenu;

}
