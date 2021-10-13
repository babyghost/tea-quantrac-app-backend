package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import vn.tea.app.core.entity.CoreRole2ChucNang;

public interface CoreRole2ChucNangService {
	public CoreRole2ChucNang save(CoreRole2ChucNang coreRole2ChucNang);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<CoreRole2ChucNang> findById(Long id);

	public List<CoreRole2ChucNang> findByRoleIdAndDaXoa(Long roleId, Integer daXoa);

	public List<CoreRole2ChucNang> findByRoleIdAndChucNangId(Long roleId, Long chucNangId);

	public int setFixedDaXoaForRoleId(Integer daXoa, Long roleId);

	public List<CoreRole2ChucNang> findByChucNangIdAndDaXoa(Long chucNangId, Integer daXoa);

}
