package vn.tea.app.core.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.core.entity.CoreMessage;

public interface CoreMessageService {
	public Page<CoreMessage> findAll(Long donViId, Long phongBanId, String nguoiDungId, Pageable pageable);
	public Optional<CoreMessage> findById(Long id);
	public CoreMessage save(CoreMessage CoreNotification);
	public void delete(Long id);
}
