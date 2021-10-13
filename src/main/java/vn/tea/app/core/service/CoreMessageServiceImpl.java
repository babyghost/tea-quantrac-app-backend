package vn.tea.app.core.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.core.entity.CoreMessage;

@Service 
public class CoreMessageServiceImpl implements CoreMessageService {
	@Autowired
	private CoreMessageRepo CoreNotificationRepo;
	
	public Optional<CoreMessage> findById(Long id) {
		return CoreNotificationRepo.findById(id);		
	}
	public CoreMessage save(CoreMessage CoreNotification) {
		return CoreNotificationRepo.save(CoreNotification);
	}
	public void delete(Long id) {
		CoreNotificationRepo.deleteById(id);
	}

	public Page<CoreMessage> findAll(Long donViId,Long phongBanId,String nguoiDungId,Pageable pageable){
		return CoreNotificationRepo.findAll(CoreMessageSpecifications.quickSearch(donViId, phongBanId,nguoiDungId), pageable);
	}


}
