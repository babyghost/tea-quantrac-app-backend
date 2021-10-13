package vn.tea.app.ungdung.bussiness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.danhmuc.entity.DmDonViHanhChinh;
import vn.tea.app.danhmuc.service.DmDonViHanhChinhService;
import vn.tea.app.exceptions.EntityNotFoundException;
import vn.tea.app.ungdung.dao.DuLieuMoiTruongForMapData;
import vn.tea.app.ungdung.dao.DuLieuMoiTruongTheoDanhMucData;
import vn.tea.app.ungdung.dao.QuanTracPhanTichMoiTruongData;
import vn.tea.app.ungdung.dao.QuanTracViTriLayMauData;
import vn.tea.app.ungdung.entity.QuanTracDanhMuc;
import vn.tea.app.ungdung.entity.QuanTracHeSoTrungBinh;
import vn.tea.app.ungdung.entity.QuanTracPhanTichMoiTruong;
import vn.tea.app.ungdung.entity.QuanTracViTriLayMau;
import vn.tea.app.ungdung.service.QuanTracDanhMucService;
import vn.tea.app.ungdung.service.QuanTracHeSoTrungBinhService;
import vn.tea.app.ungdung.service.QuanTracPhanTichMoiTruongService;
import vn.tea.app.ungdung.service.QuanTracViTriLayMauService;

@Service
@Slf4j
public class QuanTracPhanTichMoiTruongBusiness {

	@Autowired
	private QuanTracPhanTichMoiTruongService coreChucNangService;
	@Autowired
	private QuanTracViTriLayMauService serviceQuanTracViTriLayMauService; 
	@Autowired
	private DmDonViHanhChinhService serviceDmDonViHanhChinhService;
	@Autowired
	private QuanTracDanhMucService serviceQuanTracDanhMucService;
	@Autowired
	private QuanTracHeSoTrungBinhService serviceQuanTracHeSoTrungBinhService;
	
	public Page<QuanTracPhanTichMoiTruong> findAll(String ten, Long donViHanhChinhId, LocalDate ngayTaoTuNgay, LocalDate ngayTaoDenNgay, int page, int size, String sortBy, String sortDir) {

		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<QuanTracPhanTichMoiTruong> pageQuanTracPhanTichMoiTruong = coreChucNangService.findAll(ten, donViHanhChinhId, ngayTaoTuNgay, ngayTaoDenNgay, PageRequest.of(page, size, direction, sortBy));
		return pageQuanTracPhanTichMoiTruong;
	}
	
	public List<QuanTracDanhMuc> searcViTriByPhanTich(Long phanTichId, String ten) {
			List<QuanTracViTriLayMau> viTris = serviceQuanTracViTriLayMauService.searcViTriByPhanTich(phanTichId, ten);
			List<QuanTracDanhMuc> arrayDmViTri = new ArrayList<QuanTracDanhMuc>();
			if(viTris.size() > 0) {
				for(QuanTracViTriLayMau a: viTris) {
					if(a.getViTriQuanTracId() != null && a.getViTriQuanTracId() > 0) {
						Optional<QuanTracDanhMuc> opt = serviceQuanTracDanhMucService.findById(a.getViTriQuanTracId());
						if(!arrayDmViTri.contains(opt.get())) {
							arrayDmViTri.add(opt.get());
						}
					}
				}
			}
		return arrayDmViTri;
	}
	public List<DuLieuMoiTruongTheoDanhMucData> search(Long phanTichId, List<Long> viTriQuanTracId, Long tacDongId) {
		List<DuLieuMoiTruongTheoDanhMucData> list = new ArrayList<DuLieuMoiTruongTheoDanhMucData>();
		Optional<QuanTracPhanTichMoiTruong> optPhanTich = coreChucNangService.findByIdAndTrangThaiAndDaXoa(phanTichId, 1, 0);
		if(optPhanTich.isPresent()) {
			List<QuanTracViTriLayMau> viTris = serviceQuanTracViTriLayMauService.search(phanTichId, viTriQuanTracId);
			List<Long> arrayIdDmViTri = new ArrayList<Long>();
			System.out.println("viTris size  : "+viTris.size());
			for(QuanTracViTriLayMau qt : viTris) {
				System.out.println("test : "+qt.getViTriQuanTracId());
				if(qt.getViTriQuanTracId() != null && !arrayIdDmViTri.contains(qt.getViTriQuanTracId())) {
					arrayIdDmViTri.add(qt.getViTriQuanTracId());
					DuLieuMoiTruongTheoDanhMucData objectDanhMucData = new DuLieuMoiTruongTheoDanhMucData();
					objectDanhMucData.setDmViTriId(qt.getViTriQuanTracId());
					objectDanhMucData.setDmViTriString(qt.getViTriLayMau());
					Optional<QuanTracDanhMuc> optDm = serviceQuanTracDanhMucService.findById(qt.getViTriQuanTracId());
					objectDanhMucData.setLat(optDm.get().getLat());
					objectDanhMucData.setLng(optDm.get().getLng());
					 List<DuLieuMoiTruongForMapData> listDuLieuForViTri = this.getDuLieuFromListViTri(viTris, qt, optPhanTich.get(), tacDongId);
					objectDanhMucData.setListDuLieuForViTri(listDuLieuForViTri);
					list.add(objectDanhMucData);
				}
			}
		}
		return list;
	}
	public List<DuLieuMoiTruongForMapData> getDuLieuFromListViTri(List<QuanTracViTriLayMau> viTris, QuanTracViTriLayMau dataIn, QuanTracPhanTichMoiTruong phanTich, Long tacDongId) {
		List<DuLieuMoiTruongForMapData> list = new ArrayList<DuLieuMoiTruongForMapData>();
		for(QuanTracViTriLayMau a : viTris) {
			if(tacDongId != null && tacDongId > 0) {
				if(dataIn.getViTriQuanTracId() == a.getViTriQuanTracId() && tacDongId == a.getTacDongId()) {
					DuLieuMoiTruongForMapData b = new DuLieuMoiTruongForMapData();
					b.setTenBaoCao(phanTich.getTen());
					if(a.getTacDongId() != null && a.getTacDongId() > 0) {
						Optional<QuanTracDanhMuc> opt = serviceQuanTracDanhMucService.findById(a.getTacDongId());
						b.setTenTacDong(opt.get().getTen());
					}
					b.setTenViTri(a.getViTriLayMau());
					b.setTen(a.getTen());
					Optional<QuanTracHeSoTrungBinh> hsOpt = serviceQuanTracHeSoTrungBinhService.findById(phanTich.getHeSoTrungBinhId());
					if(hsOpt.isPresent()) {
						Double heSoNo2 = hsOpt.get().getChiTieuNo2();
						Double heSoO3 = hsOpt.get().getChiTieuO3();
						Double heSoP10 = hsOpt.get().getChiTieuPm10();
						Double heSoSo2 = hsOpt.get().getChiTieuSo2();
						Double heSoTsp = hsOpt.get().getChiTieuTsp();
						b.setChiTieuNo2(a.getChiTieuNo2());
						b.setChiTieuO3(a.getChiTieuO3());
						b.setChiTieuPm10(a.getChiTieuPm10());
						b.setChiTieuSo2(a.getChiTieuSo2());
						b.setChiTieuTsp(a.getChiTieuTsp());
						Double soTsp = (a.getChiTieuTsp()/heSoTsp)*100;
						Double soNo2 = (a.getChiTieuNo2()/heSoNo2)*100;
						Double soP10 = (a.getChiTieuPm10()/heSoP10)*100;
						Double soO3 = (a.getChiTieuO3()/heSoO3)*100;
						Double soSo2 = (a.getChiTieuSo2()/heSoSo2)*100;
						List<Double> listKetQua = new ArrayList<Double>();
						listKetQua.add(soTsp);
						listKetQua.add(soNo2);
						listKetQua.add(soP10);
						listKetQua.add(soO3);
						listKetQua.add(soSo2);
						Double AQI_kk = Collections.max(listKetQua);
						b.setKetQuaAQI(String.valueOf(AQI_kk));
						if(AQI_kk >= 0 && AQI_kk <=50) {
							b.setColor("xanh");
							b.setTrangThaiMoiTruong("Tốt");
							b.setThongBao("Không ảnh hưởng tới sức khỏe");
							b.setDesThongBao("Tự do thực hiện các hoạt động ngoài trời");
						}
						if(AQI_kk >= 51 && AQI_kk <=100) {
							b.setColor("vang");
							b.setTrangThaiMoiTruong("Trung bình");
							b.setThongBao("Trung bình");
							b.setDesThongBao("Trung bình");
						}
						if(AQI_kk >= 101 && AQI_kk <=150) {
							b.setColor("cam");
							b.setTrangThaiMoiTruong("Kém");
							b.setThongBao("Kém");
							b.setDesThongBao("Kém");
						}
						if(AQI_kk >= 151 && AQI_kk <= 200) {
							b.setColor("do");
							b.setTrangThaiMoiTruong("Xấu");
							b.setThongBao("Xấu");
							b.setDesThongBao("Xấu");
						}
						if(AQI_kk >= 201 && AQI_kk <=300) {
							b.setColor("tim");
							b.setTrangThaiMoiTruong("Rất xấu");
							b.setThongBao("Rất xấu");
							b.setDesThongBao("Rất xấu");
						}
						if(AQI_kk >= 301) {
							b.setColor("dohuyet");
							b.setTrangThaiMoiTruong("Nguy hại");
							b.setThongBao("Nguy hại");
							b.setDesThongBao("Nguy hại");
						}
					}
					list.add(b);
				}
			} else {
				if(dataIn.getViTriQuanTracId() == a.getViTriQuanTracId()) {
					DuLieuMoiTruongForMapData b = new DuLieuMoiTruongForMapData();
					b.setTenBaoCao(phanTich.getTen());
					if(a.getTacDongId() != null && a.getTacDongId() > 0) {
						Optional<QuanTracDanhMuc> opt = serviceQuanTracDanhMucService.findById(a.getTacDongId());
						b.setTenTacDong(opt.get().getTen());
					}
					b.setTenViTri(a.getViTriLayMau());
					b.setTen(a.getTen());
					Optional<QuanTracHeSoTrungBinh> hsOpt = serviceQuanTracHeSoTrungBinhService.findById(phanTich.getHeSoTrungBinhId());
					if(hsOpt.isPresent()) {
						Double heSoNo2 = hsOpt.get().getChiTieuNo2();
						Double heSoO3 = hsOpt.get().getChiTieuO3();
						Double heSoP10 = hsOpt.get().getChiTieuPm10();
						Double heSoSo2 = hsOpt.get().getChiTieuSo2();
						Double heSoTsp = hsOpt.get().getChiTieuTsp();
						b.setChiTieuNo2(a.getChiTieuNo2());
						b.setChiTieuO3(a.getChiTieuO3());
						b.setChiTieuPm10(a.getChiTieuPm10());
						b.setChiTieuSo2(a.getChiTieuSo2());
						b.setChiTieuTsp(a.getChiTieuTsp());
						Double soTsp = (a.getChiTieuTsp()/heSoTsp)*100;
						Double soNo2 = (a.getChiTieuNo2()/heSoNo2)*100;
						Double soP10 = (a.getChiTieuPm10()/heSoP10)*100;
						Double soO3 = (a.getChiTieuO3()/heSoO3)*100;
						Double soSo2 = (a.getChiTieuSo2()/heSoSo2)*100;
						List<Double> listKetQua = new ArrayList<Double>();
						listKetQua.add(soTsp);
						listKetQua.add(soNo2);
						listKetQua.add(soP10);
						listKetQua.add(soO3);
						listKetQua.add(soSo2);
						Double AQI_kk = Collections.max(listKetQua);
						b.setKetQuaAQI(String.valueOf(AQI_kk));
						if(AQI_kk >= 0 && AQI_kk <=50) {
							b.setColor("xanh");
							b.setTrangThaiMoiTruong("Tốt");
							b.setThongBao("Không ảnh hưởng tới sức khỏe");
							b.setDesThongBao("Tự do thực hiện các hoạt động ngoài trời");
						}
						if(AQI_kk >= 51 && AQI_kk <=100) {
							b.setColor("vang");
							b.setTrangThaiMoiTruong("Trung bình");
							b.setThongBao("Trung bình");
							b.setDesThongBao("Trung bình");
						}
						if(AQI_kk >= 101 && AQI_kk <=150) {
							b.setColor("cam");
							b.setTrangThaiMoiTruong("Kém");
							b.setThongBao("Kém");
							b.setDesThongBao("Kém");
						}
						if(AQI_kk >= 151 && AQI_kk <= 200) {
							b.setColor("do");
							b.setTrangThaiMoiTruong("Xấu");
							b.setThongBao("Xấu");
							b.setDesThongBao("Xấu");
						}
						if(AQI_kk >= 201 && AQI_kk <=300) {
							b.setColor("tim");
							b.setTrangThaiMoiTruong("Rất xấu");
							b.setThongBao("Rất xấu");
							b.setDesThongBao("Rất xấu");
						}
						if(AQI_kk >= 301) {
							b.setColor("dohuyet");
							b.setTrangThaiMoiTruong("Nguy hại");
							b.setThongBao("Nguy hại");
							b.setDesThongBao("Nguy hại");
						}
					}
					list.add(b);
				}
			}
			
		}
		return list;
	}
	
	public QuanTracPhanTichMoiTruongData phanTichMoiNhat() throws EntityNotFoundException {
		Optional<QuanTracPhanTichMoiTruong> optional = coreChucNangService.findFirstByDaXoaOrderByIdDesc(0);
		if (optional.isPresent()) {
			QuanTracPhanTichMoiTruong coreChucNang = optional.get();
			QuanTracPhanTichMoiTruongData result = this.convertDataFormEntity(coreChucNang);
			return result;
		}else {
			return new QuanTracPhanTichMoiTruongData();
		}
	}
	public QuanTracPhanTichMoiTruongData findById(Long id) throws EntityNotFoundException {
		Optional<QuanTracPhanTichMoiTruong> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(QuanTracPhanTichMoiTruong.class, "id", String.valueOf(id));
		}
		QuanTracPhanTichMoiTruong coreChucNang = optional.get();
		QuanTracPhanTichMoiTruongData result = this.convertDataFormEntity(coreChucNang);
		return result;
	}
	public QuanTracPhanTichMoiTruongData convertDataFormEntity(QuanTracPhanTichMoiTruong data) {
		QuanTracPhanTichMoiTruongData result = new QuanTracPhanTichMoiTruongData();
		result.setId(data.getId());
		result.setHeSoTrungBinhId(data.getHeSoTrungBinhId());
		result.setTen(data.getTen());
		List<QuanTracViTriLayMau> listViTri = serviceQuanTracViTriLayMauService.findByPhanTichIdAndDaXoa(result.getId(), 0);
		List<QuanTracViTriLayMauData> listViTriData = new ArrayList<QuanTracViTriLayMauData>();
		if(listViTri.size() > 0) {
			for(int i = 0; i < listViTri.size(); i++) {
				QuanTracViTriLayMauData obj = convertViTriDataFromEntity(listViTri.get(i));
				listViTriData.add(obj);
			}
		}
		result.setListViTriLayMau(listViTriData);
		return result;
	}
	public QuanTracViTriLayMauData convertViTriDataFromEntity(QuanTracViTriLayMau item) {
		QuanTracViTriLayMauData viTri = new QuanTracViTriLayMauData();
		viTri.setViTriLayMau(item.getViTriLayMau());
		if(item.getChiTieuNo2() != null) {
			viTri.setChiTieuNo2(item.getChiTieuNo2());
		}
		if(item.getChiTieuO3() != null) {
			viTri.setChiTieuO3(item.getChiTieuO3());
		}
		if(item.getChiTieuPm10() != null) {
			viTri.setChiTieuPm10(item.getChiTieuPm10());
		}
		if(item.getChiTieuSo2() != null) {
			viTri.setChiTieuSo2(item.getChiTieuSo2());
		}
		if(item.getChiTieuTsp() != null) {
			viTri.setChiTieuTsp(item.getChiTieuTsp());
		}
		viTri.setDonViHanhChinhId(item.getDonViHanhChinhId());
		if(item.getDonViHanhChinhId() != null && item.getDonViHanhChinhId() > 0) {
			Optional<DmDonViHanhChinh> dv = serviceDmDonViHanhChinhService.findById(item.getDonViHanhChinhId());
			viTri.setQuanHuyen(dv.get().getTen());
		}
		if(item.getTacDongId() != null && item.getTacDongId() > 0) {
			Optional<QuanTracDanhMuc> dv = serviceQuanTracDanhMucService.findById(item.getTacDongId());
			viTri.setTacDong(dv.get().getTen());
			viTri.setTacDongId(dv.get().getId());
		}
		viTri.setViTriQuanTracId(item.getViTriQuanTracId());
		viTri.setTen(item.getTen());
		viTri.setLat(item.getLat());
		viTri.setLng(item.getLng());
		viTri.setNgayLayMau(item.getNgayLayMau());
		return viTri;
	}
	
	public QuanTracPhanTichMoiTruong create(QuanTracPhanTichMoiTruongData coreChucNangData)
			throws MethodArgumentNotValidException {
		
		QuanTracPhanTichMoiTruong coreChucNang = new QuanTracPhanTichMoiTruong();
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setHeSoTrungBinhId(coreChucNangData.getHeSoTrungBinhId());
		coreChucNang.setDaXoa(0);
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		
		QuanTracPhanTichMoiTruong result = coreChucNangService.save(coreChucNang);
		
		List<QuanTracViTriLayMauData> viTriLayMaus = coreChucNangData.getListViTriLayMau();
		if(viTriLayMaus.size() > 0) {
			viTriLayMaus.stream().forEach(item -> {
				QuanTracViTriLayMau viTri = new QuanTracViTriLayMau();
				viTri.setViTriLayMau(item.getViTriLayMau());
				viTri.setViTriQuanTracId(item.getViTriQuanTracId());
				viTri.setChiTieuNo2(Double.valueOf(item.getChiTieuNo2()));
				viTri.setChiTieuO3(Double.valueOf(item.getChiTieuO3()));
				viTri.setChiTieuPm10(Double.valueOf(item.getChiTieuPm10()));
				viTri.setChiTieuSo2(Double.valueOf(item.getChiTieuSo2()));
				viTri.setChiTieuTsp(Double.valueOf(item.getChiTieuTsp()));
				viTri.setDonViHanhChinhId(item.getDonViHanhChinhId());
				viTri.setTen(item.getTen());
				viTri.setDaXoa(0);
				viTri.setLat(item.getLat());
				viTri.setLng(item.getLng());
				viTri.setNgayLayMau(item.getNgayLayMau());
				viTri.setPhanTichId(result.getId());
				viTri.setTacDongId(item.getTacDongId());
				serviceQuanTracViTriLayMauService.save(viTri);
			});
		}
		return coreChucNang;
	}
	public QuanTracPhanTichMoiTruong createCopy(HashMap<String, Long> data)
			throws MethodArgumentNotValidException {
		Long phanTichId = data.get("idCopy");
		Optional<QuanTracPhanTichMoiTruong> optPhanTich = coreChucNangService.findById(phanTichId);
		QuanTracPhanTichMoiTruong coreChucNang = new QuanTracPhanTichMoiTruong();
		if(optPhanTich.isPresent()) {
			QuanTracPhanTichMoiTruong objectRoot = optPhanTich.get();
			String title_copy = objectRoot.getTen() + "_copy_"+ new Date().getTime();
			coreChucNang.setTen(title_copy);
			coreChucNang.setHeSoTrungBinhId(objectRoot.getHeSoTrungBinhId());
			coreChucNang.setDaXoa(0);
			coreChucNang.setTrangThai(0);
			QuanTracPhanTichMoiTruong result = coreChucNangService.save(coreChucNang);
			
			List<QuanTracViTriLayMau> listQuanTracViTriLayMau = serviceQuanTracViTriLayMauService.findByPhanTichIdAndDaXoa(objectRoot.getId(), 0);
			
			if(listQuanTracViTriLayMau.size() > 0) {
				listQuanTracViTriLayMau.stream().forEach(item -> {
					QuanTracViTriLayMau viTri = new QuanTracViTriLayMau();
					viTri.setViTriLayMau(item.getViTriLayMau());
					viTri.setViTriQuanTracId(item.getViTriQuanTracId());
					viTri.setChiTieuNo2(Double.valueOf(item.getChiTieuNo2()));
					viTri.setChiTieuO3(Double.valueOf(item.getChiTieuO3()));
					viTri.setChiTieuPm10(Double.valueOf(item.getChiTieuPm10()));
					viTri.setChiTieuSo2(Double.valueOf(item.getChiTieuSo2()));
					viTri.setChiTieuTsp(Double.valueOf(item.getChiTieuTsp()));
					viTri.setDonViHanhChinhId(item.getDonViHanhChinhId());
					viTri.setDaXoa(0);
					viTri.setTen(item.getTen());
					viTri.setLat(item.getLat());
					viTri.setLng(item.getLng());
					viTri.setNgayLayMau(item.getNgayLayMau());
					viTri.setPhanTichId(result.getId());
					viTri.setTacDongId(item.getTacDongId());
					serviceQuanTracViTriLayMauService.save(viTri);
				});
			}
		}
		return coreChucNang;
	}
	

	public QuanTracPhanTichMoiTruong update(Long id, QuanTracPhanTichMoiTruongData coreChucNangData)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<QuanTracPhanTichMoiTruong> optional = coreChucNangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(QuanTracPhanTichMoiTruong.class, "id", String.valueOf(id));
		}
		QuanTracPhanTichMoiTruong coreChucNang = optional.get();
		coreChucNang.setTen(coreChucNangData.getTen());
		coreChucNang.setHeSoTrungBinhId(coreChucNangData.getHeSoTrungBinhId());
		coreChucNang.setTrangThai(coreChucNangData.getTrangThai());
		QuanTracPhanTichMoiTruong result = coreChucNangService.save(coreChucNang);
		List<QuanTracViTriLayMauData> viTriLayMaus = coreChucNangData.getListViTriLayMau();
		serviceQuanTracViTriLayMauService.deleteAllByPhanTichId(result.getId());
		if(viTriLayMaus.size() > 0) {
			serviceQuanTracViTriLayMauService.deleteAllByPhanTichId(result.getId());
			viTriLayMaus.stream().forEach(item -> {
				QuanTracViTriLayMau viTri = new QuanTracViTriLayMau();
				viTri.setViTriLayMau(item.getViTriLayMau());
				if(item.getChiTieuNo2() != null) {
					viTri.setChiTieuNo2(item.getChiTieuNo2());
				}
				if(item.getChiTieuO3() != null) {
					viTri.setChiTieuO3(item.getChiTieuO3());
				}
				if(item.getChiTieuPm10() != null) {
					viTri.setChiTieuPm10(item.getChiTieuPm10());
				}
				if(item.getChiTieuSo2() != null) {
					viTri.setChiTieuSo2(item.getChiTieuSo2());
				}
				if(item.getChiTieuTsp() != null) {
					viTri.setChiTieuTsp(item.getChiTieuTsp());
				}
				viTri.setViTriQuanTracId(item.getViTriQuanTracId());
				viTri.setDonViHanhChinhId(item.getDonViHanhChinhId());
				viTri.setDaXoa(0);
				viTri.setTen(item.getTen());
				viTri.setLat(item.getLat());
				viTri.setLng(item.getLng());
				viTri.setNgayLayMau(item.getNgayLayMau());
				viTri.setPhanTichId(result.getId());
				viTri.setTacDongId(item.getTacDongId());
				serviceQuanTracViTriLayMauService.save(viTri);
			});
		}
		return coreChucNang;
	}

	public int delete(Long id) throws EntityNotFoundException {
			try {
				coreChucNangService.delete(id);
				serviceQuanTracViTriLayMauService.deleteAllByPhanTichId(id);
				return 1;
			} catch (Exception e) {
				// TODO: handle exception
				return 0;
			}
	}
}