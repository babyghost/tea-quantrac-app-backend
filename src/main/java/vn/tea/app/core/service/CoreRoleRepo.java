package vn.tea.app.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreRole;

@Repository
public interface CoreRoleRepo extends JpaRepository<CoreRole, Long>, JpaSpecificationExecutor<CoreRole> {

	public List<CoreRole> findByMaIgnoreCaseAndDaXoa(String ma, Integer daXoa);

	public List<CoreRole> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Integer daXoa);
	
	@Query(value = "SELECT * FROM core_role WHERE id in (select role_id from core_user2role where user_id = ?1 and daxoa = 0) ", nativeQuery = true)
	public List<CoreRole> getRoles(long userId);
}
