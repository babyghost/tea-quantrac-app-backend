package vn.tea.app.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreUser2Role;

@Repository
public interface CoreUser2RoleRepo extends JpaRepository<CoreUser2Role, Long>, JpaSpecificationExecutor<CoreUser2Role> {
	public List<CoreUser2Role> findByRoleIdAndDaXoa(Long roleId, Integer daXoa);

	public List<CoreUser2Role> findByRoleIdAndUserId(Long roleId, Long userId);

	@Modifying
	@Query("update CoreUser2Role u set u.daXoa = ?1 where u.roleId = ?2")
	public int setFixedDaXoaForRoleId(Integer daXoa, Long roleId);
	
	@Modifying
	@Query("update CoreUser2Role u set u.daXoa = ?1 where u.userId = ?2")
	public int setFixedDaXoaForUserId(Integer daXoa, Long userId);

	public List<CoreUser2Role> findByUserIdAndDaXoa(Long userId, Integer daXoa);
}
