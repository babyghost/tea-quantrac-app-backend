package vn.tea.app.core.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.core.dto.FileDinhKem;
import vn.tea.app.core.entity.CoreAttachment;
import vn.tea.app.core.entity.FileList;
import vn.tea.app.core.service.CoreAttachmentService;
import vn.tea.app.exceptions.EntityNotFoundException;
import vn.tea.app.utils.CoreUtil;

@CrossOrigin
@RestController
@RequestMapping("/core/attachment")
@Slf4j
public class CoreAttachmentController {
	@Value("${core.attachment.host.download}")
	private String coreAttachmentHostDownload;
	@Value("${core.attachment.path.upload}")
	private String coreAttachmentPathUpload;
	@Value("${core.attachment.path.uploadtemp}")
	private String coreAttachmentPathUploadTemp;
	@Autowired
	private CoreAttachmentService coreAttachmentService;
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<List<FileList>> listAttachment(
			@RequestParam(value = "idList", required = false, defaultValue = "") List<Long> idList,
			@RequestHeader(name = "APP_CODE") String appCode) {
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
		return ResponseEntity.ok(listFile);
	}
	@PostMapping(value = { "/getlist", "" })
	public ResponseEntity<List<FileList>> getListAttachmentByObjectId(
			@RequestParam(value = "objectId", required = false, defaultValue = "") Long objectId,
			@RequestParam(name = "appcode", required = false, defaultValue = "") String appCode) {
		List<CoreAttachment> listCoreAttachment = coreAttachmentService.findByObjectIdAndAppCodeAndDaXoa(objectId, appCode,
				0);
		List<FileList> listFile = new ArrayList<>();
		for (CoreAttachment coreAttachment : listCoreAttachment) {
			FileList fileList = new FileList();
			fileList.setId(coreAttachment.getId());
			fileList.setName(coreAttachment.getFileName());
			fileList.setUrl(coreAttachment.getLink());
			listFile.add(fileList);
		}
		return ResponseEntity.ok(listFile);
	}
	@GetMapping(value = { "/get" })
	public ResponseEntity<FileDinhKem> getAttachments(
			@RequestParam(value = "fileDinhKemId", required = false, defaultValue = "-1") Long fileDinhKemId,
			@RequestParam(value = "appCode", required = true) String appCode,
			@RequestParam(value = "objectId", required = true) Long objectId,
			@RequestParam(value = "type", required = true) Integer type) {

		return ResponseEntity.ok(coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<List<CoreAttachment>> findByIds(@PathVariable("id") List<Long> idList)
			throws EntityNotFoundException {
		List<CoreAttachment> listCoreAttachment = coreAttachmentService.findByIdIn(idList);
		if (listCoreAttachment == null) {
			throw new EntityNotFoundException(CoreAttachment.class, "idList", String.valueOf(idList));
		}
		return ResponseEntity.ok(listCoreAttachment);
	}

	@PostMapping(value = { "/doupload" })
	public ResponseEntity<CoreAttachment> doUpload(// @RequestHeader(name = "APP_CODE") String appCode,
			@RequestParam("uploadfile") MultipartFile uploadfile, Model model) {
		// Get the filename and build the local file path (be sure that the
		// application have write permissions on such directory)
		String fileName = uploadfile.getOriginalFilename();
		CoreAttachment coreAttachment = new CoreAttachment();
		BufferedOutputStream stream = null;
		Path path = Paths.get(this.coreAttachmentPathUploadTemp);
		if (fileName.length() > 0) {
			int month = 1;
			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);
			int year = CoreUtil.getYear();
			month = cal.get(Calendar.MONTH) + 1;
			coreAttachment.setYear(year);
			coreAttachment.setMonth(month);
			coreAttachment.setFileName(fileName);
			coreAttachment.setSize(uploadfile.getSize());
			coreAttachment.setMime(uploadfile.getContentType());
			coreAttachment.setAppCode("");
			coreAttachment.setFolder(this.coreAttachmentPathUploadTemp);
			coreAttachment = coreAttachmentService.save(coreAttachment);
			String code = coreAttachment.getId() + coreAttachment.getFileName()
					+ coreAttachment.getCreatedDate().toString();
			code = DigestUtils.md5Hex(code).toUpperCase();
			coreAttachment.setCode(code);

			String link = this.coreAttachmentHostDownload + "/attachment/download/" + coreAttachment.getCode();
			coreAttachment.setLink(link);
			coreAttachment = coreAttachmentService.save(coreAttachment);
			String filepath = Paths.get(this.coreAttachmentPathUploadTemp, code).toString();
			// Save the file locally
			try {
				Files.createDirectories(path);
				stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
				stream.write(uploadfile.getBytes());
				stream.close();
			} catch (IOException e) {
				log.error("Lỗi xử lý file: ", e.getMessage());
			} finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						log.error("Lỗi xử lý file: ", e.getMessage());
					}
				}
			}

		}
		return ResponseEntity.ok(coreAttachment);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<List<CoreAttachment>> delete(@PathVariable("id") List<Long> idList) {
		List<CoreAttachment> listCoreAttachment = coreAttachmentService.findByIdIn(idList);
		for (CoreAttachment coreAttachment : listCoreAttachment) {
			//coreAttachment.setDaXoa(1);
			coreAttachmentService.delete(coreAttachment); // xóa trực tiếp
		}
		return ResponseEntity.ok(listCoreAttachment);
	}

	@GetMapping(value = "/download/{code}")
	public ResponseEntity<InputStreamResource> download(@PathVariable String code) throws IOException {
		CoreAttachment coreAttachment = coreAttachmentService.findByCode(code);
		String path = Paths.get(String.valueOf(coreAttachment.getFolder() + File.separator + coreAttachment.getCode()))
				.toString();
		File file = new File(path);
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.valueOf(coreAttachment.getMime()));
		respHeaders.setContentLength(file.length());
		respHeaders.setContentDispositionFormData("attachment", coreAttachment.getFileName());
		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
	}
}
