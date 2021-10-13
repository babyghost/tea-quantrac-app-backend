package vn.tea.app.core.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;

import vn.tea.app.core.dto.CoreModuleData;
import vn.tea.app.core.dto.validator.CoreModuleValidator;
import vn.tea.app.core.entity.CoreModule;
import vn.tea.app.core.service.CoreModuleService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
public class CoreModuleBusiness {

	@Autowired
	private CoreModuleService coreModuleService;
	@Autowired
	private CoreModuleValidator coreModuleValidator;

	public Page<CoreModule> findAll(int page, int size, String sortBy, String sortDir, String search,
			Boolean trangThai) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoreModule> pageCoreModule = coreModuleService.findAll(search, trangThai,
				PageRequest.of(page, size, direction, sortBy));

		return pageCoreModule;
	}

	public CoreModule findById(Long id) throws EntityNotFoundException {
		Optional<CoreModule> optional = coreModuleService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreModule.class, "id", String.valueOf(id));
		}
		CoreModule coreModule = optional.get();
		return coreModule;
	}

	public CoreModule create(CoreModuleData coreModuleData, BindingResult result)
			throws MethodArgumentNotValidException {
		coreModuleValidator.validate(coreModuleData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		CoreModule coreModule = new CoreModule();

		coreModule.setDaXoa(0);
		coreModule.setTen(coreModuleData.getTen());
		coreModule.setMa(coreModuleData.getMa());
		coreModule.setModuleChaId(coreModuleData.getModuleChaId());
		coreModule.setMoTa(coreModuleData.getMoTa());
		coreModule.setTrangThai(coreModuleData.getTrangThai());
		coreModule.setSapXep(coreModuleData.getSapXep());
		coreModule = coreModuleService.save(coreModule);

		return coreModule;
	}

	public CoreModule update(Long id, CoreModuleData coreModuleData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coreModuleValidator.validate(coreModuleData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		Optional<CoreModule> optional = coreModuleService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreModule.class, "id", String.valueOf(id));
		}
		CoreModule coreModule = optional.get();

		coreModule.setDaXoa(0);
		coreModule.setTen(coreModuleData.getTen());
		coreModule.setMa(coreModuleData.getMa());
		coreModule.setModuleChaId(coreModuleData.getModuleChaId());
		coreModule.setMoTa(coreModuleData.getMoTa());
		coreModule.setTrangThai(coreModuleData.getTrangThai());
		coreModule.setSapXep(coreModuleData.getSapXep());
		coreModule = coreModuleService.save(coreModule);

		return coreModule;
	}

	public CoreModule delete(@PathVariable("id") Long id) throws EntityNotFoundException {

		Optional<CoreModule> optional = coreModuleService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreModule.class, "id", String.valueOf(id));
		}
		CoreModule coreModule = optional.get();
		coreModule.setDaXoa(1);
		coreModuleService.save(coreModule);

		return coreModule;
	}

}
