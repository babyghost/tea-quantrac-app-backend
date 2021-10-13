package vn.tea.app.cms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "cms_tintuc2danhmuc")
public class CmsTinTuc2DanhMuc implements Serializable {
	private static final long serialVersionUID = 7482139714356696412L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "tintuc_id")
	private Long tinTucId;

	@Column(name = "danhmuc_id")
	private Long danhMucId;

	@Column(name = "danhmuc_string")
	private String danhMucString;
	
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