package vn.tea.app.core.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.tea.app.core.entity.DanhMucDanToc;

public interface DanhMucDanTocRepository extends JpaRepository<DanhMucDanToc, Long>{

	public Optional<DanhMucDanToc> findByMa(String ma);
}
