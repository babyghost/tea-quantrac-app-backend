package vn.tea.app.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;

import vn.tea.app.core.dto.CoreChucNangData;
import vn.tea.app.core.dto.CoreNhomChucNangData;
import vn.tea.app.core.dto.validator.CoreNhomChucNangValidator;
import vn.tea.app.core.entity.CoreChucNang;
import vn.tea.app.core.entity.CoreNhomChucNang;
import vn.tea.app.core.service.CoreChucNangService;
import vn.tea.app.core.service.CoreNhomChucNangService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
public class CoreNhomChucNangBusiness {

	@Autowired
	private CoreNhomChucNangService coreNhomChucNangService;
	@Autowired
	private CoreNhomChucNangValidator coreNhomChucNangValidator;
	@Autowired
	private CoreChucNangService coreChucNangService;

	public Page<CoreNhomChucNang> findAll(int page, int size, String sortBy, String sortDir, String search,
			Integer trangThai, Long id, Long nhomChucNangChaId) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoreNhomChucNang> pageCoreNhomChucNang = coreNhomChucNangService.findAll(search, trangThai, id,
				nhomChucNangChaId, PageRequest.of(page, size, direction, sortBy));

		return pageCoreNhomChucNang;
	}

	public CoreNhomChucNangData findById(Long id) throws EntityNotFoundException {
		Optional<CoreNhomChucNang> optional = coreNhomChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreNhomChucNang.class, "id", String.valueOf(id));
		}
		CoreNhomChucNang coreNhomChucNang = optional.get();
		CoreNhomChucNangData coreNhomChucNangData = new CoreNhomChucNangData();
		coreNhomChucNangData.setId(coreNhomChucNang.getId());
		coreNhomChucNangData.setTen(coreNhomChucNang.getTen());
		coreNhomChucNangData.setMa(coreNhomChucNang.getMa());
		coreNhomChucNangData.setMoTa(coreNhomChucNang.getMoTa());
		coreNhomChucNangData.setSapXep(coreNhomChucNang.getSapXep());
		coreNhomChucNangData.setTrangThai(coreNhomChucNang.getTrangThai());
		coreNhomChucNangData.setIcon(coreNhomChucNang.getIcon());
		coreNhomChucNangData.setPath(coreNhomChucNang.getPath());
		coreNhomChucNangData.setNhomChucNangChaId(
				coreNhomChucNang.getParent() != null ? coreNhomChucNang.getParent().getId() : null);
		coreNhomChucNangData.setNhomChucNangChaTen(
				coreNhomChucNang.getParent() != null ? coreNhomChucNang.getParent().getTen() : null);

		List<CoreChucNangData> coreChucNangDatas = new ArrayList<>();
		List<CoreChucNang> coreChucNangs = coreChucNangService
				.findByCoreNhomChucNangIdAndTrangThaiAndDaXoa(coreNhomChucNang.getId(), 1, 0);
		if (Objects.nonNull(coreChucNangs) && !coreChucNangs.isEmpty()) {
			for (CoreChucNang coreChucNang : coreChucNangs) {
				CoreChucNangData coreChucNangData = new CoreChucNangData();
				coreChucNangData.setId(coreChucNang.getId());
				coreChucNangData.setActiveMenu(coreChucNang.getActiveMenu());
				coreChucNangData.setAffix(coreChucNang.getAffix());
				coreChucNangData.setAlwaysShow(coreChucNang.getAlwaysShow());
				coreChucNangData.setBreadcrumb(coreChucNang.getBreadcrumb());
				coreChucNangData.setComponent(coreChucNang.getComponent());
				coreChucNangData.setHidden(coreChucNang.getHidden());
				coreChucNangData.setIcon(coreChucNang.getIcon());
				coreChucNangData.setMa(coreChucNang.getMa());
				coreChucNangData.setMoTa(coreChucNang.getMoTa());
				coreChucNangData.setNoCache(coreChucNang.getNoCache());
				coreChucNangData.setPath(coreChucNang.getPath());
				coreChucNangData.setSapXep(coreChucNang.getSapXep());
				coreChucNangData.setTen(coreChucNang.getTen());
				coreChucNangData.setTitle(coreChucNang.getTitle());
				coreChucNangData.setTrangThai(coreChucNang.getTrangThai());
				coreChucNangDatas.add(coreChucNangData);
			}
		}
		coreNhomChucNangData.setCoreChucNangDatas(coreChucNangDatas);
		return coreNhomChucNangData;
	}

	public CoreNhomChucNang create(CoreNhomChucNangData coreNhomChucNangData, BindingResult result)
			throws MethodArgumentNotValidException {
		coreNhomChucNangValidator.validate(coreNhomChucNangData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		CoreNhomChucNang coreNhomChucNang = new CoreNhomChucNang();

		coreNhomChucNang.setDaXoa(0);
		coreNhomChucNang.setTen(coreNhomChucNangData.getTen());
		coreNhomChucNang.setMa(coreNhomChucNangData.getMa());
		if (Objects.nonNull(coreNhomChucNangData.getNhomChucNangChaId())) {
			Optional<CoreNhomChucNang> optionalCoreNhomChucNang = coreNhomChucNangService
					.findById(coreNhomChucNangData.getNhomChucNangChaId());
			if (optionalCoreNhomChucNang.isPresent()) {
				coreNhomChucNang.setNhomChucNangChaId(coreNhomChucNangData.getNhomChucNangChaId());
			}
		} else {
			coreNhomChucNang.setNhomChucNangChaId(null);
		}
		coreNhomChucNang.setMoTa(coreNhomChucNangData.getMoTa());
		coreNhomChucNang.setTrangThai(coreNhomChucNangData.getTrangThai());
		coreNhomChucNang.setSapXep(coreNhomChucNangData.getSapXep());
		coreNhomChucNang.setIcon(coreNhomChucNangData.getIcon());
		coreNhomChucNang.setPath(coreNhomChucNangData.getPath());
		coreNhomChucNang = coreNhomChucNangService.save(coreNhomChucNang);

		coreChucNangService.setFixedDaXoaForNhomChucNangId(1, coreNhomChucNang.getId());
		List<CoreChucNangData> coreChucNangDatas = coreNhomChucNangData.getCoreChucNangDatas();
		if (Objects.nonNull(coreChucNangDatas) && !coreChucNangDatas.isEmpty()) {
			for (CoreChucNangData coreChucNangData : coreChucNangDatas) {
				CoreChucNang coreChucNang = new CoreChucNang();
				if (Objects.nonNull(coreChucNangData.getId())) {
					Optional<CoreChucNang> optionalCoreChucNang = coreChucNangService
							.findById(coreChucNangData.getId());
					if (optionalCoreChucNang.isPresent()) {
						coreChucNang = optionalCoreChucNang.get();
					}
				}
				coreChucNang.setDaXoa(0);
				coreChucNang.setCoreNhomChucNang(coreNhomChucNang);
				coreChucNang.setActiveMenu(coreChucNangData.getActiveMenu());
				coreChucNang.setAffix(coreChucNangData.getAffix());
				coreChucNang.setAlwaysShow(coreChucNangData.getAlwaysShow());
				coreChucNang.setBreadcrumb(coreChucNangData.getBreadcrumb());
				coreChucNang.setComponent(coreChucNangData.getComponent());
				coreChucNang.setHidden(coreChucNangData.getHidden());
				coreChucNang.setIcon(coreChucNangData.getIcon());
				coreChucNang.setMa(coreChucNangData.getMa());
				coreChucNang.setMoTa(coreChucNangData.getMoTa());
				coreChucNang.setNoCache(coreChucNangData.getNoCache());
				coreChucNang.setPath(coreChucNangData.getPath());
				coreChucNang.setSapXep(coreChucNangData.getSapXep());
				coreChucNang.setTen(coreChucNangData.getTen());
				coreChucNang.setTitle(coreChucNangData.getTitle());
				coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
				coreChucNangService.save(coreChucNang);
			}
		}

		return coreNhomChucNang;
	}

	public CoreNhomChucNang update(Long id, CoreNhomChucNangData coreNhomChucNangData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coreNhomChucNangValidator.validate(coreNhomChucNangData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		Optional<CoreNhomChucNang> optional = coreNhomChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreNhomChucNang.class, "id", String.valueOf(id));
		}
		CoreNhomChucNang coreNhomChucNang = optional.get();

		coreNhomChucNang.setDaXoa(0);
		coreNhomChucNang.setTen(coreNhomChucNangData.getTen());
		coreNhomChucNang.setMa(coreNhomChucNangData.getMa());
		if (Objects.nonNull(coreNhomChucNangData.getNhomChucNangChaId())) {
			Optional<CoreNhomChucNang> optionalCoreNhomChucNang = coreNhomChucNangService
					.findById(coreNhomChucNangData.getNhomChucNangChaId());
			if (optionalCoreNhomChucNang.isPresent()) {
				coreNhomChucNang.setNhomChucNangChaId(coreNhomChucNangData.getNhomChucNangChaId());
			}
		} else {
			coreNhomChucNang.setNhomChucNangChaId(null);
		}
		coreNhomChucNang.setMoTa(coreNhomChucNangData.getMoTa());
		coreNhomChucNang.setTrangThai(coreNhomChucNangData.getTrangThai());
		coreNhomChucNang.setSapXep(coreNhomChucNangData.getSapXep());
		coreNhomChucNang.setIcon(coreNhomChucNangData.getIcon());
		coreNhomChucNang.setPath(coreNhomChucNangData.getPath());
		coreNhomChucNang = coreNhomChucNangService.save(coreNhomChucNang);

		coreChucNangService.setFixedDaXoaForNhomChucNangId(1, coreNhomChucNang.getId());
		List<CoreChucNangData> coreChucNangDatas = coreNhomChucNangData.getCoreChucNangDatas();
		if (Objects.nonNull(coreChucNangDatas) && !coreChucNangDatas.isEmpty()) {
			for (CoreChucNangData coreChucNangData : coreChucNangDatas) {
				CoreChucNang coreChucNang = new CoreChucNang();
				if (Objects.nonNull(coreChucNangData.getId())) {
					Optional<CoreChucNang> optionalCoreChucNang = coreChucNangService
							.findById(coreChucNangData.getId());
					if (optionalCoreChucNang.isPresent()) {
						coreChucNang = optionalCoreChucNang.get();
					}
				}
				coreChucNang.setDaXoa(0);
				coreChucNang.setCoreNhomChucNang(coreNhomChucNang);
				coreChucNang.setActiveMenu(coreChucNangData.getActiveMenu());
				coreChucNang.setAffix(coreChucNangData.getAffix());
				coreChucNang.setAlwaysShow(coreChucNangData.getAlwaysShow());
				coreChucNang.setBreadcrumb(coreChucNangData.getBreadcrumb());
				coreChucNang.setComponent(coreChucNangData.getComponent());
				coreChucNang.setHidden(coreChucNangData.getHidden());
				coreChucNang.setIcon(coreChucNangData.getIcon());
				coreChucNang.setMa(coreChucNangData.getMa());
				coreChucNang.setMoTa(coreChucNangData.getMoTa());
				coreChucNang.setNoCache(coreChucNangData.getNoCache());
				coreChucNang.setPath(coreChucNangData.getPath());
				coreChucNang.setSapXep(coreChucNangData.getSapXep());
				coreChucNang.setTen(coreChucNangData.getTen());
				coreChucNang.setTitle(coreChucNangData.getTitle());
				coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
				coreChucNangService.save(coreChucNang);
			}
		}

		return coreNhomChucNang;
	}

	public List<CoreNhomChucNang> checkMaExit(String ma) {
		return coreNhomChucNangService.findByMaIgnoreCase(ma);
	}

	public CoreNhomChucNang delete(@PathVariable("id") Long id) throws EntityNotFoundException {

		Optional<CoreNhomChucNang> optional = coreNhomChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreNhomChucNang.class, "id", String.valueOf(id));
		}
		CoreNhomChucNang coreNhomChucNang = optional.get();
		coreNhomChucNang.setDaXoa(1);
		coreNhomChucNangService.save(coreNhomChucNang);

		return coreNhomChucNang;
	}

}
