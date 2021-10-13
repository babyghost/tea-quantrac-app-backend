package vn.tea.app.core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.tea.app.core.dto.CoreMessageData;
import vn.tea.app.core.entity.CoreMessage;
import vn.tea.app.exceptions.EntityNotFoundException;

@RestController
@RequestMapping(value = "/core/message")
public class CoreMessageController {
	@Autowired
	private CoreMessageBusiness coreMessageBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<CoreMessage>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "donViId", defaultValue = "", required = false) Long donViId,
			@RequestParam(name = "phongBanId", defaultValue = "", required = false) Long phongBanId,
			@RequestParam(name = "nguoiDungId", defaultValue = "", required = false) String nguoiDungId) {
		Page<CoreMessage> pageList = coreMessageBusiness.findAll(page, size, sortBy, sortDir, donViId, phongBanId,
				nguoiDungId);
		return ResponseEntity.ok(pageList);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<CoreMessage> findById(@PathVariable("id") long id) throws EntityNotFoundException {
		return ResponseEntity.ok(coreMessageBusiness.findById(id));
	}

	@PostMapping(value = { "" })
	public ResponseEntity<CoreMessage> create(@Valid @RequestBody CoreMessageData coreMessageData) {
		CoreMessage coreMessage = coreMessageBusiness.create(coreMessageData);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreMessage);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<CoreMessage> update(@PathVariable("id") Long id,
			@Valid @RequestBody CoreMessageData coreMessageData) throws EntityNotFoundException {
		CoreMessage coreMessage = coreMessageBusiness.update(id, coreMessageData);
		return ResponseEntity.ok(coreMessage);
	}

	@DeleteMapping(value = { "/{id}" })
	public void delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		coreMessageBusiness.delete(id);
	}

}
