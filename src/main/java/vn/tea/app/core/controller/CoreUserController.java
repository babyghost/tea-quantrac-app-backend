package vn.tea.app.core.controller;

import java.util.List;

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

import vn.tea.app.core.dto.CoreUserData;
import vn.tea.app.core.entity.CoreUser;
import vn.tea.app.exceptions.EntityNotFoundException;

//@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/core/user")
public class CoreUserController {
	@Autowired
	private CoreUserBusiness coreUserBusiness;
	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoreUserData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "email", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "roleIds", required = false) List<Long> roleIds,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "hoTen", required = false) String hoTen) {
		Page<CoreUserData> pageCoreUser = coreUserBusiness.findAll(page, size, sortBy, sortDir, email, hoTen, roleIds);
		return ResponseEntity.ok(pageCoreUser);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreUserData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CoreUserData coreUser = coreUserBusiness.findById(id);
		return ResponseEntity.ok(coreUser);
	}

	@GetMapping(value = { "/info/{email}" })
	public ResponseEntity<CoreUserData> findInfo(@PathVariable("email") String email) throws EntityNotFoundException {
		CoreUserData coreUser = coreUserBusiness.findUserById(email);
		return ResponseEntity.ok(coreUser);
	}

	@GetMapping(value = { "/email/{email}" })
	public ResponseEntity<CoreUser> findByEmail(@PathVariable("email") String email) {
		CoreUser coreUser = coreUserBusiness.findByEmail(email);
		return ResponseEntity.ok(coreUser);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoreUser> create(@Valid @RequestBody CoreUserData coreUserData, BindingResult result)
			throws MethodArgumentNotValidException {
		CoreUser coreUser = coreUserBusiness.create(coreUserData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoreUser> update(@PathVariable("id") Long id, @Valid @RequestBody CoreUserData coreUserData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		CoreUser coreUser = coreUserBusiness.update(id, coreUserData, result);
		return ResponseEntity.ok(coreUser);
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoreUser> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoreUser coreUser = coreUserBusiness.delete(id);
		return ResponseEntity.ok(coreUser);
	}
}
