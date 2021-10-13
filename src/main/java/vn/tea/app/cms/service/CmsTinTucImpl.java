package vn.tea.app.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.cms.entity.CmsDanhMuc;
import vn.tea.app.cms.entity.CmsTinTuc;
import vn.tea.app.cms.repo.CmsTinTucRepo;

@Service
public class CmsTinTucImpl implements CmsTinTucService {

	@Autowired
	private CmsTinTucRepo repo;

	public Optional<CmsTinTuc> findById(Long id) {
		return repo.findById(id);
	}

	public CmsTinTuc save(CmsTinTuc object) {
		return repo.save(object);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}

	public Page<CmsTinTuc> findAll(String ten, Pageable pageable) {
		return repo.findAll(CmsTinTucSpecifications.quickSearch(ten), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CmsTinTuc> findByTenAndDaXoa(String ten, Integer daXoa) {
		return repo.findByTenAndDaXoa(ten, daXoa);
	}
	@Override
	public Page<CmsTinTuc> danhSachTinTucPublic(String search, List<Long> danhMucIds, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(CmsTinTucSpecifications.danhSachTinTucPublic(search, danhMucIds), pageable);
	}
	

}
