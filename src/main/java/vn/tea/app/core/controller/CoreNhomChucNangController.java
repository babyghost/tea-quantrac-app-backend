package vn.tea.app.core.controller;

import java.util.List;

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

import vn.tea.app.core.dto.CoreNhomChucNangData;
import vn.tea.app.core.entity.CoreNhomChucNang;
import vn.tea.app.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/core/nhomchucnang")
public class CoreNhomChucNangController {
	@Autowired
	private CoreNhomChucNangBusiness coreNhomChucNangBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoreNhomChucNang>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai", required = false) Integer trangThai,
			@RequestParam(name = "id", defaultValue = "-1", required = false) Long id,
			@RequestParam(name = "nhomChucNangChaId", defaultValue = "-1", required = false) Long nhomChucNangChaId) {
		Page<CoreNhomChucNang> pageCoreNhomChucNang = coreNhomChucNangBusiness.findAll(page, size, sortBy, sortDir,
				search, trangThai, id, nhomChucNangChaId);
		return ResponseEntity.ok(pageCoreNhomChucNang);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreNhomChucNangData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CoreNhomChucNangData coreNhomChucNangData = coreNhomChucNangBusiness.findById(id);
		return ResponseEntity.ok(coreNhomChucNangData);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoreNhomChucNang> create(@Valid @RequestBody CoreNhomChucNangData coreNhomChucNangData,
			BindingResult result) throws MethodArgumentNotValidException {
		CoreNhomChucNang coreNhomChucNang = coreNhomChucNangBusiness.create(coreNhomChucNangData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreNhomChucNang);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoreNhomChucNang> update(@PathVariable("id") Long id,
			@Valid @RequestBody CoreNhomChucNangData coreNhomChucNangData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		CoreNhomChucNang coreNhomChucNang = coreNhomChucNangBusiness.update(id, coreNhomChucNangData, result);
		return ResponseEntity.ok(coreNhomChucNang);
	}

	@GetMapping(value = { "/checkma" })
	public ResponseEntity<List<CoreNhomChucNang>> findById(@RequestParam(name = "ma", required = true) String ma) {
		return ResponseEntity.ok(coreNhomChucNangBusiness.checkMaExit(ma));
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoreNhomChucNang> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoreNhomChucNang coreNhomChucNang = coreNhomChucNangBusiness.delete(id);
		return ResponseEntity.ok(coreNhomChucNang);
	}

}
