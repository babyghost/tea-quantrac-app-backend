package vn.tea.app.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import vn.tea.app.core.dto.FileDinhKem;
import vn.tea.app.core.entity.CoreAttachment;
import vn.tea.app.core.entity.FileList;
import vn.tea.app.core.service.CoreAttachmentService;
import vn.tea.app.utils.Constants;

@Service
public class CoreAttachmentBusiness {

	@Autowired
	private CoreAttachmentService coreAttachmentService;

	public List<FileList> listAttachment(
			@RequestParam(value = "appCode", required = false, defaultValue = "") String appCode,
			@RequestParam(value = "idList", required = false, defaultValue = "") List<Long> idList) {
		List<CoreAttachment> listCoreAttachment = coreAttachmentService.findByIdInAndAppCodeAndDaXoa(idList, appCode,
				0);
		List<FileList> listFile = new ArrayList<>();
		for (CoreAttachment coreAttachment : listCoreAttachment) {
			FileList fileList = new FileList();
			fileList.setId(coreAttachment.getId());
			fileList.setName(coreAttachment.getFileName());
			fileList.setUrl(coreAttachment.getLink());
			listFile.add(fileList);
		}
		return listFile;
	}

	public FileDinhKem getAttachments(Long fileDinhKemId, String appCode, Long objectId, Integer type) {
		FileDinhKem fileDinhKem = new FileDinhKem();
		List<FileList> fileLists = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			List<CoreAttachment> coreAttachments = coreAttachmentService
					.findByObjectIdAndAppCodeAndTypeAndDaXoa(objectId, appCode, type, Constants.CHUA_XOA);
			if (coreAttachments != null && !coreAttachments.isEmpty()) {
				for (CoreAttachment coreAttachment : coreAttachments) {
					FileList fileList = new FileList();
					fileList.setId(coreAttachment.getId());
					fileList.setName(coreAttachment.getFileName());
					fileList.setUrl(coreAttachment.getLink());
					ids.add(coreAttachment.getId());
					fileLists.add(fileList);
				}
			}
		} else if (type == Constants.DINH_KEM_1_FILE && Objects.nonNull(fileDinhKemId)) {
			Optional<CoreAttachment> optionalAttachment = coreAttachmentService.findById(fileDinhKemId);
			if (optionalAttachment.isPresent()) {
				FileList fileList = new FileList();
				fileList.setId(optionalAttachment.get().getId());
				fileList.setName(optionalAttachment.get().getFileName());
				fileList.setUrl(optionalAttachment.get().getLink());
				ids.add(optionalAttachment.get().getId());
				fileLists.add(fileList);
			}
		}
		fileDinhKem.setIds(ids);
		fileDinhKem.setFileLists(fileLists);
		return fileDinhKem;
	}

	public CoreAttachment dinhKemFile(Long fileDinhKemId, Long objectId, int type, String appCode) {
		CoreAttachment coreAttachment = new CoreAttachment();
		Optional<CoreAttachment> optionalCoreAttachment = coreAttachmentService.findById(fileDinhKemId);
		if (optionalCoreAttachment.isPresent()) {
			coreAttachment = optionalCoreAttachment.get();
		}
		coreAttachment.setDaXoa(Constants.CHUA_XOA);
		coreAttachment.setAppCode(appCode);
		coreAttachment.setObjectId(objectId);
		coreAttachment.setType(type);
		coreAttachmentService.save(coreAttachment);
		return coreAttachment;
	}

	public void setFixDaXoaByObjectIdAndAppCodeAndType(Long objectId, String appCode, int type) {
		coreAttachmentService.setFixedDaXoaForObjectIdAndAppCodeAndType(Constants.DA_XOA, objectId, appCode, type);
	}

	public CoreAttachment saveAndMove(CoreAttachment coreAttachment) {
		coreAttachment = coreAttachmentService.saveAndMove(coreAttachment);
		return coreAttachment;
	}

}
