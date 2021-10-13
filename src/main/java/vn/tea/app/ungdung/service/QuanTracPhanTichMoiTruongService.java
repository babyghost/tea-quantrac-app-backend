package vn.tea.app.ungdung.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.ungdung.entity.QuanTracPhanTichMoiTruong;

public interface QuanTracPhanTichMoiTruongService {

	public Optional<QuanTracPhanTichMoiTruong> findById(Long id);
	
	public Optional<QuanTracPhanTichMoiTruong> findByIdAndTrangThaiAndDaXoa(Long id, Integer trangThai, Integer daXoa);
	
	public Optional<QuanTracPhanTichMoiTruong> findFirstByDaXoaOrderByIdDesc(Integer daXoa);
	
	public QuanTracPhanTichMoiTruong save(QuanTracPhanTichMoiTruong viTri);

	public void delete(Long id);
	
	public boolean existsById(Long id);

	public Page<QuanTracPhanTichMoiTruong> findAll(String ten, Long donViHanhChinhId, LocalDate ngayTaoTuNgay, LocalDate ngayTaoDenNgay, Pageable pageable);

}
