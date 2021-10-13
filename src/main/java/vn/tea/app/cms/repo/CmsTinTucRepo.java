package vn.tea.app.cms.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.cms.entity.CmsTinTuc;

@Repository
public interface CmsTinTucRepo extends JpaRepository<CmsTinTuc, Long>, JpaSpecificationExecutor<CmsTinTuc> {

	@Transactional
	@Modifying
	@Query("update CmsTinTuc u set u.daXoa = 1 where u.id = ?1")
	public int setFixedDaXoaById(Long id);
	
	public List<CmsTinTuc> findByTenAndDaXoa(String ten, Integer daXoa);

}
