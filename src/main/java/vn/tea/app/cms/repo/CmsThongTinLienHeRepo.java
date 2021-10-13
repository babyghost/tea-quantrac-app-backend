package vn.tea.app.cms.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.cms.entity.CmsThongTinLienHe;

@Repository
public interface CmsThongTinLienHeRepo extends JpaRepository<CmsThongTinLienHe, Long>, JpaSpecificationExecutor<CmsThongTinLienHe> {

	@Transactional
	@Modifying
	@Query("update CmsThongTinLienHe u set u.daXoa = 1 where u.id = ?1")
	public int setFixedDaXoaById(Long id);
	
	public List<CmsThongTinLienHe> findByTenAndDaXoa(String ten, Integer daXoa);

}
