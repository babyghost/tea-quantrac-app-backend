package vn.tea.app.danhmuc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.danhmuc.entity.DmDonViHanhChinh;

public interface DmDonViHanhChinhService {

	public Optional<DmDonViHanhChinh> findById(Long id);

	public DmDonViHanhChinh save(DmDonViHanhChinh coreUser);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<DmDonViHanhChinh> findAll(String ten, String ma, Pageable pageable);

	public List<DmDonViHanhChinh> findByTenAndDaXoa(String ten, Integer daXoa);

}
