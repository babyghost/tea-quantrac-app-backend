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
	private QuanTracHeSoTrungBinhService quanTracHeSoTrungBinhService;
	@Autowired
	private QuanTracHeSoTrungBinhValidator quanTracHeSoTrungBinhValidator;

	public Page<QuanTracHeSoTrungBinh> findAll(String ten, int page, int size, String sortBy, String sortDir) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<QuanTracHeSoTrungBinh> pageQuanTracHeSoTrungBinh = quanTracHeSoTrungBinhService.findAll(ten, PageRequest.of(page, size, direction, sortBy));
		return pageQuanTracHeSoTrungBinh;
	}

	public QuanTracHeSoTrungBinh findById(Long id) throws EntityNotFoundException {
		Optional<QuanTracHeSoTrungBinh> optional = quanTracHeSoTrungBinhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(QuanTracHeSoTrungBinh.class, "id", String.valueOf(id));
		}
		QuanTracHeSoTrungBinh coreChucNang = optional.get();
		return coreChucNang;
	}

	public QuanTracHeSoTrungBinh create(QuanTracHeSoTrungBinhData data, BindingResult result)
			throws MethodArgumentNotValidException {
		quanTracHeSoTrungBinhValidator.validate(data, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		QuanTracHeSoTrungBinh coreChucNang = new QuanTracHeSoTrungBinh();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(data.getTen());
		coreChucNang.setChiTieuNo2(data.getChiTieuNo2());
		coreChucNang.setChiTieuO3(data.getChiTieuO3());
		coreChucNang.setChiTieuPm10(data.getChiTieuPm10());
		coreChucNang.setChiTieuSo2(data.getChiTieuSo2());
		coreChucNang.setChiTieuTsp(data.getChiTieuTsp());
		coreChucNang = quanTracHeSoTrungBinhService.save(coreChucNang);
		return coreChucNang;
	}

	public QuanTracHeSoTrungBinh update(Long id, QuanTracHeSoTrungBinhData data, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		quanTracHeSoTrungBinhValidator.validate(data, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		Optional<QuanTracHeSoTrungBinh> optional = quanTracHeSoTrungBinhService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(QuanTracHeSoTrungBinh.class, "id", String.valueOf(id));
		}
		QuanTracHeSoTrungBinh coreChucNang = optional.get();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(data.getTen());
		coreChucNang.setChiTieuNo2(data.getChiTieuNo2());
		coreChucNang.setChiTieuO3(data.getChiTieuO3());
		coreChucNang.setChiTieuPm10(data.getChiTieuPm10());
		coreChucNang.setChiTieuSo2(data.getChiTieuSo2());
		coreChucNang.setChiTieuTsp(data.getChiTieuTsp());
		coreChucNang = quanTracHeSoTrungBinhService.save(coreChucNang);

		return coreChucNang;
	}

	public int delete(Long id) throws EntityNotFoundException {
			try {
				quanTracHeSoTrungBinhService.delete(id);
				return 1;
			} catch (Exception e) {
				// TODO: handle exception
				return 0;
			}
	}
}
