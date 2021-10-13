package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.core.entity.CoreNhomChucNang;

public interface CoreNhomChucNangService {

	public Optional<CoreNhomChucNang> findById(Long id);

	public CoreNhomChucNang save(CoreNhomChucNang coreNhomChucNang);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CoreNhomChucNang> findAll(String search, Integer trangThai, Long id, Long nhomChucNangChaId,
			Pageable pageable);

	public List<CoreNhomChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Integer daXoa);

	public List<CoreNhomChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Integer daXoa);

	public List<CoreNhomChucNang> findByTrangThaiAndDaXoa(Integer trangThai, Integer daXoa);

	public List<CoreNhomChucNang> findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(Long nhomChucNangChaId, Integer trangThai,
			Integer daXoa);

	public List<CoreNhomChucNang> findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(Integer trangThai, Integer daXoa);

	public int setFixedDaXoa(Integer daXoa);

	public List<CoreNhomChucNang> findByMaIgnoreCase(String ma);

}
