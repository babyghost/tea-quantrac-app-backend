package vn.tea.app.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "core_attachment_temp")
@Data
public class CoreAttachmentTemp{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
