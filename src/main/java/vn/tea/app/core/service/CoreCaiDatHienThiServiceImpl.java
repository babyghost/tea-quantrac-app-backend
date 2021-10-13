package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.core.entity.CoreCaiDatHienThi;

@Service
public class CoreCaiDatHienThiServiceImpl implements CoreCaiDatHienThiService {
	@Autowired
	private CoreCaiDatHienThiRepo repo;

	public Optional<CoreCaiDatHienThi> findById(Long id) {
		return repo.findById(id);
	}

	public CoreCaiDatHienThi save(CoreCaiDatHienThi CoreCaiDatHienThi) {
		return repo.save(CoreCaiDatHienThi);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreCaiDatHienThi> findAll(String ma, Pageable pageable) {
		return repo.findAll(CoreCaiDatHienThiSpecifications.quickSearch(ma), pageable);
	}

	public List<CoreCaiDatHienThi> findByAppCodeAndNguoiSuDungAndMaDanhSachAndDaXoa(String appCode, String nguoiSuDung,
			String maDanhSach, boolean daXoa) {
		return repo.findByAppCodeAndNguoiSuDungAndMaDanhSachAndDaXoa(appCode, nguoiSuDung, maDanhSach, daXoa);
	}

	public List<CoreCaiDatHienThi> findByAppCodeAndNguoiSuDungAndMaDanhSach(String appCode, String nguoiSuDung,
			String maDanhSach) {
		return repo.findByAppCodeAndNguoiSuDungAndMaDanhSach(appCode, nguoiSuDung, maDanhSach);
	}
}
