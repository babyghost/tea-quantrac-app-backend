package vn.tea.app.cms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import vn.tea.app.cms.bussiness.CmsDanhMucBusiness;
import vn.tea.app.cms.dao.CmsDanhMucData;
import vn.tea.app.cms.entity.CmsDanhMuc;
import vn.tea.app.exceptions.EntityNotFoundException;

//@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/cms/danhmuc")
public class CmsDanhMucController {
	@Autowired
	CmsDanhMucBusiness bussinessCmsDanhMucBusiness;
//	@Autowired
//	AuthenticationManager authenticationManager;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CmsDanhMucData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ma", required = false) String ma,
			@RequestParam(name = "ten", required = false) String ten) {
		Page<CmsDanhMucData> pageCmsDanhMuc = bussinessCmsDanhMucBusiness.findAll(ten, ma, page, size, sortBy, sortDir);
		return ResponseEntity.ok(pageCmsDanhMuc);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CmsDanhMuc> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CmsDanhMuc coreUser = bussinessCmsDanhMucBusiness.findById(id);
		return ResponseEntity.ok(coreUser);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CmsDanhMuc> create(@Valid @RequestBody CmsDanhMucData coreUserData, BindingResult result)
			throws MethodArgumentNotValidException {
		CmsDanhMuc coreUser = bussinessCmsDanhMucBusiness.create(coreUserData);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CmsDanhMuc> update(@PathVariable("id") Long id, @Valid @RequestBody CmsDanhMucData coreUserData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		CmsDanhMuc coreUser = bussinessCmsDanhMucBusiness.update(id, coreUserData);
		return ResponseEntity.ok(coreUser);
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Integer> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Integer result = bussinessCmsDanhMucBusiness.delete(id);
		return ResponseEntity.ok(result);
	}
}
