package vn.tea.app.ungdung.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monitorjbl.xlsx.StreamingReader;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.core.entity.CoreAttachment;
import vn.tea.app.core.service.CoreAttachmentService;
import vn.tea.app.exceptions.EntityNotFoundException;
import vn.tea.app.ungdung.bussiness.QuanTracPhanTichMoiTruongBusiness;
import vn.tea.app.ungdung.dao.DuLieuMoiTruongTheoDanhMucData;
import vn.tea.app.ungdung.dao.QuanTracPhanTichMoiTruongData;
import vn.tea.app.ungdung.entity.QuanTracDanhMuc;
import vn.tea.app.ungdung.entity.QuanTracPhanTichMoiTruong;
import vn.tea.app.utils.CoreUtil;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/quantrac/phantichmoitruong")
public class QuanTracPhanTichMoiTruongController {
	@Value("${core.attachment.host.download}")
	private String coreAttachmentHostDownload;
	@Value("${core.attachment.path.upload}")
	private String coreAttachmentPathUpload;
	@Value("${core.attachment.path.uploadtemp}")
	private String coreAttachmentPathUploadTemp;
	@Autowired
	private CoreAttachmentService coreAttachmentService;
	@Autowired
	QuanTracPhanTichMoiTruongBusiness bussinessQuanTracPhanTichMoiTruongBusiness;
	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<QuanTracPhanTichMoiTruong>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuNgayToTrinh", required = false) LocalDate tuNgayToTrinh,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denNgayToTrinh", required = false) LocalDate denNgayToTrinh,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "donViHanhChinhId", required = false) Long donViHanhChinhId) {
		Page<QuanTracPhanTichMoiTruong> pageQuanTracPhanTichMoiTruong = bussinessQuanTracPhanTichMoiTruongBusiness
				.findAll(ten, donViHanhChinhId, tuNgayToTrinh, denNgayToTrinh, page, size, sortBy, sortDir);
		return ResponseEntity.ok(pageQuanTracPhanTichMoiTruong);
	}

	@GetMapping(value = { "/search" })
	public ResponseEntity<List<DuLieuMoiTruongTheoDanhMucData>> search(
			@RequestParam(name = "phanTichId", required = false) Long phanTichId,
			@RequestParam(name = "tacDongId", required = false) Long tacDongId,
			@RequestParam(name = "viTriQuanTracIds", required = false) List<Long> viTriQuanTracIds) {
		List<DuLieuMoiTruongTheoDanhMucData> pageQuanTracPhanTichMoiTruong = bussinessQuanTracPhanTichMoiTruongBusiness
				.search(phanTichId, viTriQuanTracIds, tacDongId);
		return ResponseEntity.ok(pageQuanTracPhanTichMoiTruong);
	}

	@GetMapping(value = { "/vitri/search" })
	public ResponseEntity<List<QuanTracDanhMuc>> searcViTriByPhanTich(
			@RequestParam(name = "phanTichId", required = false) Long phanTichId,
			@RequestParam(name = "ten", required = false) String ten) {
		List<QuanTracDanhMuc> pageQuanTracPhanTichMoiTruong = bussinessQuanTracPhanTichMoiTruongBusiness
				.searcViTriByPhanTich(phanTichId, ten);
		return ResponseEntity.ok(pageQuanTracPhanTichMoiTruong);
	}

	@GetMapping(value = { "/new" })
	public ResponseEntity<QuanTracPhanTichMoiTruongData> phanTichMoiNhat() throws EntityNotFoundException {
		QuanTracPhanTichMoiTruongData coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.phanTichMoiNhat();
		return ResponseEntity.ok(coreUser);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<QuanTracPhanTichMoiTruongData> findById(@PathVariable("id") long id)
			throws EntityNotFoundException {
		QuanTracPhanTichMoiTruongData coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.findById(id);
		return ResponseEntity.ok(coreUser);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<QuanTracPhanTichMoiTruong> create(@Valid @RequestBody QuanTracPhanTichMoiTruongData data,
			BindingResult result) throws MethodArgumentNotValidException {
		QuanTracPhanTichMoiTruong coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.create(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PostMapping(value = { "/copy" })
	public ResponseEntity<QuanTracPhanTichMoiTruong> createCopy(@Valid @RequestBody HashMap<String, Long> data)
			throws MethodArgumentNotValidException {
		QuanTracPhanTichMoiTruong coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.createCopy(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<QuanTracPhanTichMoiTruong> update(@PathVariable("id") Long id,
			@Valid @RequestBody QuanTracPhanTichMoiTruongData data, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		QuanTracPhanTichMoiTruong coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.update(id, data);
		return ResponseEntity.ok(coreUser);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Integer> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Integer result = bussinessQuanTracPhanTichMoiTruongBusiness.delete(id);
		return ResponseEntity.ok(result);
	}

	// LINHLT begin - import data QuanTrac

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

	@PostMapping(value = { "/import" })
	public ResponseEntity<String> importQuanTrac(@RequestParam("uploadfile") MultipartFile uploadfile, @RequestParam("year") int year, Model model) {
		try {
			// Workbook workbook = new XSSFWorkbook(uploadfile.getInputStream());
			Workbook workbook = StreamingReader.builder().rowCacheSize(200).bufferSize(4096)
					.open(uploadfile.getInputStream());
			bussinessQuanTracPhanTichMoiTruongBusiness.importQuanTrac(workbook, year);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi trong quá trình import dữ liệu: " + e.getMessage());
		}

		return ResponseEntity.ok("OK");
	}

}
