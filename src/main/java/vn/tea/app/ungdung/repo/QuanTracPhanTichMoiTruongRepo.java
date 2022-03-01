package vn.tea.app.ungdung.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.ungdung.entity.QuanTracPhanTichMoiTruong;

@Repository
public interface QuanTracPhanTichMoiTruongRepo extends JpaRepository<QuanTracPhanTichMoiTruong, Long>, JpaSpecificationExecutor<QuanTracPhanTichMoiTruong> {

	@Transactional
	@Modifying
	@Query("update QuanTracPhanTichMoiTruong u set u.daXoa = 1 where u.id = ?1")
	public int setFixedDaXoaById(Long id);

	
	public Optional<QuanTracPhanTichMoiTruong> findByIdAndTrangThaiAndDaXoa(Long id, Integer trangThai, Integer daXoa);
	public Optional<QuanTracPhanTichMoiTruong> findFirstByDaXoaOrderByIdDesc(Integer daXoa);
	
	public Optional<QuanTracPhanTichMoiTruong> findFirstByThangAndNamAndLoaiAndDaXoa(Integer thang, Integer nam, String loai, Integer daXoa);
	
	public List<QuanTracPhanTichMoiTruong> findByNgayTaoAndDaXoa(LocalDate ngayTao, Integer daXoa);

}
