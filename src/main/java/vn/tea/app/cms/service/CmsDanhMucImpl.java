package vn.tea.app.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.cms.entity.CmsDanhMuc;
import vn.tea.app.cms.repo.CmsDanhMucRepo;

@Service
public class CmsDanhMucImpl implements CmsDanhMucService {

	@Autowired
	private CmsDanhMucRepo repo;

	public Optional<CmsDanhMuc> findById(Long id) {
		return repo.findById(id);
	}

	public CmsDanhMuc save(CmsDanhMuc coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}

	public Page<CmsDanhMuc> findAll(String ten, String ma, Pageable pageable) {
		return repo.findAll(CmsDanhMucSpecifications.quickSearch(ten, ma), pageable);
	}


	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CmsDanhMuc> findByTenAndDaXoa(String ten, Integer daXoa) {
		return repo.findByTenAndDaXoa(ten, daXoa);
	}

}
