package vn.tea.app.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreUser;

@Repository
public interface CoreUserRepo extends JpaRepository<CoreUser, Long>, JpaSpecificationExecutor<CoreUser> {

	public List<CoreUser> findByEmailIgnoreCaseAndDaXoa(String email, Integer daXoa);

	public List<CoreUser> findByIdAndEmailIgnoreCaseAndDaXoa(long id, String email, Integer daXoa);
	
	public CoreUser findFirstByUserNameAndDaXoa(String userName, Integer daXoa);

}
