package vn.tea.app.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.tea.app.core.dto.CoreLogOutput;
import vn.tea.app.core.entity.CoreLog;
import vn.tea.app.exceptions.EntityNotFoundException;

@CrossOrigin
@RestController
@RequestMapping(value = "/core/log")
public class CoreLogController {
	@Autowired
	private CoreLogBusiness coreLogBusiness;

//	@GetMapping(value = { "/", "" })
//	public ResponseEntity<Page<CoreLog>> findAll(
//			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
//			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
//			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
//			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
//			@RequestParam(name = "nhomChucNangId", defaultValue = "-1", required = false) Long nhomChucNangId,
//			@RequestParam(name = "search", required = false) String search,
//			@RequestParam(name = "trangThai", required = false) Integer trangThai) {
//		Page<CoreLog> pageCoreLog = CoreLogBusiness.findAll(page, size, sortBy, sortDir, search,
//				nhomChucNangId, trangThai);
//		return ResponseEntity.ok(pageCoreLog);
//	}
	
	@GetMapping(value = { "/list"})
	public ResponseEntity<Page<CoreLogOutput>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "objectId", defaultValue = "-1", required = false) Long objectId,
			@RequestParam(name = "nguoiCapNhat", defaultValue = "", required = false) String nguoiCapNhat,
			@RequestParam(name = "type", required = false) Integer type) {
		Page<CoreLogOutput> pageCoreLog = coreLogBusiness.findAll(page, size, sortBy, sortDir,
				nguoiCapNhat, objectId, type);
		return ResponseEntity.ok(pageCoreLog);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreLog> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CoreLog CoreLog = coreLogBusiness.findById(id);
		return ResponseEntity.ok(CoreLog);
	}
}
