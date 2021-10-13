package vn.tea.app.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "core_role2chucnang")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoreRole2ChucNang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "chucnang_id")
	private Long chucNangId;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "daxoa", nullable = false)
	@JsonIgnore
	private Integer daXoa;

}
