package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.tea.app.core.entity.CoreChucNang;

@Service
@Transactional
public class CoreChucNangServiceImpl implements CoreChucNangService {

	@Autowired
	private CoreChucNangRepo repo;

	public Optional<CoreChucNang> findById(Long id) {
		return repo.findById(id);
	}

	public CoreChucNang save(CoreChucNang coreChucNang) {
		return repo.save(coreChucNang);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreChucNang> findAll(String search, Long nhomChucNangId, Integer trangThai, Pageable pageable) {
		return repo.findAll(CoreChucNangSpecifications.quickSearch(search, nhomChucNangId, trangThai), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CoreChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Integer daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<CoreChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Integer daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

	public List<CoreChucNang> findByCoreNhomChucNangIdAndTrangThaiAndDaXoa(Long nhomChucNangId, Integer trangThai,
			Integer daXoa) {
		return repo.findByCoreNhomChucNangIdAndTrangThaiAndDaXoaOrderBySapXepAsc(nhomChucNangId, trangThai, daXoa);
	}

	public int setFixedDaXoaForNhomChucNangId(Integer daXoa, Long nhomChucNangId) {
		return repo.setFixedDaXoaForNhomChucNangId(daXoa, nhomChucNangId);
	}

	public int setFixedDaXoa(Integer daXoa) {
		return repo.setFixedDaXoa(daXoa);
	}

	public List<CoreChucNang> findByMaIgnoreCase(String ma) {
		return repo.findByMaIgnoreCase(ma);
	}

	public List<String> getChucNangMas(String roleName) {
		return repo.getChucNangMas(roleName);
	}
}
