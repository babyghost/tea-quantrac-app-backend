package vn.tea.app.ungdung.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.danhmuc.service.DmDonViHanhChinhService;
import vn.tea.app.exceptions.EntityNotFoundException;
import vn.tea.app.ungdung.dao.QuanTracDanhMucData;
import vn.tea.app.ungdung.entity.QuanTracDanhMuc;
import vn.tea.app.ungdung.service.QuanTracDanhMucService;

@Service
@Slf4j
public class QuanTracDanhMucBusiness {

	@Autowired
	private QuanTracDanhMucService coreChucNangService;

	public Page<QuanTracDanhMuc> findAll(String ten, Long donViHanhChinhId, String catCode, String ma, int page, int size, String sortBy, String sortDir) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<QuanTracDanhMuc> pageQuanTracDanhMuc = coreChucNangService.findAll(ten, donViHanhChinhId, catCode, ma, PageRequest.of(page, size, direction, sortBy));
		return pageQuanTracDanhMuc;
	}
	
	public QuanTracDanhMuc findById(Long id) throws EntityNotFoundException {
		Optional<QuanTracDanhMuc> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(QuanTracDanhMuc.class, "id", String.valueOf(id));
		}
		QuanTracDanhMuc coreChucNang = optional.get();
		return coreChucNang;
	}

	public QuanTracDanhMuc create(QuanTracDanhMucData coreChucNangData)
			throws MethodArgumentNotValidException {
		
		QuanTracDanhMuc coreChucNang = new QuanTracDanhMuc();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		coreChucNang.setChaId(coreChucNangData.getDiaDiemHanhChinhId());
		coreChucNang.setLat(coreChucNangData.getLat());
		coreChucNang.setLng(coreChucNangData.getLng());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai()); // 0 khong hoat dong, 1 hoat dong
		coreChucNang.setCatCode(coreChucNangData.getCatCode()); // cat_code = "VITRI_QUANTRAC" danh muc vị trí quan trác
		coreChucNang = coreChucNangService.save(coreChucNang);
		return coreChucNang;
	}

	public QuanTracDanhMuc update(Long id, QuanTracDanhMucData coreChucNangData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<QuanTracDanhMuc> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(QuanTracDanhMuc.class, "id", String.valueOf(id));
		}
		QuanTracDanhMuc coreChucNang = optional.get();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		coreChucNang.setChaId(coreChucNangData.getDiaDiemHanhChinhId());
		coreChucNang.setLat(coreChucNangData.getLat());
		coreChucNang.setLng(coreChucNangData.getLng());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai()); // 0 khong hoat dong, 1 hoat dong
		coreChucNang.setCatCode(coreChucNangData.getCatCode()); // cat_code = "TAC_DONG" danh muc tac dong
		coreChucNang = coreChucNangService.save(coreChucNang);

		return coreChucNang;
	}

	public int delete(Long id) throws EntityNotFoundException {
			try {
				coreChucNangService.delete(id);
				return 1;
			} catch (Exception e) {
				// TODO: handle exception
				return 0;
			}
	}
}
