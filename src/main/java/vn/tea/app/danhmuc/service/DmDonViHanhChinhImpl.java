package vn.tea.app.danhmuc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.danhmuc.entity.DmDonViHanhChinh;
import vn.tea.app.danhmuc.repo.DmDonViHanhChinhRepo;

@Service
public class DmDonViHanhChinhImpl implements DmDonViHanhChinhService {

	@Autowired
	private DmDonViHanhChinhRepo repo;

	public Optional<DmDonViHanhChinh> findById(Long id) {
		return repo.findById(id);
	}

	public DmDonViHanhChinh save(DmDonViHanhChinh coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}

	public Page<DmDonViHanhChinh> findAll(String ten, String ma, Pageable pageable) {
		return repo.findAll(DmDonViHanhChinhSpecifications.quickSearch(ten, ma), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<DmDonViHanhChinh> findByTenAndDaXoa(String ten, Integer daXoa) {
		return repo.findByTenAndDaXoa(ten, daXoa);
	}

}
