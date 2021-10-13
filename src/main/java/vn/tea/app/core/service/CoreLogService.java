package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.core.entity.CoreLog;

public interface CoreLogService {

	public Optional<CoreLog> findById(Long id);

	public CoreLog save(CoreLog CoreLog);

	public void delete(Long id);

	public boolean existsById(Long id);

	public List<CoreLog> findByObjectIdAndTypeOrderByPhienBanDesc(Long objectId, Integer type);
	
	public Page<CoreLog> findAll(String nguoiCapNhat, Long objectId, Integer type, Pageable pageable);
	
	public Integer getMaxPhienBanByObjectIdAndType(Long objectId, Integer type);
	
	public int setFixedIsPrimary(Integer isPrimary, long objectId, Integer phienBan, Integer type);
	


	
	
	

}
