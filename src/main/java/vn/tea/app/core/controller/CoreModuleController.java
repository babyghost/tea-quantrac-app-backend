package vn.tea.app.core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.tea.app.core.dto.CoreModuleData;
import vn.tea.app.core.entity.CoreModule;
import vn.tea.app.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/core/module")
public class CoreModuleController {
	@Autowired
	private CoreModuleBusiness coreModuleBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoreModule>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai) {
		Page<CoreModule> pageCoreModule = coreModuleBusiness.findAll(page, size, sortBy, sortDir, search, trangThai);
		return ResponseEntity.ok(pageCoreModule);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreModule> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CoreModule coreModule = coreModuleBusiness.findById(id);
		return ResponseEntity.ok(coreModule);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoreModule> create(@Valid @RequestBody CoreModuleData coreModuleData, BindingResult result)
			throws MethodArgumentNotValidException {
		CoreModule coreModule = coreModuleBusiness.create(coreModuleData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreModule);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoreModule> update(@PathVariable("id") Long id,
			@Valid @RequestBody CoreModuleData coreModuleData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		CoreModule coreModule = coreModuleBusiness.update(id, coreModuleData, result);
		return ResponseEntity.ok(coreModule);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoreModule> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoreModule coreModule = coreModuleBusiness.delete(id);
		return ResponseEntity.ok(coreModule);
	}

}
