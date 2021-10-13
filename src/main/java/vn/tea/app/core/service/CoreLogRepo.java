package vn.tea.app.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreLog;

@Repository
public interface CoreLogRepo extends JpaRepository<CoreLog, Long>, JpaSpecificationExecutor<CoreLog> {
	
	public List<CoreLog> findByObjectIdAndTypeOrderByPhienBanDesc(Long objectId, Integer type);

	@Query("UPDATE CoreLog u set u.isPrimary = ?1 where u.objectId = ?2 and u.phienBan < ?3 and u.type = ?4")
	public int setFixedIsPrimary(Integer isPrimary, long objectId, Integer phienBan, Integer type);
	
	@Query(value = "SELECT max(u.phienBan) FROM CoreLog u where u.objectId = ?1 and u.type = ?2")
	public Integer getMaxPhienBanByObjectIdAndType(Long objectId, Integer type);
}
