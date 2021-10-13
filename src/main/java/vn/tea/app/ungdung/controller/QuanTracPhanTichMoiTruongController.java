package vn.tea.app.ungdung.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
import vn.tea.app.ungdung.bussiness.QuanTracPhanTichMoiTruongBusiness;
import vn.tea.app.ungdung.dao.DuLieuMoiTruongTheoDanhMucData;
import vn.tea.app.ungdung.dao.QuanTracPhanTichMoiTruongData;
import vn.tea.app.ungdung.entity.QuanTracDanhMuc;
import vn.tea.app.ungdung.entity.QuanTracPhanTichMoiTruong;

//@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/quantrac/phantichmoitruong")
public class QuanTracPhanTichMoiTruongController {
	@Autowired
	QuanTracPhanTichMoiTruongBusiness bussinessQuanTracPhanTichMoiTruongBusiness;
	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<QuanTracPhanTichMoiTruong>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "tuNgayToTrinh", required = false) LocalDate tuNgayToTrinh,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "denNgayToTrinh", required = false) LocalDate denNgayToTrinh,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "donViHanhChinhId", required = false) Long donViHanhChinhId) {
		Page<QuanTracPhanTichMoiTruong> pageQuanTracPhanTichMoiTruong = bussinessQuanTracPhanTichMoiTruongBusiness.findAll(ten, donViHanhChinhId, tuNgayToTrinh, denNgayToTrinh, page, size, sortBy, sortDir);
		return ResponseEntity.ok(pageQuanTracPhanTichMoiTruong);
	}
	
	@GetMapping(value = { "/search"})
	public ResponseEntity<List<DuLieuMoiTruongTheoDanhMucData>> search(
			@RequestParam(name = "phanTichId", required = false) Long phanTichId,
			@RequestParam(name = "tacDongId", required = false) Long tacDongId,
			@RequestParam(name = "viTriQuanTracIds", required = false) List<Long> viTriQuanTracIds) {
		List<DuLieuMoiTruongTheoDanhMucData> pageQuanTracPhanTichMoiTruong = bussinessQuanTracPhanTichMoiTruongBusiness.search(phanTichId, viTriQuanTracIds, tacDongId);
		return ResponseEntity.ok(pageQuanTracPhanTichMoiTruong);
	}
	@GetMapping(value = { "/vitri/search"})
	public ResponseEntity<List<QuanTracDanhMuc>> searcViTriByPhanTich(
			@RequestParam(name = "phanTichId", required = false) Long phanTichId,
			@RequestParam(name = "ten", required = false) String ten) {
		List<QuanTracDanhMuc> pageQuanTracPhanTichMoiTruong = bussinessQuanTracPhanTichMoiTruongBusiness.searcViTriByPhanTich(phanTichId, ten);
		return ResponseEntity.ok(pageQuanTracPhanTichMoiTruong);
	}
	@GetMapping(value = { "/new" })
	public ResponseEntity<QuanTracPhanTichMoiTruongData> phanTichMoiNhat() throws EntityNotFoundException {
		QuanTracPhanTichMoiTruongData coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.phanTichMoiNhat();
		return ResponseEntity.ok(coreUser);
	}
	
	@GetMapping(value = { "/{id}" })
	public ResponseEntity<QuanTracPhanTichMoiTruongData> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		QuanTracPhanTichMoiTruongData coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.findById(id);
		return ResponseEntity.ok(coreUser);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<QuanTracPhanTichMoiTruong> create(@Valid @RequestBody QuanTracPhanTichMoiTruongData data, BindingResult result)
			throws MethodArgumentNotValidException {
		QuanTracPhanTichMoiTruong coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.create(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}
	
	@PostMapping(value = { "/copy" })
	public ResponseEntity<QuanTracPhanTichMoiTruong> createCopy(@Valid @RequestBody HashMap<String, Long> data)
			throws MethodArgumentNotValidException {
		QuanTracPhanTichMoiTruong coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.createCopy(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<QuanTracPhanTichMoiTruong> update(@PathVariable("id") Long id, @Valid @RequestBody QuanTracPhanTichMoiTruongData data,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		QuanTracPhanTichMoiTruong coreUser = bussinessQuanTracPhanTichMoiTruongBusiness.update(id, data);
		return ResponseEntity.ok(coreUser);
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Integer> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		Integer result = bussinessQuanTracPhanTichMoiTruongBusiness.delete(id);
		return ResponseEntity.ok(result);
	}
}
