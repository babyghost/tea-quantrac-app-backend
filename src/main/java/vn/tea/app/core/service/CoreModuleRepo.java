package vn.tea.app.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tea.app.core.entity.CoreModule;

@Repository
public interface CoreModuleRepo extends JpaRepository<CoreModule, Long>, JpaSpecificationExecutor<CoreModule> {

	public List<CoreModule> findByMaIgnoreCaseAndDaXoa(String ma, boolean daXoa);

	public List<CoreModule> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, boolean daXoa);
}
