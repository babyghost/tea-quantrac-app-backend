package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.tea.app.core.entity.CoreNhomChucNang;

@Service
@Transactional
public class CoreNhomChucNangServiceImpl implements CoreNhomChucNangService {

	@Autowired
	private CoreNhomChucNangRepo repo;

	public Optional<CoreNhomChucNang> findById(Long id) {
		return repo.findById(id);
	}

	public CoreNhomChucNang save(CoreNhomChucNang coreNhomChucNang) {
		return repo.save(coreNhomChucNang);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreNhomChucNang> findAll(String search, Integer trangThai, Long id, Long nhomChucNangChaId,
			Pageable pageable) {
		return repo.findAll(CoreNhomChucNangSpecifications.quickSearch(search, trangThai, id, nhomChucNangChaId),
				pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CoreNhomChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Integer daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<CoreNhomChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Integer daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

	public List<CoreNhomChucNang> findByTrangThaiAndDaXoa(Integer trangThai, Integer daXoa) {
		return repo.findByTrangThaiAndDaXoa(trangThai, daXoa);
	}

	public List<CoreNhomChucNang> findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(Long nhomChucNangChaId,
			Integer trangThai, Integer daXoa) {
		return repo.findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(nhomChucNangChaId, trangThai, daXoa);
	}

	public List<CoreNhomChucNang> findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(Integer trangThai,
			Integer daXoa) {
		return repo.findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(trangThai, daXoa);
	}

	public int setFixedDaXoa(Integer daXoa) {
		return repo.setFixedDaXoa(daXoa);
	}

	public List<CoreNhomChucNang> findByMaIgnoreCase(String ma) {
		return repo.findByMaIgnoreCase(ma);
	}
	
	

}
