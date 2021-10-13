package vn.tea.app.core.entity;

import java.util.Date;

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

import lombok.Data;

@Entity
@Table(name = "core_attachment")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoreAttachment{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	@Column(nullable = false)
	private String folder;
	
	@Column(nullable = false)
	private String appCode;
	
	private Integer month;
	
	private Integer year;	
	
	private String link;
	
	private Long size;
	
	@Column(nullable = false)
	private String mime;
	
	@Column(name="filename", nullable = false)
	private String fileName;
	
	@Column(name = "daxoa",nullable = false)
	private Integer daXoa = 0;
	
	@Column(name = "object_id")
	private Long objectId;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
//	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate; 
	
    @Column(name = "modified_date")
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name = "created_by",nullable = false, updatable = false)
    @CreatedBy
    private String createdBy; 
    
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
