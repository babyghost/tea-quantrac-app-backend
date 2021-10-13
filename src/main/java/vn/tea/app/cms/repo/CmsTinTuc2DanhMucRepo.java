package vn.tea.app.cms.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.cms.entity.CmsTinTuc2DanhMuc;

@Repository
public interface CmsTinTuc2DanhMucRepo extends JpaRepository<CmsTinTuc2DanhMuc, Long>, JpaSpecificationExecutor<CmsTinTuc2DanhMuc> {

	@Transactional
	@Modifying
	@Query("update CmsTinTuc2DanhMuc u set u.daXoa = 1 where u.id = ?1")
	public int setFixedDaXoaById(Long id);
	
	@Transactional
	@Modifying
	@Query("update CmsTinTuc2DanhMuc u set u.daXoa = 1 where u.tinTucId = ?1")
	public int deleteAllByTinTucId(Long tinTucId);
	
	public List<CmsTinTuc2DanhMuc> findByTinTucIdAndDaXoa(Long tinTucId, Integer daXoa);
}
