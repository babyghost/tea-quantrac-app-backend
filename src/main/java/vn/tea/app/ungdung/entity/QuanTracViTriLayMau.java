package vn.tea.app.ungdung.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "quantrac_vitrilaymau")
public class QuanTracViTriLayMau implements Serializable {
	private static final long serialVersionUID = 7482139714356696412L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ten")
	private String ten;
	
	@Column(name = "vitrilaymau")
	private String viTriLayMau;
	@Column(name = "dm_vitriquantrac_id")
	private Long viTriQuanTracId;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "dm_vitriquantrac_id", insertable=false, updatable=false)	
	@Where(clause = "daxoa = 0")
	private QuanTracDanhMuc viTriMauObject;
	
	
	@Column(name = "tacdong_id")
	private Long tacDongId;
	@Column(name = "phantich_id")
	private Long phanTichId;
	@Column(name = "donvihanhchinh_id")
	private Long donViHanhChinhId;
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

	@Column(name = "ngaylaymau")
	private LocalDate ngayLayMau;
	
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
	
	
	@Column(name = "thanglaymau", nullable = false)
	private Integer thangLayMau;
	
	@Column(name = "namlaymau", nullable = false)
	private Integer namLayMau;
	
}