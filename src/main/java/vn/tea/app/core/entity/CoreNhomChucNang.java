package vn.tea.app.core.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "core_nhomchucnang")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoreNhomChucNang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ten", length = 250, nullable = false)
	private String ten;

	@Column(name = "ma", length = 50, nullable = false, unique = true)
	private String ma;

	@Column(name = "nhomchucnangcha_id")
	private Long nhomChucNangChaId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nhomchucnangcha_id", referencedColumnName = "id", insertable = false, updatable = false)
	@Where(clause = "daxoa = 0")
	private CoreNhomChucNang parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Where(clause = "daxoa = 0")
	@OrderBy(value = "sapxep ASC")
	private List<CoreNhomChucNang> children;

	@Column(name = "mota")
	private String moTa;

	@Column(name = "icon")
	private String icon;

	@Column(name = "path")
	private String path;

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

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nhomchucnang_id")
	@Where(clause = "daxoa = 0 AND trangthai = 1")
	@OrderBy(value = "sapxep ASC")
	private List<CoreChucNang> coreChucNangs;

}
