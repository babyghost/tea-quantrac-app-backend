package vn.tea.app.cms.service;

import java.util.List;
import java.util.Optional;

import vn.tea.app.cms.entity.CmsTinTuc2DanhMuc;

public interface CmsTinTuc2DanhMucService {

	public Optional<CmsTinTuc2DanhMuc> findById(Long id);

	public CmsTinTuc2DanhMuc save(CmsTinTuc2DanhMuc object);

	public void delete(Long id);

	public boolean existsById(Long id);
	
	public int deleteAllByTinTucId(Long tinTucId);
	
	public List<CmsTinTuc2DanhMuc> findByTinTucIdAndDaXoa(Long tinTucId, Integer daXoa);

}
