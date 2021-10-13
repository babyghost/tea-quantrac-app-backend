package vn.tea.app.core.dto.validator;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.tea.app.core.dto.CoreNhomChucNangData;
import vn.tea.app.core.service.CoreNhomChucNangService;

@Component
public class CoreNhomChucNangValidator implements Validator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected CoreNhomChucNangService coreNhomChucNangService;

	@Override
	public boolean supports(Class<?> clazz) {
		return CoreNhomChucNangData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CoreNhomChucNangData object = (CoreNhomChucNangData) target;

		if (checkMaExits(object.getId(), object.getMa())) {
			errors.rejectValue("ma", "error.ma", new Object[] { "ma" }, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		new ArrayList<>();
		if (Objects.nonNull(id) && id > 0) {
//			listCoreNhomChucNang = coreNhomChucNangService.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, Constants.CHUA_XOA);
//			if (Objects.nonNull(listCoreNhomChucNang) && !listCoreNhomChucNang.isEmpty()) {
//				check = false;
//			} else {
//				listCoreNhomChucNang = coreNhomChucNangService.findByMaIgnoreCaseAndDaXoa(ma, Constants.CHUA_XOA);
//				if (Objects.nonNull(listCoreNhomChucNang) && !listCoreNhomChucNang.isEmpty()) {
//					check = true;
//				}
//			}
//		} else {
//			listCoreNhomChucNang = coreNhomChucNangService.findByMaIgnoreCaseAndDaXoa(ma, Constants.CHUA_XOA);
//			if (Objects.nonNull(listCoreNhomChucNang) && !listCoreNhomChucNang.isEmpty()) {
//				check = true;
//			}
		}
		return check;
	}
}
