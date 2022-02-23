package vn.tea.app.ungdung.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.ungdung.entity.QuanTracDanhMuc;
import vn.tea.app.ungdung.repo.QuanTracDanhMucRepo;

@Service
public class QuanTracDanhMucImpl implements QuanTracDanhMucService {

	@Autowired
	private QuanTracDanhMucRepo repo;

	public Optional<QuanTracDanhMuc> findById(Long id) {
		return repo.findById(id);
	}

	public QuanTracDanhMuc save(QuanTracDanhMuc coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}

	public Page<QuanTracDanhMuc> findAll(String ten, Long donViHanhChinhId, String catCode, String ma, Pageable pageable) {
		return repo.findAll(QuanTracDanhMucSpecifications.quickSearch(ten, donViHanhChinhId, catCode, ma), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<QuanTracDanhMuc> findByTenAndDaXoa(String ten, Integer daXoa) {
		return repo.findByTenAndDaXoa(ten, daXoa);
	}

	@Override
	public QuanTracDanhMuc findByMa(String ma) {
		// TODO Auto-generated method stub
		return repo.findByMaAndDaXoa(ma, 0);
	}

}
