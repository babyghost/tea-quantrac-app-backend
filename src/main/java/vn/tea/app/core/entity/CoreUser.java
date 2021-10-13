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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "core_user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoreUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username", length = 250)
	private String userName;

	@Column(name = "email", length = 250, nullable = false, unique = true)
	private String email;

	@Column(name = "phone", length = 50)
	private String phone;

	@Column(name = "hoten")
	private String hoTen;

	@Column(name = "canbo_id")
	private Long canBoId;

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

	@Column(name = "daxoa", nullable = false)
	@JsonIgnore
	private Integer daXoa;

//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "core_user2role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	@WhereJoinTable(clause = "daxoa=0")
//	private List<CoreRole> coreRoles = new ArrayList<>();
	
	@Column(name = "password")
	private String password;
}
