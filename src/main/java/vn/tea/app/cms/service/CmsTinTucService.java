package vn.tea.app.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.cms.entity.CmsDanhMuc;
import vn.tea.app.cms.entity.CmsTinTuc;

public interface CmsTinTucService {

	public Optional<CmsTinTuc> findById(Long id);

	public CmsTinTuc save(CmsTinTuc coreUser);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CmsTinTuc> findAll(String ten, Pageable pageable);

	public List<CmsTinTuc> findByTenAndDaXoa(String ten, Integer daXoa);

	public Page<CmsTinTuc> danhSachTinTucPublic(String search, List<Long> danhMucIds, Pageable pageable);
}
