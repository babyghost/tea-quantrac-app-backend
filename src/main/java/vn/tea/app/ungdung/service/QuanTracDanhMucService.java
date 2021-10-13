package vn.tea.app.ungdung.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.ungdung.entity.QuanTracDanhMuc;

public interface QuanTracDanhMucService {

	public Optional<QuanTracDanhMuc> findById(Long id);

	public QuanTracDanhMuc save(QuanTracDanhMuc coreUser);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<QuanTracDanhMuc> findAll(String ten, Long donViHanhChinhId, String catCode, String ma, Pageable pageable);

	public List<QuanTracDanhMuc> findByTenAndDaXoa(String ten, Integer daXoa);

}
