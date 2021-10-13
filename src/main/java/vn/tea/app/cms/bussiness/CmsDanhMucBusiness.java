package vn.tea.app.cms.bussiness;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.cms.dao.CmsDanhMucData;
import vn.tea.app.cms.entity.CmsDanhMuc;
import vn.tea.app.cms.service.CmsDanhMucService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
@Slf4j
public class CmsDanhMucBusiness {

	@Autowired
	private CmsDanhMucService coreChucNangService;

	public Page<CmsDanhMucData> findAll(String ten, String ma, int page, int size, String sortBy, String sortDir) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CmsDanhMuc> pageCmsDanhMuc = coreChucNangService.findAll(ten, ma, PageRequest.of(page, size, direction, sortBy));
		Page<CmsDanhMucData> result = pageCmsDanhMuc.map(this::convertDataCmsDanhMuc);
		return result;
	}

	public CmsDanhMucData convertDataCmsDanhMuc(CmsDanhMuc dataIn) {
		CmsDanhMucData dataOut = new CmsDanhMucData();
		dataOut.setId(dataIn.getId());
		dataOut.setCatCode(dataIn.getCatCode());
		dataOut.setTrangThai(dataIn.getTrangThai());
		dataOut.setMa(dataIn.getMa());
		dataOut.setTen(dataIn.getTen());
		return dataOut;
	}
	public CmsDanhMuc findById(Long id) throws EntityNotFoundException {
		Optional<CmsDanhMuc> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CmsDanhMuc.class, "id", String.valueOf(id));
		}
		CmsDanhMuc coreChucNang = optional.get();
		return coreChucNang;
	}

	public CmsDanhMuc create(CmsDanhMucData coreChucNangData)
			throws MethodArgumentNotValidException {
		
		CmsDanhMuc coreChucNang = new CmsDanhMuc();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMa(coreChucNangData.getMa());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		coreChucNangData.setCatCode(coreChucNangData.getCatCode()); // cat_code = "TAC_DONG" danh muc tac dong
		coreChucNang = coreChucNangService.save(coreChucNang);
		return coreChucNang;
	}

	public CmsDanhMuc update(Long id, CmsDanhMucData coreChucNangData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<CmsDanhMuc> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CmsDanhMuc.class, "id", String.valueOf(id));
		}
		CmsDanhMuc coreChucNang = optional.get();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		coreChucNang.setMa(coreChucNangData.getMa());
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
