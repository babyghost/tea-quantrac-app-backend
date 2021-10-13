package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import vn.tea.app.core.entity.CoreUser;

public interface CoreUserService {

	public Optional<CoreUser> findById(Long id);

	public CoreUser save(CoreUser coreUser);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CoreUser> findAll(String email, String hoTen, List<Long> roleIds, Pageable pageable);

	public List<CoreUser> findByEmailIgnoreCaseAndDaXoa(String email, Integer daXoa);

	public List<CoreUser> findByIdAndEmailIgnoreCaseAndDaXoa(long id, String email, Integer daXoa);

	public CoreUser findFirstByUserNameAndDaXoa(String userName, Integer daXoa);

	public CoreUser findFirstByEmailIgnoreCaseAndDaXoa(String email, Integer daXoa);

	public UserDetails loadUserByUserName(String userName);

}
