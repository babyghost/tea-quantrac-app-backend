package vn.tea.app.ungdung.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.ungdung.entity.QuanTracHeSoTrungBinh;

@Repository
public interface QuanTracHeSoTrungBinhRepo extends JpaRepository<QuanTracHeSoTrungBinh, Long>, JpaSpecificationExecutor<QuanTracHeSoTrungBinh> {

	@Transactional
	@Modifying
	@Query("update QuanTracHeSoTrungBinh u set u.daXoa = 1 where u.id = ?1")
	public int setFixedDaXoaById(Long id);
	
	public List<QuanTracHeSoTrungBinh> findByTenAndDaXoa(String ten, Integer daXoa);

}
