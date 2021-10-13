package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import vn.tea.app.core.entity.CoreUser;
import vn.tea.app.core.entity.CustomUserDetails;

@Service
public class CoreUserServiceImpl implements CoreUserService {

	@Autowired
	private CoreUserRepo repo;

	public Optional<CoreUser> findById(Long id) {
		return repo.findById(id);
	}

	public CoreUser save(CoreUser coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreUser> findAll(String email, String hoTen, List<Long> roleIds, Pageable pageable) {
		return repo.findAll(CoreUserSpecifications.quickSearch(email, hoTen, roleIds), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CoreUser> findByEmailIgnoreCaseAndDaXoa(String email, Integer daXoa) {
		return repo.findByEmailIgnoreCaseAndDaXoa(email, daXoa);
	}

	public List<CoreUser> findByIdAndEmailIgnoreCaseAndDaXoa(long id, String email, Integer daXoa) {
		return repo.findByIdAndEmailIgnoreCaseAndDaXoa(id, email, daXoa);
	}
	
	public CoreUser findFirstByUserNameAndDaXoa(String userName, Integer daXoa) {
		return repo.findFirstByUserNameAndDaXoa(userName, daXoa);
	}
	@Override
	public CoreUser findFirstByEmailIgnoreCaseAndDaXoa(String email, Integer daXoa) {
		// TODO Auto-generated method stub
		return repo.findFirstByUserNameAndDaXoa(email, daXoa);
	}
	
	@Transactional
    public UserDetails loadUserByUserName(String userName) {
        CoreUser user = repo.findFirstByUserNameAndDaXoa(userName, 0);
        return new CustomUserDetails(user);
    }

}
