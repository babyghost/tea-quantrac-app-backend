package vn.tea.app.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.tea.app.cms.entity.CmsTinTuc2DanhMuc;
import vn.tea.app.cms.repo.CmsTinTuc2DanhMucRepo;

@Service
public class CmsTinTuc2DanhMucImpl implements CmsTinTuc2DanhMucService {

	@Autowired
	private CmsTinTuc2DanhMucRepo repo;

	public Optional<CmsTinTuc2DanhMuc> findById(Long id) {
		return repo.findById(id);
	}

	public CmsTinTuc2DanhMuc save(CmsTinTuc2DanhMuc object) {
		return repo.save(object);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public int deleteAllByTinTucId(Long tinTucId) {
		// TODO Auto-generated method stub
		return repo.deleteAllByTinTucId(tinTucId);
	}

	@Override
	public List<CmsTinTuc2DanhMuc> findByTinTucIdAndDaXoa(Long tinTucId, Integer daXoa) {
		// TODO Auto-generated method stub
		return repo.findByTinTucIdAndDaXoa(tinTucId, daXoa);
	}
}
