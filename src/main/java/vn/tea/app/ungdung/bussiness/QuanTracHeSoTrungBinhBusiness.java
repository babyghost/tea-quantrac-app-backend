package vn.tea.app.ungdung.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.exceptions.EntityNotFoundException;
import vn.tea.app.ungdung.dao.QuanTracHeSoTrungBinhData;
import vn.tea.app.ungdung.entity.QuanTracHeSoTrungBinh;
import vn.tea.app.ungdung.service.QuanTracHeSoTrungBinhService;
import vn.tea.app.ungdung.validator.QuanTracHeSoTrungBinhValidator;

@Service
@Slf4j
public class QuanTracHeSoTrungBinhBusiness {

	@Autowired
	private QuanTracHeSoTrungBinhService coreChucNangService;
	@Autowired
	private QuanTracHeSoTrungBinhValidator coreChucNangValidator;

	public Page<QuanTracHeSoTrungBinh> findAll(String ten, int page, int size, String sortBy, String sortDir) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<QuanTracHeSoTrungBinh> pageQuanTracHeSoTrungBinh = coreChucNangService.findAll(ten, PageRequest.of(page, size, direction, sortBy));
		return pageQuanTracHeSoTrungBinh;
	}

	public QuanTracHeSoTrungBinh findById(Long id) throws EntityNotFoundException {
		Optional<QuanTracHeSoTrungBinh> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(QuanTracHeSoTrungBinh.class, "id", String.valueOf(id));
		}
		QuanTracHeSoTrungBinh coreChucNang = optional.get();
		return coreChucNang;
	}

	public QuanTracHeSoTrungBinh create(QuanTracHeSoTrungBinhData coreChucNangData, BindingResult result)
			throws MethodArgumentNotValidException {
		coreChucNangValidator.validate(coreChucNangData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		QuanTracHeSoTrungBinh coreChucNang = new QuanTracHeSoTrungBinh();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setChiTieuNo2(coreChucNangData.getChiTieuNo2());
		coreChucNang.setChiTieuO3(coreChucNangData.getChiTieuO3());
		coreChucNang.setChiTieuPm10(coreChucNangData.getChiTieuPm10());
		coreChucNang.setChiTieuSo2(coreChucNangData.getChiTieuSo2());
		coreChucNang.setChiTieuTsp(coreChucNangData.getChiTieuTsp());
		coreChucNang = coreChucNangService.save(coreChucNang);
		return coreChucNang;
	}

	public QuanTracHeSoTrungBinh update(Long id, QuanTracHeSoTrungBinhData coreChucNangData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coreChucNangValidator.validate(coreChucNangData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		Optional<QuanTracHeSoTrungBinh> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(QuanTracHeSoTrungBinh.class, "id", String.valueOf(id));
		}
		QuanTracHeSoTrungBinh coreChucNang = optional.get();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setChiTieuNo2(coreChucNangData.getChiTieuNo2());
		coreChucNang.setChiTieuO3(coreChucNangData.getChiTieuO3());
		coreChucNang.setChiTieuPm10(coreChucNangData.getChiTieuPm10());
		coreChucNang.setChiTieuSo2(coreChucNangData.getChiTieuSo2());
		coreChucNang.setChiTieuTsp(coreChucNangData.getChiTieuTsp());
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
