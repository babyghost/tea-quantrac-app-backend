package vn.tea.app.core.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.tea.app.core.entity.DanhMucTonGiao;

public interface DanhMucTonGiaoRepository extends JpaRepository<DanhMucTonGiao, Long>{

	public Optional<DanhMucTonGiao> findByMa(String ma);
}
