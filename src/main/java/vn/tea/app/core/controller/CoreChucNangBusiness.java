package vn.tea.app.core.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.core.dto.CoreChucNangData;
import vn.tea.app.core.dto.MetaData;
import vn.tea.app.core.dto.RouterData;
import vn.tea.app.core.dto.validator.CoreChucNangValidator;
import vn.tea.app.core.entity.CoreChucNang;
import vn.tea.app.core.entity.CoreNhomChucNang;
import vn.tea.app.core.service.CoreChucNangService;
import vn.tea.app.core.service.CoreNhomChucNangService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
@Slf4j
public class CoreChucNangBusiness {

	@Autowired
	private CoreChucNangService coreChucNangService;
	@Autowired
	private CoreNhomChucNangService coreNhomChucNangService;
	@Autowired
	private CoreChucNangValidator coreChucNangValidator;

	public Page<CoreChucNang> findAll(int page, int size, String sortBy, String sortDir, String search,
			Long nhomChucNangId, Integer trangThai) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoreChucNang> pageCoreChucNang = coreChucNangService.findAll(search, nhomChucNangId, trangThai,
				PageRequest.of(page, size, direction, sortBy));

		return pageCoreChucNang;
	}

	public CoreChucNang findById(Long id) throws EntityNotFoundException {
		Optional<CoreChucNang> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreChucNang.class, "id", String.valueOf(id));
		}
		CoreChucNang coreChucNang = optional.get();
		return coreChucNang;
	}

	public CoreChucNang create(CoreChucNangData coreChucNangData, BindingResult result)
			throws MethodArgumentNotValidException {
		coreChucNangValidator.validate(coreChucNangData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		CoreChucNang coreChucNang = new CoreChucNang();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		if (Objects.nonNull(coreChucNangData.getNhomChucNangId())) {
			Optional<CoreNhomChucNang> optionalCoreNhomChucNang = coreNhomChucNangService
					.findById(coreChucNangData.getNhomChucNangId());
			if (optionalCoreNhomChucNang.isPresent()) {
				coreChucNang.setCoreNhomChucNang(optionalCoreNhomChucNang.get());
			}
		}
		coreChucNang.setMoTa(coreChucNangData.getMoTa());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		coreChucNang.setSapXep(coreChucNangData.getSapXep());
		coreChucNang = coreChucNangService.save(coreChucNang);

		return coreChucNang;
	}

	public CoreChucNang update(Long id, CoreChucNangData coreChucNangData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coreChucNangValidator.validate(coreChucNangData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		Optional<CoreChucNang> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreChucNang.class, "id", String.valueOf(id));
		}
		CoreChucNang coreChucNang = optional.get();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		if (Objects.nonNull(coreChucNangData.getNhomChucNangId())) {
			Optional<CoreNhomChucNang> optionalCoreNhomChucNang = coreNhomChucNangService
					.findById(coreChucNangData.getNhomChucNangId());
			if (optionalCoreNhomChucNang.isPresent()) {
				coreChucNang.setCoreNhomChucNang(optionalCoreNhomChucNang.get());
			}
		}
		coreChucNang.setMoTa(coreChucNangData.getMoTa());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		coreChucNang.setSapXep(coreChucNangData.getSapXep());
		coreChucNang = coreChucNangService.save(coreChucNang);

		return coreChucNang;
	}

	private static Integer checkRun = 0;

	@Async
	public Future<String> getRouterDatas(Object object) {
		String thongBao = "Bắt đầu get dữ liệu router";
		if (checkRun == 1) {
			thongBao = "Đang thực hiện get dữ liệu router";
			log.info("Bắt đầu get dữ liệu router");
			checkRun = 1;
			if (Objects.nonNull(object)) {
				ObjectMapper mapper = new ObjectMapper();
				List<RouterData> routerDatas = mapper.convertValue(object, new TypeReference<List<RouterData>>() {
				});
				coreChucNangService.setFixedDaXoa(1);
				coreNhomChucNangService.setFixedDaXoa(1);
				if (Objects.nonNull(routerDatas) && !routerDatas.isEmpty()) {
					int sapXep = 0;
					for (RouterData routerData : routerDatas) {
						sapXep++;
						saveRouterData(routerData, null, sapXep);
					}
				}

				log.info("Đã hoàn thành get dữ liệu router");
				thongBao = "Đã hoàn thành get dữ liệu router";
				checkRun = 0;
				return new AsyncResult<String>(thongBao);
			}
			checkRun = 0;
		}
		return null;
	}

	public void saveRouterData(RouterData routerData, CoreNhomChucNang coreNhomChucNangCha, int sapXep) {
		Integer isChucNang = 0;
		// log.info("RouterData:" + routerData);
		if (Objects.nonNull(routerData.getChildren()) && routerData.getChildren().isEmpty()) {
			isChucNang = 1;
		}
		log.info("Router:" + routerData.getName() + " - " + isChucNang);
		if (isChucNang == 1 && Objects.nonNull(coreNhomChucNangCha)) {
			List<CoreChucNang> coreChucNangs = coreChucNangService.findByMaIgnoreCase(routerData.getName());
			CoreChucNang coreChucNang = new CoreChucNang();
			if (Objects.nonNull(coreChucNangs) && !coreChucNangs.isEmpty()) {
				coreChucNang = coreChucNangs.get(0);
			}
			coreChucNang.setDaXoa(0);
			coreChucNang.setTrangThai(1);
			coreChucNang.setMa(routerData.getName());
			coreChucNang.setTen(routerData.getName());
			coreChucNang.setTitle(routerData.getName());
			coreChucNang.setCoreNhomChucNang(coreNhomChucNangCha);
			coreChucNang.setPath(routerData.getPath());
			coreChucNang.setSapXep(sapXep);
			MetaData metaData = new MetaData();
			if (Objects.nonNull(routerData.getMeta())) {
				metaData = routerData.getMeta();
				coreChucNang.setIcon(metaData.getIcon());
				coreChucNang.setTen(metaData.getTitle());
				coreChucNang.setTitle(metaData.getTitle());
				coreChucNang.setMoTa(metaData.getTitle());
				coreChucNang.setActiveMenu(metaData.getActiveMenu());
				coreChucNang.setAffix(metaData.getAffix());
				coreChucNang.setBreadcrumb(metaData.getBreadcrumb());
				coreChucNang.setComponent(routerData.getMeta().getComponent());
				coreChucNang.setHidden(routerData.getHidden());
			}
			coreChucNangService.save(coreChucNang);
		} else {
			List<CoreNhomChucNang> coreNhomChucNangs = coreNhomChucNangService.findByMaIgnoreCase(routerData.getName());
			CoreNhomChucNang coreNhomChucNang = new CoreNhomChucNang();
			if (Objects.nonNull(coreNhomChucNangs) && !coreNhomChucNangs.isEmpty()) {
				coreNhomChucNang = coreNhomChucNangs.get(0);
			}
			coreNhomChucNang.setDaXoa(0);
			coreNhomChucNang.setTrangThai(1);
			coreNhomChucNang.setMa(routerData.getName());
			coreNhomChucNang.setTen(routerData.getName());
			coreNhomChucNang.setPath(routerData.getPath());
			coreNhomChucNang.setNhomChucNangChaId(coreNhomChucNangCha != null ? coreNhomChucNangCha.getId() : null);
			coreNhomChucNang.setSapXep(sapXep);
			MetaData metaData = new MetaData();
			if (Objects.nonNull(routerData.getMeta())) {
				metaData = routerData.getMeta();
				coreNhomChucNang.setIcon(metaData.getIcon());
				coreNhomChucNang.setTen(metaData.getTitle());
				coreNhomChucNang.setMoTa(metaData.getTitle());
			}
			coreNhomChucNang = coreNhomChucNangService.save(coreNhomChucNang);
			int sapXepCon = 0;
			if (Objects.nonNull(routerData.getChildren()) && !routerData.getChildren().isEmpty()) {
				for (RouterData children : routerData.getChildren()) {
					sapXepCon++;
					saveRouterData(children, coreNhomChucNang, sapXepCon);
				}
			}
		}

	}

	public CoreChucNang delete(Long id) throws EntityNotFoundException {

		Optional<CoreChucNang> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreChucNang.class, "id", String.valueOf(id));
		}
		CoreChucNang coreChucNang = optional.get();
		coreChucNang.setDaXoa(1);
		coreChucNangService.save(coreChucNang);

		return coreChucNang;
	}

}
