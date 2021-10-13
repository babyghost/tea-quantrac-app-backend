package vn.tea.app.ungdung.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.ungdung.entity.QuanTracHeSoTrungBinh;
import vn.tea.app.ungdung.repo.QuanTracHeSoTrungBinhRepo;

@Service
public class QuanTracHeSoTrungBinhImpl implements QuanTracHeSoTrungBinhService {

	@Autowired
	private QuanTracHeSoTrungBinhRepo repo;

	public Optional<QuanTracHeSoTrungBinh> findById(Long id) {
		return repo.findById(id);
	}

	public QuanTracHeSoTrungBinh save(QuanTracHeSoTrungBinh coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}

	public Page<QuanTracHeSoTrungBinh> findAll(String ten, Pageable pageable) {
		return repo.findAll(QuanTracHeSoTrungBinhSpecifications.quickSearch(ten), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<QuanTracHeSoTrungBinh> findByTenAndDaXoa(String ten, Integer daXoa) {
		return repo.findByTenAndDaXoa(ten, daXoa);
	}

}
