package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.core.entity.CoreRole;

@Service
public class CoreRoleServiceImpl implements CoreRoleService {

	@Autowired
	private CoreRoleRepo repo;

	public Optional<CoreRole> findById(Long id) {
		return repo.findById(id);
	}

	public CoreRole save(CoreRole coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreRole> findAll(String search, Integer trangThai, Pageable pageable) {
		return repo.findAll(CoreRoleSpecifications.quickSearch(search, trangThai), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CoreRole> findByMaIgnoreCaseAndDaXoa(String ma, Integer daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<CoreRole> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Integer daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}
	
	public List<CoreRole> getRoles(long userId) {
		return repo.getRoles(userId);
	}

}
