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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import lombok.Data;

@Data
@Entity
@Table(name = "quantrac_phantichmoitruong")
public class QuanTracPhanTichMoiTruong implements Serializable {
	private static final long serialVersionUID = 7482139714356696412L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ten")
	private String ten;
	
	@Column(name = "hesotrungbinh_id")
	private Long heSoTrungBinhId;

	@Column(name = "daxoa", nullable = false)
	private Integer daXoa = 0;

	@Column(name = "ngaytao")
	@CreationTimestamp
	private LocalDateTime ngayTao;

	
	@Column(name = "nguoitao", nullable = false)
	@CreatedBy
	private String nguoiTao;

	@Column(name = "ngaysua")
	@UpdateTimestamp
	private LocalDateTime ngaySua;

	@Column(name = "nguoisua")
	@LastModifiedBy
	private String nguoiSua;
	
	@Column(name = "trangthai")
	private Integer trangThai;
	
	@Column(name = "loai")
	private String loai;
	
	@Column(name = "nam")
	private Integer nam;
	
	@Column(name = "thang")
	private Integer thang;
	
	
}