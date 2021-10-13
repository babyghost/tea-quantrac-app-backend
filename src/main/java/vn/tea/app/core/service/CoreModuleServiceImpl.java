package vn.tea.app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.tea.app.core.entity.CoreModule;

@Service
public class CoreModuleServiceImpl implements CoreModuleService {

	@Autowired
	private CoreModuleRepo repo;

	public Optional<CoreModule> findById(Long id) {
		return repo.findById(id);
	}

	public CoreModule save(CoreModule coreModule) {
		return repo.save(coreModule);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreModule> findAll(String search, Boolean trangThai, Pageable pageable) {
		return repo.findAll(CoreModuleSpecifications.quickSearch(search, trangThai), pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CoreModule> findByMaIgnoreCaseAndDaXoa(String ma, boolean daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<CoreModule> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, boolean daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

}
