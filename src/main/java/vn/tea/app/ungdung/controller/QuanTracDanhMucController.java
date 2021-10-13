package vn.tea.app.ungdung.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

import vn.tea.app.exceptions.EntityNotFoundException;
import vn.tea.app.ungdung.bussiness.QuanTracDanhMucBusiness;
import vn.tea.app.ungdung.dao.QuanTracDanhMucData;
import vn.tea.app.ungdung.entity.QuanTracDanhMuc;

//@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/quantrac/danhmuc")
public class QuanTracDanhMucController {
	@Autowired
	QuanTracDanhMucBusiness bussinessQuanTracDanhMucBusiness;
	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<QuanTracDanhMuc>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ma", required = false) String ma,
			@RequestParam(name = "catCode", defaultValue="TAC_DONG", required = false) String catCode,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "donViHanhChinhId", required = false) Long donViHanhChinhId) {
		Page<QuanTracDanhMuc> pageQuanTracDanhMuc = bussinessQuanTracDanhMucBusiness.findAll(ten, donViHanhChinhId, catCode, ma, page, size, sortBy, sortDir);
		return ResponseEntity.ok(pageQuanTracDanhMuc);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<QuanTracDanhMuc> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		QuanTracDanhMuc coreUser = bussinessQuanTracDanhMucBusiness.findById(id);
		return ResponseEntity.ok(coreUser);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<QuanTracDanhMuc> create(@Valid @RequestBody QuanTracDanhMucData coreUserData, BindingResult result)
			throws MethodArgumentNotValidException {
		QuanTracDanhMuc coreUser = bussinessQuanTracDanhMucBusiness.create(coreUserData);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<QuanTracDanhMuc> update(@PathVariable("id") Long id, @Valid @RequestBody QuanTracDanhMucData coreUserData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		QuanTracDanhMuc coreUser = bussinessQuanTracDanhMucBusiness.update(id, coreUserData);
		return ResponseEntity.ok(coreUser);
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Integer> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Integer result = bussinessQuanTracDanhMucBusiness.delete(id);
		return ResponseEntity.ok(result);
	}
}
