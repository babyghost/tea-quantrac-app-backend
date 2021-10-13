package vn.tea.app.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.cms.entity.CmsDanhMuc;

public interface CmsDanhMucService {

	public Optional<CmsDanhMuc> findById(Long id);

	public CmsDanhMuc save(CmsDanhMuc coreUser);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CmsDanhMuc> findAll(String ten, String ma, Pageable pageable);

	public List<CmsDanhMuc> findByTenAndDaXoa(String ten, Integer daXoa);

}
