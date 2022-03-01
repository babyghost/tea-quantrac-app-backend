package vn.tea.app.ungdung.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.ungdung.entity.QuanTracViTriLayMau;

@Repository
public interface QuanTracViTriLayMauRepo extends JpaRepository<QuanTracViTriLayMau, Long>, JpaSpecificationExecutor<QuanTracViTriLayMau> {

	@Transactional
	@Modifying
	@Query("update QuanTracViTriLayMau u set u.daXoa = 1 where u.id = ?1")
	public int setFixedDaXoaById(Long id);
	
	@Transactional
	@Modifying
	@Query("update QuanTracViTriLayMau u set u.daXoa = 1 where u.phanTichId = ?1")
	public int setFixedDaXoaByPhanTichId(Long phanTichId);
	
	public List<QuanTracViTriLayMau> findByViTriLayMauAndDaXoa(String viTriLayMau, Integer daXoa);
	
	public List<QuanTracViTriLayMau> findByPhanTichIdAndDaXoa(Long phanTichId, Integer daXoa);
	
	public QuanTracViTriLayMau findFirstByViTriQuanTracIdAndThangLayMauAndNamLayMau(Long viTriQuanTracId, Integer thangLayMau, Integer namLayMau);

}
