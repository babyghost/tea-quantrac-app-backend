package vn.tea.app.ungdung.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.ungdung.entity.QuanTracPhanTichMoiTruong;
import vn.tea.app.ungdung.repo.QuanTracPhanTichMoiTruongRepo;

@Service
public class QuanTracPhanTichMoiTruongImpl implements QuanTracPhanTichMoiTruongService {

	@Autowired
	private QuanTracPhanTichMoiTruongRepo repo;

	public Optional<QuanTracPhanTichMoiTruong> findById(Long id) {
		return repo.findById(id);
	}
	
	public Optional<QuanTracPhanTichMoiTruong> findByIdAndTrangThaiAndDaXoa(Long id, Integer trangThai, Integer daXoa) {
		return repo.findByIdAndTrangThaiAndDaXoa(id, trangThai, daXoa);
	}

	public Optional<QuanTracPhanTichMoiTruong> findFirstByDaXoaOrderByIdDesc(Integer daXoa) {
		return repo.findFirstByDaXoaOrderByIdDesc(daXoa);
	}

	
	public QuanTracPhanTichMoiTruong save(QuanTracPhanTichMoiTruong coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public Page<QuanTracPhanTichMoiTruong> findAll(String ten, Long donViHanhChinhId, LocalDate ngayTaoTuNgay, LocalDate ngayTaoDenNgay, Pageable pageable) {
		return repo.findAll(QuanTracPhanTichMoiTruongSpecifications.quickSearch(ten, donViHanhChinhId, ngayTaoTuNgay, ngayTaoDenNgay), pageable);
	}

	@Override
	public Optional<QuanTracPhanTichMoiTruong> findFirstByThangAndNamAndLoaiAndDaXoa(Integer thang, Integer nam, String loai,
			Integer daXoa) {
		// TODO Auto-generated method stub
		return repo.findFirstByThangAndNamAndLoaiAndDaXoa( thang, nam, loai, daXoa);
	}
}
