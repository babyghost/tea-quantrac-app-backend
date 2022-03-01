package vn.tea.app.ungdung.service;

import java.util.List;
import java.util.Optional;

import vn.tea.app.ungdung.entity.QuanTracViTriLayMau;

public interface QuanTracViTriLayMauService {

	public Optional<QuanTracViTriLayMau> findById(Long id);

	public QuanTracViTriLayMau save(QuanTracViTriLayMau viTri);

	public void delete(Long id);
	
	public void deleteAllByPhanTichId(Long phanTichId);

	public boolean existsById(Long id);

	public List<QuanTracViTriLayMau> findByViTriLayMauAndDaXoa(String viTriLayMau, Integer daXoa);
	
	public List<QuanTracViTriLayMau> findByPhanTichIdAndDaXoa(Long phanTichId, Integer daXoa);
	public List<QuanTracViTriLayMau> search(Long phanTichId, List<Long> viTriLayMauId);
	public List<QuanTracViTriLayMau> searcViTriByPhanTich(Long phanTichId, String ten);
	
	public QuanTracViTriLayMau findFirstByViTriQuanTracIdAndThangLayMauAndNamLayMau(Long viTriQuanTracId, Integer thangLayMau, Integer namLayMau);
}
