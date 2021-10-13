package vn.tea.app.core.dto.validator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.tea.app.core.dto.CoreChucNangData;
import vn.tea.app.core.service.CoreChucNangService;

@Component
public class CoreChucNangValidator implements Validator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected CoreChucNangService coreChucNangService;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoreChucNangData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CoreChucNangData object = (CoreChucNangData) target;

		if (checkMaExits(object.getId(), object.getMa())) {
			errors.rejectValue("ma", "error.ma", new Object[] { "ma" }, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		// List<CoreChucNang> listCoreChucNang = new ArrayList<>();
		if (Objects.nonNull(id) && id > 0) {
			/*
			 * listCoreChucNang = coreChucNangService.findByIdAndMaIgnoreCaseAndDaXoa(id,
			 * ma, Constants.CHUA_XOA); if (Objects.nonNull(listCoreChucNang) &&
			 * !listCoreChucNang.isEmpty()) { check = false; } else { listCoreChucNang =
			 * coreChucNangService.findByMaIgnoreCaseAndDaXoa(ma, Constants.CHUA_XOA); if
			 * (Objects.nonNull(listCoreChucNang) && !listCoreChucNang.isEmpty()) { check =
			 * true; } } } else { listCoreChucNang =
			 * coreChucNangService.findByMaIgnoreCaseAndDaXoa(ma, Constants.CHUA_XOA); if
			 * (Objects.nonNull(listCoreChucNang) && !listCoreChucNang.isEmpty()) { check =
			 * true; }
			 */
		}
		return check;
	}
}
