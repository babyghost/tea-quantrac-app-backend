package vn.tea.app.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;
@Entity
@Table(name = "core_category")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoreCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name", length = 225)
	private String name;
	@Column(name = "code", length = 225)
	private String code;
	@Column(name = "app_code", length = 225)
	private String appCode;
	@Column(name = "parent_id")
	private Long parentId;
	@Column(name = "value_extend1", length = 225)
	private String valueExtend1;
	@Column(name = "value_extend2", length = 225)
	private String valueExtend2;
	@Column(name = "value_extend3", length = 225)
	private String valueExtend3;
	@Column(name = "daxoa")
	private Integer daXoa;
}
