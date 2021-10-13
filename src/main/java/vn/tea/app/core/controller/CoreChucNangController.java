package vn.tea.app.core.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

import vn.tea.app.core.dto.CoreChucNangData;
import vn.tea.app.core.entity.CoreChucNang;
import vn.tea.app.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/core/chucnang")
public class CoreChucNangController {
	@Autowired
	private CoreChucNangBusiness coreChucNangBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoreChucNang>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "nhomChucNangId", defaultValue = "-1", required = false) Long nhomChucNangId,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "trangThai", required = false) Integer trangThai) {
		Page<CoreChucNang> pageCoreChucNang = coreChucNangBusiness.findAll(page, size, sortBy, sortDir, search,
				nhomChucNangId, trangThai);
		return ResponseEntity.ok(pageCoreChucNang);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreChucNang> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		CoreChucNang coreChucNang = coreChucNangBusiness.findById(id);
		return ResponseEntity.ok(coreChucNang);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoreChucNang> create(@Valid @RequestBody CoreChucNangData coreChucNangData,
			BindingResult result) throws MethodArgumentNotValidException {
		CoreChucNang coreChucNang = coreChucNangBusiness.create(coreChucNangData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreChucNang);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoreChucNang> update(@PathVariable("id") Long id,
			@Valid @RequestBody CoreChucNangData coreChucNangData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		CoreChucNang coreChucNang = coreChucNangBusiness.update(id, coreChucNangData, result);
		return ResponseEntity.ok(coreChucNang);
	}

	@PostMapping(value = { "/routerdatas" })
	public ResponseEntity<String> create(@Valid @RequestBody Object object)
			throws InterruptedException, ExecutionException {
		String thongBao = "Tiến hành get dữ liệu router";
		try {
			Future<String> future = coreChucNangBusiness.getRouterDatas(object);
			thongBao = "Đang thực hiện get dữ liệu router, vui lòng chờ !";
			while (future.isDone()) {
				thongBao = "Đã hoàn thành get dữ liệu router";
			}
		} catch (Exception e) {
			thongBao = "Có lỗi xảy ra trong quá trình get dữ liệu router";
			System.out.println("ERROR in async thread " + e.getMessage());
		}

		return ResponseEntity.ok(thongBao);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<CoreChucNang> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		CoreChucNang coreChucNang = coreChucNangBusiness.delete(id);
		return ResponseEntity.ok(coreChucNang);
	}

}
