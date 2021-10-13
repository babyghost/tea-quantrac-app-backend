package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.tea.app.core.entity.CoreModule;

public interface CoreModuleService {

	public Optional<CoreModule> findById(Long id);

	public CoreModule save(CoreModule coreModule);

	public void delete(Long id);

	public boolean existsById(Long id);

	public Page<CoreModule> findAll(String search, Boolean trangThai, Pageable pageable);

	public List<CoreModule> findByMaIgnoreCaseAndDaXoa(String ma, boolean daXoa);

	public List<CoreModule> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, boolean daXoa);

}
