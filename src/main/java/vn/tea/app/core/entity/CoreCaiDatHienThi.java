package vn.tea.app.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "core_caidathienthi")
@EntityListeners(AuditingEntityListener.class)
@Data
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "DanhSachHienThi", classes = @ConstructorResult(targetClass = DanhSachHienThi.class, columns = {
				@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "madanhsach", type = String.class),
				@ColumnResult(name = "tencot", type = String.class),
				@ColumnResult(name = "tengiatri", type = String.class),
				@ColumnResult(name = "dorongcot", type = Integer.class),
				@ColumnResult(name = "sapxep", type = Integer.class),
				@ColumnResult(name = "is_hienthi", type = Boolean.class),
				@ColumnResult(name = "caidathienthi_id", type = Long.class),
				@ColumnResult(name = "nguoisudung", type = String.class) })) })
public class CoreCaiDatHienThi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nguoisudung", length = 250, nullable = false)
	private String nguoiSuDung;

	@Column(name = "madanhsach", length = 20, nullable = false)
	private String maDanhSach;

	@Column(name = "cauhinhdanhsach_id")
	private Long cauHinhDanhSachId;

	@Column(name = "is_hienthi")
	private Integer isHienThi;

	@Column(name = "appcode", nullable = false)
	private String appCode;

	@Column(name = "nguoicapnhat", length = 150, nullable = false)
	@LastModifiedBy
	private String nguoiCapNhat;

	@Column(name = "ngaycapnhat", nullable = false)
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;

	@Column(name = "nguoitao", length = 150, nullable = false)
	@CreatedBy
	private String nguoiTao;

	@Column(name = "ngaytao", nullable = false)
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;

	@Column(name = "daxoa", nullable = false)
	private Integer daXoa;

}
