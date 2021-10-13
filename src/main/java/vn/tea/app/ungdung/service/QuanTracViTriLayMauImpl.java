package vn.tea.app.ungdung.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.tea.app.ungdung.entity.QuanTracViTriLayMau;
import vn.tea.app.ungdung.repo.QuanTracViTriLayMauRepo;

@Service
public class QuanTracViTriLayMauImpl implements QuanTracViTriLayMauService {

	@Autowired
	private QuanTracViTriLayMauRepo repo;

	public Optional<QuanTracViTriLayMau> findById(Long id) {
		return repo.findById(id);
	}

	public QuanTracViTriLayMau save(QuanTracViTriLayMau coreRole) {
		return repo.save(coreRole);
	}

	public void delete(Long id) {
		repo.setFixedDaXoaById(id);
	}
	public void deleteAllByPhanTichId(Long phanTichId) {
		repo.setFixedDaXoaByPhanTichId(phanTichId);
	}


	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<QuanTracViTriLayMau> findByPhanTichIdAndDaXoa(Long phanTichId, Integer daXoa) {
		return repo.findByPhanTichIdAndDaXoa(phanTichId, daXoa);
	}
	@Override
	public List<QuanTracViTriLayMau> findByViTriLayMauAndDaXoa(String viTriLayMau, Integer daXoa) {
		// TODO Auto-generated method stub
		return repo.findByViTriLayMauAndDaXoa(viTriLayMau, daXoa);
	}
	
	@Override
	public List<QuanTracViTriLayMau> search(Long phanTichId, List<Long> viTriLayMauId) {
		// TODO Auto-generated method stub
		return repo.findAll(QuanTracViTriLayMauSpecifications.quickSearch(phanTichId, viTriLayMauId));
	}
	@Override
	public List<QuanTracViTriLayMau> searcViTriByPhanTich(Long phanTichId, String ten) {
		// TODO Auto-generated method stub
		return repo.findAll(QuanTracViTriLayMauSpecifications.searcViTriByPhanTich(phanTichId, ten));
	}

}
