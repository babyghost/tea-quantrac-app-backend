package vn.tea.app.core.dto.validator;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.tea.app.core.dto.CoreRoleData;
import vn.tea.app.core.service.CoreRoleService;

@Component
public class CoreRoleValidator implements Validator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected CoreRoleService coreRoleService;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoreRoleData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CoreRoleData object = (CoreRoleData) target;

		if (checkMaExits(object.getId(), object.getMa())) {
			errors.rejectValue("ma", "error.ma", new Object[] { "ma" }, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		new ArrayList<>();
		if (Objects.nonNull(id) && id > 0) {
//			listCoreRole = coreRoleService.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, Constants.CHUA_XOA);
//			if (Objects.nonNull(listCoreRole) && !listCoreRole.isEmpty()) {
//				check = false;
//			} else {
//				listCoreRole = coreRoleService.findByMaIgnoreCaseAndDaXoa(ma, Constants.CHUA_XOA);
//				if (Objects.nonNull(listCoreRole) && !listCoreRole.isEmpty()) {
//					check = true;
//				}
//			}
//		} else {
//			listCoreRole = coreRoleService.findByMaIgnoreCaseAndDaXoa(ma, Constants.CHUA_XOA);
//			if (Objects.nonNull(listCoreRole) && !listCoreRole.isEmpty()) {
//				check = true;
//			}
		}
		return check;
	}
}
