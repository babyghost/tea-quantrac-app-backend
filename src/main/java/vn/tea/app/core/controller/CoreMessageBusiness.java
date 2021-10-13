package vn.tea.app.core.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import vn.tea.app.core.dto.CoreMessageData;
import vn.tea.app.core.entity.CoreMessage;
import vn.tea.app.core.service.CoreMessageService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
public class CoreMessageBusiness {

	@Autowired
	private CoreMessageService coreMessageService;

	public Page<CoreMessage> findAll(int page, int size, String sortBy, String sortDir, Long donViId, Long phongBanId,
			String nguoiDungId) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoreMessage> pageCoreMessage = coreMessageService.findAll(donViId, phongBanId, nguoiDungId,
				PageRequest.of(page, size, direction, sortBy));
		return pageCoreMessage;
	}

	public CoreMessage findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<CoreMessage> optional = coreMessageService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreMessage.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public CoreMessage create(CoreMessageData modelInput) {
		CoreMessage coreMessage = new CoreMessage();
		coreMessage.setNoiDung(modelInput.getNoiDung());
		coreMessage.setDonViId(modelInput.getDonViId());
		coreMessage.setPhongBanId(modelInput.getPhongBanId());
		coreMessage.setNguoiDungId(modelInput.getNguoiSuDungId());
		// coreMessage.setType(modelInput.getType());
		coreMessage.setIsXemBaoCao(modelInput.getIsXemBaoCao());
		coreMessage.setNgayXemThongBao(modelInput.getNgayXemThongBao());
		coreMessage.setDuongDanUrl(modelInput.getDuongDanUrl());
		coreMessage.setTieuDe(modelInput.getTieuDe());
		coreMessage.setMaModule(modelInput.getMaModule());
		coreMessage = coreMessageService.save(coreMessage);
		return coreMessage;
	}

	public CoreMessage update(Long id, CoreMessageData modelInput) throws EntityNotFoundException {
		Optional<CoreMessage> optional = coreMessageService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreMessage.class, "id", String.valueOf(id));
		}
		CoreMessage coreMessage = optional.get();
		coreMessage.setNoiDung(modelInput.getNoiDung());
		coreMessage.setDonViId(modelInput.getDonViId());
		coreMessage.setPhongBanId(modelInput.getPhongBanId());
		coreMessage.setNguoiDungId(modelInput.getNguoiSuDungId());
		// coreMessage.setType(modelInput.getType());
		coreMessage.setIsXemBaoCao(modelInput.getIsXemBaoCao());
		coreMessage.setNgayXemThongBao(modelInput.getNgayXemThongBao());
		coreMessage.setDuongDanUrl(modelInput.getDuongDanUrl());
		coreMessage.setTieuDe(modelInput.getTieuDe());
		coreMessage.setMaModule(modelInput.getMaModule());
		coreMessage = coreMessageService.save(coreMessage);
		return coreMessage;
	}

	public void delete(Long id) throws EntityNotFoundException {
		Optional<CoreMessage> optional = coreMessageService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreMessage.class, "id", String.valueOf(id));
		}
		coreMessageService.delete(optional.get().getId());
	}

}