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
import vn.tea.app.ungdung.bussiness.QuanTracHeSoTrungBinhBusiness;
import vn.tea.app.ungdung.dao.QuanTracHeSoTrungBinhData;
import vn.tea.app.ungdung.entity.QuanTracHeSoTrungBinh;

//@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/quantrac/hesotrungbinh")
public class QuanTracHeSoTrungBinhController {
	@Autowired
	QuanTracHeSoTrungBinhBusiness bussinessQuanTracHeSoTrungBinhBusiness;
	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<QuanTracHeSoTrungBinh>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten) {
		Page<QuanTracHeSoTrungBinh> pageQuanTracHeSoTrungBinh = bussinessQuanTracHeSoTrungBinhBusiness.findAll(ten, page, size, sortBy, sortDir);
		return ResponseEntity.ok(pageQuanTracHeSoTrungBinh);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<QuanTracHeSoTrungBinh> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		QuanTracHeSoTrungBinh coreUser = bussinessQuanTracHeSoTrungBinhBusiness.findById(id);
		return ResponseEntity.ok(coreUser);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<QuanTracHeSoTrungBinh> create(@Valid @RequestBody QuanTracHeSoTrungBinhData coreUserData, BindingResult result)
			throws MethodArgumentNotValidException {
		QuanTracHeSoTrungBinh coreUser = bussinessQuanTracHeSoTrungBinhBusiness.create(coreUserData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<QuanTracHeSoTrungBinh> update(@PathVariable("id") Long id, @Valid @RequestBody QuanTracHeSoTrungBinhData coreUserData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		QuanTracHeSoTrungBinh coreUser = bussinessQuanTracHeSoTrungBinhBusiness.update(id, coreUserData, result);
		return ResponseEntity.ok(coreUser);
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Integer> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Integer result = bussinessQuanTracHeSoTrungBinhBusiness.delete(id);
		return ResponseEntity.ok(result);
	}
}
