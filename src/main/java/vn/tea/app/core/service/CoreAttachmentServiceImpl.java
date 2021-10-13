package vn.tea.app.core.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.core.entity.CoreAttachment;

@Service
@Transactional
@Slf4j
public class CoreAttachmentServiceImpl implements CoreAttachmentService {
	@Value("${core.attachment.path.upload}")
	String CORE_ATTACHMENT_PATH_UPLOAD;
	@Autowired
	private CoreAttachmentRepo repo;

	public CoreAttachment save(CoreAttachment coreAttachment) {
		return repo.save(coreAttachment);
	}

	public void delete(CoreAttachment coreAttachment) {
		repo.delete(coreAttachment);
	}

	public Optional<CoreAttachment> findById(Long id) {
		return repo.findById(id);
	}

	public CoreAttachment findByCode(String code) {
		return repo.findByCode(code);
	}

	public List<CoreAttachment> findByIdIn(List<Long> idList) {
		return repo.findByIdIn(idList);
	}

	public CoreAttachment saveAndMove(CoreAttachment coreAttachment) {
		try {
			boolean IS_OS_WINDOWS = SystemUtils.IS_OS_WINDOWS;
			Path pathOld, pathNew, folderNew;
			if (IS_OS_WINDOWS == true) {
				folderNew = Paths.get(CORE_ATTACHMENT_PATH_UPLOAD + "\\" + coreAttachment.getYear() + "\\"
						+ coreAttachment.getMonth());
				pathNew = Paths.get(CORE_ATTACHMENT_PATH_UPLOAD + "\\" + coreAttachment.getYear() + "\\"
						+ coreAttachment.getMonth() + "\\" + coreAttachment.getCode());
				pathOld = Paths.get(coreAttachment.getFolder() + "\\" + coreAttachment.getCode());
			} else {
				folderNew = Paths.get(
						CORE_ATTACHMENT_PATH_UPLOAD + "/" + coreAttachment.getYear() + "/" + coreAttachment.getMonth());
				pathNew = Paths.get(CORE_ATTACHMENT_PATH_UPLOAD + "/" + coreAttachment.getYear() + "/"
						+ coreAttachment.getMonth() + "/" + coreAttachment.getCode());
				pathOld = Paths.get(coreAttachment.getFolder() + "/" + coreAttachment.getCode());
			}
			Files.createDirectories(folderNew);
			Files.move(pathOld, pathNew, StandardCopyOption.REPLACE_EXISTING);
			coreAttachment.setFolder(folderNew.toString());
			coreAttachment = repo.save(coreAttachment);
		} catch (Exception e) {
			coreAttachment = null;
			log.error(e.getMessage());
		}
		return coreAttachment;
	}
	
	
	public List<CoreAttachment> findByIdInAndAppCodeAndDaXoa(List<Long> idList, String appCode, int daXoa) {
		return repo.findByIdInAndAppCodeAndDaXoa(idList, appCode, daXoa);
	}
	

	public int setFixedDaXoaForObjectIdAndAppCode(int daXoa, Long objectId, String appCode) {
		return repo.setFixedDaXoaForObjectIdAndAppCode(daXoa, objectId, appCode);
	}

	public int setFixedDaXoaForObjectIdAndAppCodeAndType(int daXoa, Long objectId, String appCode, int type) {
		return repo.setFixedDaXoaForObjectIdAndAppCodeAndType(daXoa, objectId, appCode, type);
	}
	
	@Override
	public int setFixedObjectForAttachmentIdAndAppCode(Long objectId, Long attachmentId, String appCode) {
		// TODO Auto-generated method stub
		return repo.setFixedObjectForAttachmentIdAndAppCode(objectId, attachmentId, appCode);
	}

	public List<CoreAttachment> findByObjectIdAndAppCodeAndDaXoa(Long objectId, String appCode, int daXoa) {
		return repo.findByObjectIdAndAppCodeAndDaXoa(objectId, appCode, daXoa);
	}

	public List<CoreAttachment> findByObjectIdAndAppCodeAndTypeAndDaXoa(Long objectId, String appCode, Integer type,
			int daXoa) {
		return repo.findByObjectIdAndAppCodeAndTypeAndDaXoa(objectId, appCode, type, daXoa);
	}
}
