package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.core.entity.CoreChucNang;

public interface CoreChucNangService {

	public Optional<CoreChucNang> findById(Long id);

	public CoreChucNang save(CoreChucNang coreChucNang);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CoreChucNang> findAll(String search, Long nhomChucNangId, Integer trangThai, Pageable pageable);

	public List<CoreChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Integer daXoa);

	public List<CoreChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Integer daXoa);

	public List<CoreChucNang> findByCoreNhomChucNangIdAndTrangThaiAndDaXoa(Long nhomChucNangId, Integer trangThai,
			Integer daXoa);

	public int setFixedDaXoaForNhomChucNangId(Integer daXoa, Long nhomChucNangId);

	public int setFixedDaXoa(Integer daXoa);

	public List<CoreChucNang> findByMaIgnoreCase(String ma);

	public List<String> getChucNangMas(String roleName);
	
	
	

}
