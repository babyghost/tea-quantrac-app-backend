package vn.tea.app.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.cms.entity.CmsThongTinLienHe;
import vn.tea.app.cms.repo.CmsThongTinLienHeRepo;

@Service
public class CmsThongTinLienHeImpl implements CmsThongTinLienHeService {

	@Autowired
	private CmsThongTinLienHeRepo repo;

	public Optional<CmsThongTinLienHe> findById(Long id) {
		return repo.findById(id);
	}

	public CmsThongTinLienHe save(CmsThongTinLienHe object) {
		return repo.save(object);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}

	public Page<CmsThongTinLienHe> findAll(String ten, Pageable pageable) {
		return repo.findAll(CmsThongTinLienHeSpecifications.quickSearch(ten), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CmsThongTinLienHe> findByTenAndDaXoa(String ten, Integer daXoa) {
		return repo.findByTenAndDaXoa(ten, daXoa);
	}

}
