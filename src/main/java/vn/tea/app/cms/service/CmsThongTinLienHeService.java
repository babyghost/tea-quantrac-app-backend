package vn.tea.app.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.cms.entity.CmsThongTinLienHe;

public interface CmsThongTinLienHeService {

	public Optional<CmsThongTinLienHe> findById(Long id);

	public CmsThongTinLienHe save(CmsThongTinLienHe coreUser);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CmsThongTinLienHe> findAll(String ten, Pageable pageable);

	public List<CmsThongTinLienHe> findByTenAndDaXoa(String ten, Integer daXoa);

}
