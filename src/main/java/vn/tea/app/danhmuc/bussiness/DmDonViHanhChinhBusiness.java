package vn.tea.app.danhmuc.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.danhmuc.dao.DmDonViHanhChinhData;
import vn.tea.app.danhmuc.entity.DmDonViHanhChinh;
import vn.tea.app.danhmuc.service.DmDonViHanhChinhService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
@Slf4j
public class DmDonViHanhChinhBusiness {

	@Autowired
	private DmDonViHanhChinhService coreChucNangService;

	public Page<DmDonViHanhChinhData> findAll(String ten, String ma, int page, int size, String sortBy, String sortDir) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmDonViHanhChinh> pageDmDonViHanhChinh = coreChucNangService.findAll(ten, ma, PageRequest.of(page, size, direction, sortBy));
		Page<DmDonViHanhChinhData> result = pageDmDonViHanhChinh.map(this::convertDataDonViHanhChinh);
		return result;
	}

	public DmDonViHanhChinhData convertDataDonViHanhChinh(DmDonViHanhChinh dataIn) {
		DmDonViHanhChinhData dataOut = new DmDonViHanhChinhData();
		dataOut.setId(dataIn.getId());
		dataOut.setCatCode(dataIn.getCatCode());
		dataOut.setIdCha(dataIn.getIdCha());
		dataOut.setMa(dataIn.getMa());
		dataOut.setTen(dataIn.getTen());
		if(dataIn.getIdCha() != null && dataIn.getIdCha() > 0) {
			Optional<DmDonViHanhChinh> opt = coreChucNangService.findById(dataIn.getIdCha());
			if(opt.isPresent()) {
				dataOut.setTenDonViCha(opt.get().getTen());
			}
		}
		return dataOut;
	}
	public DmDonViHanhChinh findById(Long id) throws EntityNotFoundException {
		Optional<DmDonViHanhChinh> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonViHanhChinh.class, "id", String.valueOf(id));
		}
		DmDonViHanhChinh coreChucNang = optional.get();
		return coreChucNang;
	}

	public DmDonViHanhChinh create(DmDonViHanhChinhData coreChucNangData)
			throws MethodArgumentNotValidException {
		
		DmDonViHanhChinh coreChucNang = new DmDonViHanhChinh();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		coreChucNang.setIdCha(coreChucNangData.getIdCha());
		coreChucNangData.setCatCode(coreChucNangData.getCatCode()); // cat_code = "TAC_DONG" danh muc tac dong
		coreChucNang = coreChucNangService.save(coreChucNang);
		return coreChucNang;
	}

	public DmDonViHanhChinh update(Long id, DmDonViHanhChinhData coreChucNangData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<DmDonViHanhChinh> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonViHanhChinh.class, "id", String.valueOf(id));
		}
		DmDonViHanhChinh coreChucNang = optional.get();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		coreChucNang.setIdCha(coreChucNangData.getIdCha());
		coreChucNangData.setCatCode(coreChucNangData.getCatCode()); // cat_code = "TAC_DONG" danh muc tac dong
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
