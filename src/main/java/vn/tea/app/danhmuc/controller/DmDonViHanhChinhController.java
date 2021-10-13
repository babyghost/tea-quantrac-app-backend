package vn.tea.app.danhmuc.controller;

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

import vn.tea.app.danhmuc.bussiness.DmDonViHanhChinhBusiness;
import vn.tea.app.danhmuc.dao.DmDonViHanhChinhData;
import vn.tea.app.danhmuc.entity.DmDonViHanhChinh;
import vn.tea.app.exceptions.EntityNotFoundException;

//@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/quantrac/donvihanhchinh")
public class DmDonViHanhChinhController {
	@Autowired
	DmDonViHanhChinhBusiness bussinessDmDonViHanhChinhBusiness;
//	@Autowired
//	AuthenticationManager authenticationManager;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmDonViHanhChinhData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ma", required = false) String ma,
			@RequestParam(name = "ten", required = false) String ten) {
		Page<DmDonViHanhChinhData> pageDmDonViHanhChinh = bussinessDmDonViHanhChinhBusiness.findAll(ten, ma, page, size, sortBy, sortDir);
		return ResponseEntity.ok(pageDmDonViHanhChinh);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<DmDonViHanhChinh> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		DmDonViHanhChinh coreUser = bussinessDmDonViHanhChinhBusiness.findById(id);
		return ResponseEntity.ok(coreUser);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmDonViHanhChinh> create(@Valid @RequestBody DmDonViHanhChinhData coreUserData, BindingResult result)
			throws MethodArgumentNotValidException {
		DmDonViHanhChinh coreUser = bussinessDmDonViHanhChinhBusiness.create(coreUserData);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmDonViHanhChinh> update(@PathVariable("id") Long id, @Valid @RequestBody DmDonViHanhChinhData coreUserData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		DmDonViHanhChinh coreUser = bussinessDmDonViHanhChinhBusiness.update(id, coreUserData);
		return ResponseEntity.ok(coreUser);
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Integer> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Integer result = bussinessDmDonViHanhChinhBusiness.delete(id);
		return ResponseEntity.ok(result);
	}
}
