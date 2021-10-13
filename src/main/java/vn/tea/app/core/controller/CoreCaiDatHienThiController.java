package vn.tea.app.core.controller;
//package vn.tea.core.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import vn.tea.core.data.CoreCaiDatHienThiInput;
//import vn.tea.core.data.DanhSachHienThiInput;
//import vn.tea.core.model.CoreCaiDatHienThi;
//import vn.tea.exceptions.EntityNotFoundException;
//
//@RestController
//@RequestMapping(value = "/core/caidathienthi")
////@Slf4j
//public class CoreCaiDatHienThiController {
//	@Autowired
//	private CoreCaiDatHienThiBusiness caiDatHienThiBusiness;
//
//	@GetMapping(value = { "/", "" })
//	public ResponseEntity<Page<CoreCaiDatHienThi>> findAll(//@RequestHeader(name = "APP_CODE") String appCode,
//			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
//			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
//			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
//			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
//			@RequestParam(name = "maDanhSach", defaultValue = "maDanhSach", required = false) String maDanhSach) {
//		Page<CoreCaiDatHienThi> pageList = caiDatHienThiBusiness.findAll(page, size, sortBy, sortDir, maDanhSach);
//		return ResponseEntity.ok(pageList);
//	}
//
//	@GetMapping(value = {"/{id}"})
//	public ResponseEntity<CoreCaiDatHienThi> findOne(@PathVariable("id") long id) throws EntityNotFoundException {
//		return ResponseEntity.ok(caiDatHienThiBusiness.findById(id));
//	}
//
//	@PostMapping(value = { "" })
//	public ResponseEntity<CoreCaiDatHienThi> create(
//			@Valid @RequestBody CoreCaiDatHienThiInput modelInput) {
//		CoreCaiDatHienThi model = caiDatHienThiBusiness.create("", modelInput);
//		return ResponseEntity.status(HttpStatus.CREATED).body(model);
//	}
//
//	@PutMapping(value = { "/{id}" })
//	public ResponseEntity<CoreCaiDatHienThi> update(
//			@PathVariable("id") Long id, @Valid @RequestBody CoreCaiDatHienThiInput modelInput)
//			throws EntityNotFoundException {
//		CoreCaiDatHienThi model = caiDatHienThiBusiness.update("", id, modelInput);
//		return ResponseEntity.ok(model);
//	}
//
//	@PutMapping(value = { "/caidat" })
//	public ResponseEntity<List<CoreCaiDatHienThi>> caidat(
//			@RequestBody DanhSachHienThiInput danhSachHienThiInput) throws EntityNotFoundException {
//		List<CoreCaiDatHienThi> listCoreCaiDatHienThi = caiDatHienThiBusiness.caiDatDanhSach("",
//				danhSachHienThiInput);
//		return ResponseEntity.ok(listCoreCaiDatHienThi);
//	}
//
//	/*
//	 * @DeleteMapping(value = { "/{id}" }) public ResponseEntity<CoreCaiDatHienThi>
//	 * delete(@RequestHeader(name = "APP_CODE") String appCode, @PathVariable("id")
//	 * Long id) throws EntityNotFoundException { return
//	 * ResponseEntity.ok(caiDatHienThiBusiness.delete(appCode, id)); }
//	 */
//
//}
