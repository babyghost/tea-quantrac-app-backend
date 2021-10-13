package vn.tea.app.danhmuc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.danhmuc.entity.DmDonViHanhChinh;

@Repository
public interface DmDonViHanhChinhRepo extends JpaRepository<DmDonViHanhChinh, Long>, JpaSpecificationExecutor<DmDonViHanhChinh> {

	@Transactional
	@Modifying
	@Query("update DmDonViHanhChinh u set u.daXoa = 1 where u.id = ?1")
	public int setFixedDaXoaById(Long id);
	
	public List<DmDonViHanhChinh> findByTenAndDaXoa(String ten, Integer daXoa);
	public DmDonViHanhChinh findByMaAndDaXoa(String ma, Integer daXoa);

}
