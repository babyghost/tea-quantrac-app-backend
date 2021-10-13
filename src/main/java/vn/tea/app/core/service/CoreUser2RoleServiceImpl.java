package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.tea.app.core.entity.CoreUser2Role;

@Service
@Transactional
public class CoreUser2RoleServiceImpl implements CoreUser2RoleService {
	@Autowired
	private CoreUser2RoleRepo repo;

	public CoreUser2Role save(CoreUser2Role coreUser2Role) {
		return repo.save(coreUser2Role);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Optional<CoreUser2Role> findById(Long id) {
		return repo.findById(id);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CoreUser2Role> findByRoleIdAndDaXoa(Long roleId, Integer daXoa) {
		return repo.findByRoleIdAndDaXoa(roleId, daXoa);
	}

	public List<CoreUser2Role> findByRoleIdAndUserId(Long roleId, Long userId) {
		return repo.findByRoleIdAndUserId(roleId, userId);
	}

	public int setFixedDaXoaForRoleId(Integer daXoa, Long roleId) {
		return repo.setFixedDaXoaForRoleId(daXoa, roleId);
	}

	public List<CoreUser2Role> findByUserIdAndDaXoa(Long userId, Integer daXoa) {
		return repo.findByUserIdAndDaXoa(userId, daXoa);
	}

	public int setFixedDaXoaForUserId(Integer daXoa, Long userId) {
		return repo.setFixedDaXoaForUserId(daXoa, userId);
	}

}
