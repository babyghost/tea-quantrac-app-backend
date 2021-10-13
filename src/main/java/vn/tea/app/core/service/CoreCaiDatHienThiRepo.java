package vn.tea.app.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreCaiDatHienThi;

@Repository
public interface CoreCaiDatHienThiRepo
		extends JpaRepository<CoreCaiDatHienThi, Long>, JpaSpecificationExecutor<CoreCaiDatHienThi> {
	public List<CoreCaiDatHienThi> findByIdIn(List<Long> idList);

	public List<CoreCaiDatHienThi> findByAppCodeAndNguoiSuDungAndMaDanhSachAndDaXoa(String appCode, String nguoiSuDung,
			String maDanhSach, boolean daXoa);

	public List<CoreCaiDatHienThi> findByAppCodeAndNguoiSuDungAndMaDanhSach(String appCode, String nguoiSuDung,
			String maDanhSach);
}