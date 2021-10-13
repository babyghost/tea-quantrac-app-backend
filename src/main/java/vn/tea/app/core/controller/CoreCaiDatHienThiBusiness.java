package vn.tea.app.core.controller;
//package vn.tea.core.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import lombok.extern.slf4j.Slf4j;
//import vn.tea.core.data.CoreCaiDatHienThiInput;
//import vn.tea.core.data.DanhSachHienThiInput;
//import vn.tea.core.model.CoreCaiDatHienThi;
//import vn.tea.core.model.DanhSachHienThi;
//import vn.tea.core.service.CoreCaiDatHienThiService;
//import vn.tea.exceptions.EntityNotFoundException;
//
//@Service
//@Slf4j
//public class CoreCaiDatHienThiBusiness {
//
//	@Autowired
//	private CoreCaiDatHienThiService caiDatHienThiService;
//
//	public Page<CoreCaiDatHienThi> findAll(int page, int size, String sortBy, String sortDir, String ma) {
//		Direction direction;
//		if (sortDir.equals("ASC")) {
//			direction = Direction.ASC;
//		} else {
//			direction = Direction.DESC;
//		}
//		Page<CoreCaiDatHienThi> pageList = caiDatHienThiService.findAll(ma,
//				PageRequest.of(page, size, direction, sortBy));
//		return pageList;
//	}
//
//	public CoreCaiDatHienThi findById(@PathVariable("id") Long id) throws EntityNotFoundException {
//		Optional<CoreCaiDatHienThi> optional = caiDatHienThiService.findById(id);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(CoreCaiDatHienThi.class, "id", String.valueOf(id));
//		}
//		return optional.get();
//	}
//
//	public CoreCaiDatHienThi create(String appCode, CoreCaiDatHienThiInput modelInput) {
//		CoreCaiDatHienThi caiDatHienThi = new CoreCaiDatHienThi();
//		String email = uaaUserService.getUsername();
//		caiDatHienThi.setNguoiSuDung(email);
//		caiDatHienThi.setMaDanhSach(modelInput.getMadanhsach());
//		caiDatHienThi.setCauHinhDanhSachId(modelInput.getCauHinhDanhSachId());
//		caiDatHienThi.setIsHienThi(modelInput.getIsHienThi());
//		caiDatHienThi.setAppCode(appCode);
//		caiDatHienThi = caiDatHienThiService.save(caiDatHienThi);
//		return caiDatHienThi;
//	}
//
//	public CoreCaiDatHienThi update(String appCode, Long id, CoreCaiDatHienThiInput modelInput)
//			throws EntityNotFoundException {
//		Optional<CoreCaiDatHienThi> optional = caiDatHienThiService.findById(id);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(CoreCaiDatHienThi.class, "id", String.valueOf(id));
//		}
//		String email = uaaUserService.getUsername();
//		CoreCaiDatHienThi caiDatHienThi = optional.get();
//		caiDatHienThi.setNguoiSuDung(email);
//		caiDatHienThi.setMaDanhSach(modelInput.getMadanhsach());
//		caiDatHienThi.setCauHinhDanhSachId(modelInput.getCauHinhDanhSachId());
//		caiDatHienThi.setIsHienThi(modelInput.getIsHienThi());
//		caiDatHienThi.setAppCode(appCode);
//		caiDatHienThi = caiDatHienThiService.save(caiDatHienThi);
//		return caiDatHienThi;
//	}
//
//	public List<CoreCaiDatHienThi> caiDatDanhSach(String appCode, DanhSachHienThiInput danhSachHienThiInput)
//			throws EntityNotFoundException {
//		String email = uaaUserService.getUsername();
//		List<CoreCaiDatHienThi> listCoreCaiDatHienThi = caiDatHienThiService.findByAppCodeAndNguoiSuDungAndMaDanhSach(
//				appCode, danhSachHienThiInput.getNguoiSuDung(), danhSachHienThiInput.getMaDanhSach());
//		if (listCoreCaiDatHienThi != null && !listCoreCaiDatHienThi.isEmpty()) {
//			for (CoreCaiDatHienThi coreCaiDatHienThi : listCoreCaiDatHienThi) {
//				coreCaiDatHienThi.setDaXoa(1);
//				caiDatHienThiService.save(coreCaiDatHienThi);
//			}
//		} else {
//			listCoreCaiDatHienThi = new ArrayList<CoreCaiDatHienThi>();
//		}
//		List<DanhSachHienThi> listDanhSachHienThi = danhSachHienThiInput.getListDanhSachHienThi();
//		log.debug("listDanhSachHienThi : " + listDanhSachHienThi);
//		if (listDanhSachHienThi != null && !listDanhSachHienThi.isEmpty()) {
//			for (DanhSachHienThi danhSachHienThi : listDanhSachHienThi) {
//				CoreCaiDatHienThi coreCaiDatHienThi = new CoreCaiDatHienThi();
//				if (danhSachHienThi.getCaiDatHienThiId() != null) {
//					Optional<CoreCaiDatHienThi> optional = caiDatHienThiService
//							.findById(danhSachHienThi.getCaiDatHienThiId());
//					if (optional.isPresent()) {
//						coreCaiDatHienThi = optional.get();
//					}
//				}
//				coreCaiDatHienThi.setDaXoa(0);
//				coreCaiDatHienThi.setNguoiSuDung(email);
//				coreCaiDatHienThi.setMaDanhSach(danhSachHienThi.getMaDanhSach());
//				coreCaiDatHienThi.setCauHinhDanhSachId(danhSachHienThi.getId());
//				coreCaiDatHienThi.setIsHienThi(danhSachHienThi.getIsHienThi());
//				coreCaiDatHienThi.setAppCode(appCode);
//				coreCaiDatHienThi = caiDatHienThiService.save(coreCaiDatHienThi);
//				listCoreCaiDatHienThi.add(coreCaiDatHienThi);
//			}
//		}
//		return listCoreCaiDatHienThi;
//	}
//
//	public boolean delete(String appCode, Long id) throws EntityNotFoundException {
//		Optional<CoreCaiDatHienThi> optional = caiDatHienThiService.findById(id);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(CoreCaiDatHienThi.class, "id", String.valueOf(id));
//		}
//		CoreCaiDatHienThi caiDatHienThi = optional.get();
//		caiDatHienThi.setAppCode(appCode);
//		caiDatHienThiService.delete(caiDatHienThi.getId());
//		return true;
//	}
//
//}
