package vn.tea.app.core.dto.validator;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.tea.app.core.dto.CoreModuleData;
import vn.tea.app.core.service.CoreModuleService;

@Component
public class CoreModuleValidator implements Validator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected CoreModuleService coreModuleService;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoreModuleData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CoreModuleData object = (CoreModuleData) target;
		if (checkMaExits(object.getId(), object.getMa())) {
			errors.rejectValue("ma", "error.ma", new Object[] { "ma" }, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		new ArrayList<>();
		if (Objects.nonNull(id) && id > 0) {
/*			listCoreModule = coreModuleService.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, Constants.CHUA_XOA);
			if (Objects.nonNull(listCoreModule) && !listCoreModule.isEmpty()) {
				check = false;
			} else {
				listCoreModule = coreModuleService.findByMaIgnoreCaseAndDaXoa(ma, Constants.CHUA_XOA);
				if (Objects.nonNull(listCoreModule) && !listCoreModule.isEmpty()) {
					check = true;
				}
			}
		} else {
			listCoreModule = coreModuleService.findByMaIgnoreCaseAndDaXoa(ma, Constants.CHUA_XOA);
			if (Objects.nonNull(listCoreModule) && !listCoreModule.isEmpty()) {
				check = true;
			}
*/		}
		return check;
	}
}
