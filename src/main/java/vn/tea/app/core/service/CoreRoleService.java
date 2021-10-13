package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.core.entity.CoreRole;

public interface CoreRoleService {

	public Optional<CoreRole> findById(Long id);

	public CoreRole save(CoreRole coreRole);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CoreRole> findAll(String search, Integer trangThai, Pageable pageable);

	public List<CoreRole> findByMaIgnoreCaseAndDaXoa(String ma, Integer daXoa);

	public List<CoreRole> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Integer daXoa);
	
	public List<CoreRole> getRoles(long userId);

}
