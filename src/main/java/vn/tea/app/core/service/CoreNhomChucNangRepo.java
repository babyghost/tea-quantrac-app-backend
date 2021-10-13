package vn.tea.app.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreNhomChucNang;

@Repository
public interface CoreNhomChucNangRepo
		extends JpaRepository<CoreNhomChucNang, Long>, JpaSpecificationExecutor<CoreNhomChucNang> {

	public List<CoreNhomChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Integer daXoa);

	public List<CoreNhomChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Integer daXoa);

	public List<CoreNhomChucNang> findByTrangThaiAndDaXoa(Integer trangThai, Integer daXoa);

	public List<CoreNhomChucNang> findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(Long nhomChucNangChaId, Integer trangThai,
			Integer daXoa);

	public List<CoreNhomChucNang> findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(Integer trangThai,
			Integer daXoa);

	@Modifying(clearAutomatically = true)
	@Query("update CoreNhomChucNang u set u.daXoa = ?1")
	public int setFixedDaXoa(Integer daXoa);
	
	public List<CoreNhomChucNang> findByMaIgnoreCase(String ma);
}
