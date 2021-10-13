package vn.tea.app.core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.tea.app.core.entity.CoreAttachmentTemp;
@Service
@Transactional
public class CoreAttachmentTempServiceImpl implements CoreAttachmentTempService{
	@Autowired private CoreAttachmentTempRepo repository;
	public Long getCoreAttachmentTempId(){
		CoreAttachmentTemp coreAttachmentTemp = new CoreAttachmentTemp();
		coreAttachmentTemp = repository.save(coreAttachmentTemp);
		return coreAttachmentTemp.getId();
	}
}
