package vn.tea.app.cms.controller;

import java.util.List;

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

import vn.tea.app.cms.bussiness.CmsTinTucBusiness;
import vn.tea.app.cms.dao.CmsTinTucData;
import vn.tea.app.cms.dao.CmsTinTucDataPublic;
import vn.tea.app.cms.entity.CmsTinTuc;
import vn.tea.app.exceptions.EntityNotFoundException;

//@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/cms/tintuc")
public class CmsTinTucController {
	@Autowired
	CmsTinTucBusiness bussinessCmsTinTucBusiness;
//	@Autowired
//	AuthenticationManager authenticationManager;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CmsTinTucData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten) {
		Page<CmsTinTucData> pageCmsTinTuc = bussinessCmsTinTucBusiness.findAll(ten, page, size, sortBy, sortDir);
		return ResponseEntity.ok(pageCmsTinTuc);
	}
	@GetMapping(value = { "/danhsach"})
	public ResponseEntity<Page<CmsTinTucDataPublic>> danhSachTinTucPublic(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "danhMucIds", required = false) List<Long> danhMucIds,
			@RequestParam(name = "search", required = false) String search) {
		Page<CmsTinTucDataPublic> pageCmsTinTuc = bussinessCmsTinTucBusiness.danhSachTinTucPublic(search, danhMucIds, page, size, sortBy, sortDir);
		return ResponseEntity.ok(pageCmsTinTuc);
	}
	
	@GetMapping(value = { "/chitiet/{id}" })
	public ResponseEntity<CmsTinTucDataPublic> chiTietTinTucPublic(@PathVariable("id") long id) throws EntityNotFoundException {
		CmsTinTucDataPublic coreUser = bussinessCmsTinTucBusiness.chiTietPublic(id);
		return ResponseEntity.ok(coreUser);
	}
	
	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CmsTinTucData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CmsTinTucData coreUser = bussinessCmsTinTucBusiness.findById(id);
		return ResponseEntity.ok(coreUser);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CmsTinTuc> create(@Valid @RequestBody CmsTinTucData coreUserData, BindingResult result)
			throws MethodArgumentNotValidException {
		CmsTinTuc coreUser = bussinessCmsTinTucBusiness.create(coreUserData);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CmsTinTuc> update(@PathVariable("id") Long id, @Valid @RequestBody CmsTinTucData coreUserData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		CmsTinTuc coreUser = bussinessCmsTinTucBusiness.update(id, coreUserData);
		return ResponseEntity.ok(coreUser);
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Integer> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Integer result = bussinessCmsTinTucBusiness.delete(id);
		return ResponseEntity.ok(result);
	}
}
