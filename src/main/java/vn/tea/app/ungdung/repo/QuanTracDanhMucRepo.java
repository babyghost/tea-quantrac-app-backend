package vn.tea.app.ungdung.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.ungdung.entity.QuanTracDanhMuc;

@Repository
public interface QuanTracDanhMucRepo extends JpaRepository<QuanTracDanhMuc, Long>, JpaSpecificationExecutor<QuanTracDanhMuc> {

	@Transactional
	@Modifying
	@Query("update QuanTracDanhMuc u set u.daXoa = 1 where u.id = ?1")
	public int setFixedDaXoaById(Long id);
	
	public List<QuanTracDanhMuc> findByTenAndDaXoa(String ten, Integer daXoa);
	
	public QuanTracDanhMuc findByMaAndDaXoa(String ma, Integer daXoa);

}
