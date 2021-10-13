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
@Table(name = "quantrac_hesotrungbinh")
public class QuanTracHeSoTrungBinh implements Serializable {
	private static final long serialVersionUID = 7482139714356696412L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ten")
	private String ten;
	
	@Column(name = "chitieu_tsp")
	private Double chiTieuTsp;
	@Column(name = "chitieu_pm10")
	private Double chiTieuPm10;
	@Column(name = "chitieu_o3")
	private Double chiTieuO3;
	@Column(name = "chitieu_no2")
	private Double chiTieuNo2;
	@Column(name = "chitieu_so2")
	private Double chiTieuSo2;

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