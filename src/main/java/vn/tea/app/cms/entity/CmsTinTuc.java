package vn.tea.app.cms.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "cms_tintuc")
public class CmsTinTuc implements Serializable {
	private static final long serialVersionUID = 7482139714356696412L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ten")
	private String ten;
	
	@Column(name = "anh_thumb_id")
	private Long anhThumbId;

	@Column(name = "anh_thumb_url")
	private String anhThumbUrl;

	@Column(name = "ngayxuatban")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayXuatBan;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tinTucId")
	private List<CmsTinTuc2DanhMuc> listCmsTinTuc2DanhMuc;
	
	@Column(name = "mota")
	private String moTa;
	
	@Column(name = "noidung")
	private String noiDung;
	
	@Column(name = "trangthai")
	private Integer trangThai;

	@Column(name = "daxoa", nullable = false)
	private Integer daXoa = 0;

	@Column(name = "ngaytao")
	@CreationTimestamp
	private LocalDateTime ngayTao;

	@Column(name = "nguoitao", nullable = false)
	private String nguoiTao;

	@Column(name = "ngaysua")
	@UpdateTimestamp
	private LocalDateTime ngaySua;

	@Column(name = "nguoisua")
	private String nguoiSua;
}