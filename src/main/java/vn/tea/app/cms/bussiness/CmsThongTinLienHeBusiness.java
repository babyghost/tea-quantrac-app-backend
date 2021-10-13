package vn.tea.app.cms.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.cms.dao.CmsThongTinLienHeData;
import vn.tea.app.cms.entity.CmsThongTinLienHe;
import vn.tea.app.cms.service.CmsThongTinLienHeService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
@Slf4j
public class CmsThongTinLienHeBusiness {

	@Autowired
	private CmsThongTinLienHeService coreChucNangService;

	public Page<CmsThongTinLienHeData> findAll(String ten, String ma, int page, int size, String sortBy, String sortDir) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CmsThongTinLienHe> pageCmsThongTinLienHe = coreChucNangService.findAll(ten, PageRequest.of(page, size, direction, sortBy));
		Page<CmsThongTinLienHeData> result = pageCmsThongTinLienHe.map(this::convertDataThongTinLienHe);
		return result;
	}

	public CmsThongTinLienHeData convertDataThongTinLienHe(CmsThongTinLienHe dataIn) {
		CmsThongTinLienHeData dataOut = new CmsThongTinLienHeData();
		dataOut.setId(dataIn.getId());
		dataOut.setMa(dataIn.getMa());
		dataOut.setTen(dataIn.getTen());
		dataOut.setEmail(dataIn.getEmail());
		dataOut.setNoiDung(dataIn.getNoiDung());
		dataOut.setTrangThai(dataIn.getTrangThai());
		return dataOut;
	}
	public CmsThongTinLienHe findById(Long id) throws EntityNotFoundException {
		Optional<CmsThongTinLienHe> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CmsThongTinLienHe.class, "id", String.valueOf(id));
		}
		CmsThongTinLienHe coreChucNang = optional.get();
		return coreChucNang;
	}

	public CmsThongTinLienHe create(CmsThongTinLienHeData coreChucNangData)
			throws MethodArgumentNotValidException {
		
		CmsThongTinLienHe coreChucNang = new CmsThongTinLienHe();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		coreChucNang.setEmail(coreChucNangData.getEmail());
		coreChucNang.setSoDienThoai(coreChucNangData.getSoDienThoai());
		coreChucNang.setNoiDung(coreChucNangData.getNoiDung());
		coreChucNang = coreChucNangService.save(coreChucNang);
		return coreChucNang;
	}

	public CmsThongTinLienHe update(Long id, CmsThongTinLienHeData coreChucNangData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<CmsThongTinLienHe> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CmsThongTinLienHe.class, "id", String.valueOf(id));
		}
		CmsThongTinLienHe coreChucNang = optional.get();
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		coreChucNang.setEmail(coreChucNangData.getEmail());
		coreChucNang.setSoDienThoai(coreChucNangData.getSoDienThoai());
		coreChucNang.setNoiDung(coreChucNangData.getNoiDung());
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
