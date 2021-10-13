package vn.tea.app.ungdung.entity;

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
@Table(name = "quantrac_danhmuc")
public class QuanTracDanhMuc implements Serializable {
	private static final long serialVersionUID = 7482139714356696412L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ten")
	private String ten;
	@Column(name = "trangthai")
	private Integer trangThai;
	@Column(name = "ma", nullable = false, length = 500)
	private String ma;

	@Column(name = "cat_code", nullable = false, length = 255)
	private String catCode;

	@Column(name = "cha_id")
	private Long chaId;
	
	@Column(name = "lat")
	private String lat;
	
	@Column(name = "lng")
	private String lng;
	
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