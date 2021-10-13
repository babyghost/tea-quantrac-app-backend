package vn.tea.app.core.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import vn.tea.app.core.entity.CoreRole;
import vn.tea.app.core.entity.CoreUser;

@Data
public class CoreUserData {
	private Long id;

	@Size(max = 250, message = "Nhập username quá {max} ký tự")
	private String userName;
	
//	@NotBlank(message = "Vui lòng nhập mật khẩu")
	private String password;

	@NotBlank(message = "Vui lòng nhập email")
	@Size(max = 250, message = "Nhập email quá {max} ký tự")
	private String email;

	@Size(max = 50, message = "Nhập email quá {max} ký tự")
	private String phone;

	@Size(max = 250, message = "Nhập họ tên quá {max} ký tự")
	private String hoTen;

	private Long canBoId;
	
	private List<Long> roles;
	private List<String> rolesName;
	
	private List<CoreRole> objectCoreRoles;
	
	public CoreUserData() {}
	public CoreUserData(CoreUser user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.hoTen = user.getHoTen();
	}
}
