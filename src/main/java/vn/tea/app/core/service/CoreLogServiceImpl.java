package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.tea.app.core.entity.CoreLog;

@Service
@Transactional
public class CoreLogServiceImpl implements CoreLogService {

	@Autowired
	private CoreLogRepo repo;

	public Optional<CoreLog> findById(Long id) {
		return repo.findById(id);
	}

	public CoreLog save(CoreLog CoreLog) {
		return repo.save(CoreLog);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public List<CoreLog> findByObjectIdAndTypeOrderByPhienBanDesc(Long objectId, Integer type) {
		// TODO Auto-generated method stub
		return repo.findByObjectIdAndTypeOrderByPhienBanDesc(objectId, type);
	}
	
	@Override
	public Page<CoreLog> findAll(String nguoiCapNhat, Long objectId, Integer type, Pageable pageable) {
		return repo.findAll(CoreLogSpecifications.quickSearch(nguoiCapNhat, objectId, type), pageable);
	}
	
	public Integer getMaxPhienBanByObjectIdAndType(Long objectId, Integer type) {
		return repo.getMaxPhienBanByObjectIdAndType(objectId, type);
	}
	
	@Override
	public int setFixedIsPrimary(Integer isPrimary, long objectId, Integer phienBan, Integer type) {
		// TODO Auto-generated method stub
		return repo.setFixedIsPrimary(isPrimary, objectId, phienBan, type);
	}
	

	
}
