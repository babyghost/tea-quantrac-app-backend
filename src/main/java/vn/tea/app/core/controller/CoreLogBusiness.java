package vn.tea.app.core.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.tea.app.core.dto.CoreLogOutput;
import vn.tea.app.core.entity.CoreLog;
import vn.tea.app.core.service.CoreLogService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
public class CoreLogBusiness {

	@Autowired
	private CoreLogService coreLogService;

//	public Page<CoreLog> findAll(int page, int size, String sortBy, String sortDir, String search,
//			Long nhomChucNangId, Integer trangThai) {
//
//		Direction direction;
//		if (sortDir.equals("ASC")) {
//			direction = Direction.ASC;
//		} else {
//			direction = Direction.DESC;
//		}
//		Page<CoreLog> pageCoreLog = CoreLogService.findAll(search, nhomChucNangId, trangThai,
//				PageRequest.of(page, size, direction, sortBy));
//
//		return pageCoreLog;
//	}
	
	public Page<CoreLogOutput> findAll(int page, int size, String sortBy, String sortDir,
			String nguoiCapNhat, Long objectId, Integer type) {
		
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoreLog> pageCoreLog = coreLogService.findAll(nguoiCapNhat, objectId, type,
				PageRequest.of(page, size, direction, sortBy));
		Page<CoreLogOutput> result = pageCoreLog.map(this::convertCoreLog);
		return result;
	}
	public CoreLogOutput convertCoreLog(CoreLog coreLog) {
		CoreLogOutput output = new CoreLogOutput(coreLog);
		String temp = coreLog.getActionDonViThucHien();
		if(temp != null && temp != "") {
			String[] str = temp.split("_");
//			update_tuvan_CÔNG TY TNHH MTV CƠ KHÍ XÂY DỰNG CÔNG NGHIỆP QUANG  LONG
			String noidungUpdate = "Đã ";
			String str1 = "";
			String str2 = "";
			String str3 = "";
			if(str.length == 3) {
				switch (str[0]) {
				case "update":
					str1 = " hiệu chỉnh ";
					break;
				case "create":
					str1 = " thêm ";
					break;
				case "delete":
					str1 = " xóa ";
					break;	
				default:
					str1 = "";
					break;
				}
				switch (str[1]) {
				case "tuvan":
					str2 = " dữ liệu tư vấn ";
					break;
				case "giamsat":
					str2 = " dữ liệu giám sát ";
					break;	
				case "thicong":
					str2 = " dữ liệu thi công ";
					break;	
				default:
					str2 = "";
					break;
				}
				str3 = str[2];
			}
			if(str.length == 2) {
				switch (str[0]) {
				case "update":
					str1 = " hiệu chỉnh ";
					break;
				case "create":
					str1 = " thêm ";
					break;
				case "delete":
					str1 = " xóa ";
					break;	
				default:
					str1 = "";
					break;
				}
				str3 = str[1];
			}
			
			noidungUpdate = noidungUpdate + str1+ str2 + str3;
			output.setActionDonViThucHien(noidungUpdate);
		}
		return output;
	}
	public CoreLog findById(Long id) throws EntityNotFoundException {
		Optional<CoreLog> optional = coreLogService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreLog.class, "id", String.valueOf(id));
		}
		CoreLog CoreLog = optional.get();
		return CoreLog;
	}
}
