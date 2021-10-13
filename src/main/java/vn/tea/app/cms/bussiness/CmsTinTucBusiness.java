package vn.tea.app.cms.bussiness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.cms.dao.CmsTinTucData;
import vn.tea.app.cms.dao.CmsTinTucDataPublic;
import vn.tea.app.cms.entity.CmsDanhMuc;
import vn.tea.app.cms.entity.CmsTinTuc;
import vn.tea.app.cms.entity.CmsTinTuc2DanhMuc;
import vn.tea.app.cms.service.CmsDanhMucService;
import vn.tea.app.cms.service.CmsTinTuc2DanhMucService;
import vn.tea.app.cms.service.CmsTinTucService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
@Slf4j
public class CmsTinTucBusiness {

	@Autowired
	private CmsTinTucService coreChucNangService;
	@Autowired
	private CmsTinTuc2DanhMucService serviceCmsTinTuc2DanhMucService;
	@Autowired
	private CmsDanhMucService serviceCmsDanhMucService;
	public Page<CmsTinTucData> findAll(String ten, int page, int size, String sortBy, String sortDir) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CmsTinTuc> pageCmsTinTuc = coreChucNangService.findAll(ten, PageRequest.of(page, size, direction, sortBy));
		Page<CmsTinTucData> result = pageCmsTinTuc.map(this::convertCmsTinTucData);
		return result;
	}
	
	public CmsTinTucDataPublic chiTietPublic(Long id) throws EntityNotFoundException {
		Optional<CmsTinTuc> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CmsTinTuc.class, "id", String.valueOf(id));
		}
		CmsTinTuc coreChucNang = optional.get();
		CmsTinTucDataPublic result = this.convertCmsTinTucDataPublic(coreChucNang);
		return result;
	}

	public Page<CmsTinTucDataPublic> danhSachTinTucPublic(String search, List<Long> danhMucIds, int page, int size, String sortBy, String sortDir) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CmsTinTuc> pageCmsTinTuc = coreChucNangService.danhSachTinTucPublic(search, danhMucIds, PageRequest.of(page, size, direction, sortBy));
		Page<CmsTinTucDataPublic> result = pageCmsTinTuc.map(this::convertCmsTinTucDataPublic);
		return result;
	}
	public CmsTinTucDataPublic convertCmsTinTucDataPublic(CmsTinTuc dataIn) {
		CmsTinTucDataPublic dataOut = new CmsTinTucDataPublic();
		dataOut.setId(dataIn.getId());
		dataOut.setTen(dataIn.getTen());
		dataOut.setAnhThumbUrl(dataIn.getAnhThumbUrl());
		dataOut.setMoTa(dataIn.getMoTa());
		dataOut.setNoiDung(dataIn.getNoiDung());
		dataOut.setTrangThai(dataIn.getTrangThai());
		dataOut.setNgayXuatBan(dataIn.getNgayXuatBan());
		List<CmsTinTuc2DanhMuc> listTinTuc2DanhMuc = serviceCmsTinTuc2DanhMucService.findByTinTucIdAndDaXoa(dataIn.getId(), 0);
		List<HashMap<String, String>> dms = new ArrayList<HashMap<String, String>>();
		if(listTinTuc2DanhMuc.size() > 0) {
			for(CmsTinTuc2DanhMuc i : listTinTuc2DanhMuc) {
				HashMap<String, String> item = new HashMap<String, String>();
				item.put("danhMucId", String.valueOf(i.getDanhMucId()));
				item.put("danhMucString", String.valueOf(i.getDanhMucString()));
				dms.add(item);
			}
		}
		dataOut.setListDanhMuc(dms);
		return dataOut;
	}
	public CmsTinTucData convertCmsTinTucData(CmsTinTuc dataIn) {
		CmsTinTucData dataOut = new CmsTinTucData();
		dataOut.setId(dataIn.getId());
		dataOut.setTen(dataIn.getTen());
		dataOut.setAnhThumbUrl(dataIn.getAnhThumbUrl());
		dataOut.setMoTa(dataIn.getMoTa());
		dataOut.setNoiDung(dataIn.getNoiDung());
		dataOut.setTrangThai(dataIn.getTrangThai());
		dataOut.setNgayXuatBan(dataIn.getNgayXuatBan());
		List<CmsTinTuc2DanhMuc> listTinTuc2DanhMuc = serviceCmsTinTuc2DanhMucService.findByTinTucIdAndDaXoa(dataIn.getId(), 0);
		List<Long> dms = new ArrayList<Long>();
		if(listTinTuc2DanhMuc.size() > 0) {
			for(CmsTinTuc2DanhMuc i : listTinTuc2DanhMuc) {
				dms.add(i.getDanhMucId());
			}
		}
		dataOut.setListDanhMuc(dms);
		return dataOut;
	}
	public CmsTinTucData findById(Long id) throws EntityNotFoundException {
		Optional<CmsTinTuc> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CmsTinTuc.class, "id", String.valueOf(id));
		}
		CmsTinTuc coreChucNang = optional.get();
		CmsTinTucData result = this.convertCmsTinTucData(coreChucNang);
		return result;
	}

	public CmsTinTuc create(CmsTinTucData coreChucNangData)
			throws MethodArgumentNotValidException {
		
		CmsTinTuc coreChucNang = new CmsTinTuc();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMoTa(coreChucNangData.getMoTa());
		coreChucNang.setAnhThumbUrl(coreChucNangData.getAnhThumbUrl());
		coreChucNang.setNoiDung(coreChucNangData.getNoiDung());
		coreChucNang.setNgayXuatBan(coreChucNangData.getNgayXuatBan());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		CmsTinTuc result = coreChucNangService.save(coreChucNang);
		serviceCmsTinTuc2DanhMucService.deleteAllByTinTucId(result.getId());
		if(coreChucNangData.getListDanhMuc().size() > 0) {
			for(Long i : coreChucNangData.getListDanhMuc()) {
				CmsTinTuc2DanhMuc dm = new CmsTinTuc2DanhMuc();
				Optional<CmsDanhMuc> dm_choise = serviceCmsDanhMucService.findById(i);
				if(dm_choise.isPresent()) {
					dm.setDanhMucId(i);
					dm.setDanhMucString(dm_choise.get().getTen());
				}
				dm.setTinTucId(result.getId());
				dm.setDaXoa(0);
				serviceCmsTinTuc2DanhMucService.save(dm);
			}
		}
		return result;
	}

	public CmsTinTuc update(Long id, CmsTinTucData coreChucNangData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<CmsTinTuc> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CmsTinTuc.class, "id", String.valueOf(id));
		}
		CmsTinTuc coreChucNang = optional.get();

		coreChucNang.setDaXoa(0);
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setMoTa(coreChucNangData.getMoTa());
		coreChucNang.setAnhThumbUrl(coreChucNangData.getAnhThumbUrl());
		coreChucNang.setNoiDung(coreChucNangData.getNoiDung());
		coreChucNang.setNgayXuatBan(coreChucNangData.getNgayXuatBan());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		CmsTinTuc result = coreChucNangService.save(coreChucNang);
		serviceCmsTinTuc2DanhMucService.deleteAllByTinTucId(result.getId());
		if(coreChucNangData.getListDanhMuc().size() > 0) {
			for(Long i : coreChucNangData.getListDanhMuc()) {
				CmsTinTuc2DanhMuc dm = new CmsTinTuc2DanhMuc();
				Optional<CmsDanhMuc> dm_choise = serviceCmsDanhMucService.findById(i);
				if(dm_choise.isPresent()) {
					dm.setDanhMucId(i);
					dm.setDanhMucString(dm_choise.get().getTen());
				}
				dm.setTinTucId(result.getId());
				dm.setDaXoa(0);
				serviceCmsTinTuc2DanhMucService.save(dm);
			}
		}
		return result;
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
