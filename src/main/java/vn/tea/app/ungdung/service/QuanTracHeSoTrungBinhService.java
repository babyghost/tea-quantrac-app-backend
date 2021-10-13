package vn.tea.app.ungdung.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.ungdung.entity.QuanTracHeSoTrungBinh;

public interface QuanTracHeSoTrungBinhService {

	public Optional<QuanTracHeSoTrungBinh> findById(Long id);

	public QuanTracHeSoTrungBinh save(QuanTracHeSoTrungBinh coreUser);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<QuanTracHeSoTrungBinh> findAll(String ten, Pageable pageable);

	public List<QuanTracHeSoTrungBinh> findByTenAndDaXoa(String ten, Integer daXoa);

}
