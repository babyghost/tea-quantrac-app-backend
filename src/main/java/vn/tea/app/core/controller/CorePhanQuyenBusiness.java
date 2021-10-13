package vn.tea.app.core.controller;
//package vn.tea.core.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import lombok.extern.slf4j.Slf4j;
//import vn.tea.config.security.model.TokenUserDetails;
//import vn.tea.config.security.service.UaaUserService;
//import vn.tea.core.data.DanhSachChucNangOutput;
//import vn.tea.core.data.DanhSachMenuOutput;
//import vn.tea.core.data.DanhSachNhomChucNangOutput;
//import vn.tea.core.model.CoreChucNang;
//import vn.tea.core.model.CoreNhomChucNang;
//import vn.tea.core.model.CoreRole;
//import vn.tea.core.model.CoreRole2ChucNang;
//import vn.tea.core.model.DanhSachCoreChucNang;
//import vn.tea.core.service.CoreChucNangService;
//import vn.tea.core.service.CoreNhomChucNangService;
//import vn.tea.core.service.CoreRole2ChucNangService;
//import vn.tea.core.service.CoreRoleService;
//import vn.tea.core.service.DanhSachCoreChucNangService;
//
//@Service
//@Slf4j
//public class CorePhanQuyenBusiness {
//
//	@Autowired
//	private CoreChucNangService coreChucNangService;
//	@Autowired
//	private CoreNhomChucNangService coreNhomChucNangService;
//	@Autowired
//	private CoreRole2ChucNangService coreRole2ChucNangService;
//	@Autowired
//	private CoreRoleService coreRoleService;
//	@Autowired
//	private DanhSachCoreChucNangService danhSachCoreChucNangService;
//
//	public List<String> getChucNangMas(String roleName) {
//		List<String> chucNangMas = coreChucNangService.getChucNangMas(roleName);
//		return chucNangMas;
//
//	}
//
//	public DanhSachMenuOutput getChucNang() {
//		TokenUserDetails tokenUserDetails = uaaUserService.getTokenUserDetails();
//		String email = "";
//		List<String> roles = new ArrayList<>();
//		if (Objects.nonNull(tokenUserDetails)) {
//			email = tokenUserDetails.getUsername();
//			roles = tokenUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//					.collect(Collectors.toList());
//			log.info("user: " + uaaUserService.getTokenUserDetails().getAuthorities());
//			log.info("roles: " + roles);
//		}
//
//		DanhSachMenuOutput danhSachMenuOutput = new DanhSachMenuOutput();
//		danhSachMenuOutput.setEmail(email);
//		danhSachMenuOutput.setRoles(roles);
//		List<DanhSachNhomChucNangOutput> danhSachNhomChucNangOutputs = new ArrayList<>();
//
//		List<CoreNhomChucNang> coreNhomChucNangs = coreNhomChucNangService
//				.findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(1, 0);
//
//		if (Objects.nonNull(coreNhomChucNangs) && !coreNhomChucNangs.isEmpty()) {
//			for (CoreNhomChucNang coreNhomChucNang : coreNhomChucNangs) {
//				List<String> roleNhomChucNangs = new ArrayList<>();
//				DanhSachNhomChucNangOutput danhSachNhomChucNangOutput = new DanhSachNhomChucNangOutput();
//
//				danhSachNhomChucNangOutput.setId(coreNhomChucNang.getId());
//				danhSachNhomChucNangOutput.setNhomChucNangTen(coreNhomChucNang.getTen());
//				danhSachNhomChucNangOutput.setNhomChucNangMa(coreNhomChucNang.getMa());
//				danhSachNhomChucNangOutput.setIcon(coreNhomChucNang.getIcon());
//				danhSachNhomChucNangOutput.setPath(coreNhomChucNang.getPath());
//				danhSachNhomChucNangOutput.setDanhSachNhomChucNangCons(setNhomChucNangCon(coreNhomChucNang.getId()));
//				List<CoreChucNang> coreChucNangs = coreNhomChucNang.getCoreChucNangs();
//				List<DanhSachChucNangOutput> danhSachChucNangOutputs = new ArrayList<>();
//				if (Objects.nonNull(coreChucNangs) && !coreChucNangs.isEmpty()) {
//					for (CoreChucNang coreChucNang : coreChucNangs) {
//						List<String> roleNames = new ArrayList<>();
//						List<CoreRole2ChucNang> coreRole2ChucNangs = coreRole2ChucNangService
//								.findByChucNangIdAndDaXoa(coreChucNang.getId(), 0);
//						if (Objects.nonNull(coreRole2ChucNangs) && !coreRole2ChucNangs.isEmpty()) {
//							for (CoreRole2ChucNang coreRole2ChucNang : coreRole2ChucNangs) {
//								if (Objects.nonNull(coreRole2ChucNang.getRoleId())) {
//									Optional<CoreRole> optionalCoreRole = coreRoleService
//											.findById(coreRole2ChucNang.getRoleId());
//									if (optionalCoreRole.isPresent()) {
//										String role = optionalCoreRole.get().getMa();
//										if (Objects.nonNull(role) && !role.isEmpty()) {
//											roleNames.add(role);
//											roleNhomChucNangs.add(role);
//										}
//									}
//								}
//							}
//						}
//						DanhSachChucNangOutput danhSachChucNangOutput = new DanhSachChucNangOutput();
//						danhSachChucNangOutput.setId(coreChucNang.getId());
//						danhSachChucNangOutput.setTitle(coreChucNang.getTen());
//						danhSachChucNangOutput.setChucNangTen(coreChucNang.getTen());
//						danhSachChucNangOutput.setChucNangMa(coreChucNang.getMa());
//						danhSachChucNangOutput.setPath(coreChucNang.getPath());
//						danhSachChucNangOutput.setComponent(coreChucNang.getComponent());
//						danhSachChucNangOutput.setHidden(coreChucNang.getHidden());
//						danhSachChucNangOutput.setAffix(coreChucNang.getAffix());
//						danhSachChucNangOutput.setActiveMenu(coreChucNang.getActiveMenu());
//						danhSachChucNangOutput.setAlwaysShow(coreChucNang.getAlwaysShow());
//						danhSachChucNangOutput.setIcon(coreChucNang.getIcon());
//						danhSachChucNangOutput.setNoCache(coreChucNang.getNoCache());
//						danhSachChucNangOutput.setBreadcrumb(coreChucNang.getBreadcrumb());
//						danhSachChucNangOutput.setSapXep(coreChucNang.getSapXep());
//						danhSachChucNangOutput.setRoles(roleNames);
//						danhSachChucNangOutputs.add(danhSachChucNangOutput);
//					}
//				}
//				if (Objects.nonNull(danhSachNhomChucNangOutput.getDanhSachNhomChucNangCons())
//						&& !danhSachNhomChucNangOutput.getDanhSachNhomChucNangCons().isEmpty()) {
//					for (DanhSachNhomChucNangOutput daNhomChucNangOutput : danhSachNhomChucNangOutput
//							.getDanhSachNhomChucNangCons()) {
//						roleNhomChucNangs.addAll(daNhomChucNangOutput.getRoles());
//					}
//				}
//				System.out.println("Nhóm chức năng cha: " + coreNhomChucNang.getTen() + " - Role : "
//						+ roleNhomChucNangs.stream().distinct().collect(Collectors.toList()));
//				danhSachNhomChucNangOutput.setRoles(roleNhomChucNangs.stream().distinct().collect(Collectors.toList()));
//				danhSachNhomChucNangOutput.setDanhSachChucNangs(danhSachChucNangOutputs);
//				danhSachNhomChucNangOutputs.add(danhSachNhomChucNangOutput);
//			}
//		}
//
//		danhSachMenuOutput.setDanhSachNhomChucNangOutputs(danhSachNhomChucNangOutputs);
//		return danhSachMenuOutput;
//	}
//
//	public List<DanhSachNhomChucNangOutput> setNhomChucNangCon(Long nhomChucNangChaId) {
//		List<DanhSachNhomChucNangOutput> danhSachNhomChucNangConOutputs = new ArrayList<>();
//		List<CoreNhomChucNang> coreNhomChucNangCons = coreNhomChucNangService
//				.findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(nhomChucNangChaId, 1, 0);
//		if (Objects.nonNull(coreNhomChucNangCons) && !coreNhomChucNangCons.isEmpty()) {
//			for (CoreNhomChucNang coreNhomChucNang : coreNhomChucNangCons) {
//				List<String> roleNhomChucNangs = new ArrayList<>();
//				DanhSachNhomChucNangOutput danhSachNhomChucNangOutput = new DanhSachNhomChucNangOutput();
//
//				danhSachNhomChucNangOutput.setId(coreNhomChucNang.getId());
//				danhSachNhomChucNangOutput.setNhomChucNangTen(coreNhomChucNang.getTen());
//				danhSachNhomChucNangOutput.setNhomChucNangMa(coreNhomChucNang.getMa());
//				danhSachNhomChucNangOutput.setIcon(coreNhomChucNang.getIcon());
//				danhSachNhomChucNangOutput.setPath(coreNhomChucNang.getPath());
//				// xử lý chức năng theo nhóm chức năng
//				List<CoreChucNang> coreChucNangs = coreNhomChucNang.getCoreChucNangs();
//				List<DanhSachChucNangOutput> danhSachChucNangOutputs = new ArrayList<>();
//				if (Objects.nonNull(coreChucNangs) && !coreChucNangs.isEmpty()) {
//					for (CoreChucNang coreChucNang : coreChucNangs) {
//						List<String> roleNames = new ArrayList<>();
//						List<CoreRole2ChucNang> coreRole2ChucNangs = coreRole2ChucNangService
//								.findByChucNangIdAndDaXoa(coreChucNang.getId(), 0);
//						if (Objects.nonNull(coreRole2ChucNangs) && !coreRole2ChucNangs.isEmpty()) {
//							for (CoreRole2ChucNang coreRole2ChucNang : coreRole2ChucNangs) {
//								if (Objects.nonNull(coreRole2ChucNang.getRoleId())) {
//									Optional<CoreRole> optionalCoreRole = coreRoleService
//											.findById(coreRole2ChucNang.getRoleId());
//									if (optionalCoreRole.isPresent()) {
//										String role = optionalCoreRole.get().getMa();
//										if (Objects.nonNull(role) && !role.isEmpty()) {
//											roleNames.add(role);
//											roleNhomChucNangs.add(role);
//										}
//									}
//								}
//							}
//						}
//						DanhSachChucNangOutput danhSachChucNangOutput = new DanhSachChucNangOutput();
//						danhSachChucNangOutput.setId(coreChucNang.getId());
//						danhSachChucNangOutput.setTitle(coreChucNang.getTen());
//						danhSachChucNangOutput.setChucNangTen(coreChucNang.getTen());
//						danhSachChucNangOutput.setChucNangMa(coreChucNang.getMa());
//						danhSachChucNangOutput.setPath(coreChucNang.getPath());
//						danhSachChucNangOutput.setComponent(coreChucNang.getComponent());
//						danhSachChucNangOutput.setHidden(coreChucNang.getHidden());
//						danhSachChucNangOutput.setAffix(coreChucNang.getAffix());
//						danhSachChucNangOutput.setActiveMenu(coreChucNang.getActiveMenu());
//						danhSachChucNangOutput.setAlwaysShow(coreChucNang.getAlwaysShow());
//						danhSachChucNangOutput.setIcon(coreChucNang.getIcon());
//						danhSachChucNangOutput.setNoCache(coreChucNang.getNoCache());
//						danhSachChucNangOutput.setBreadcrumb(coreChucNang.getBreadcrumb());
//						danhSachChucNangOutput.setSapXep(coreChucNang.getSapXep());
//						danhSachChucNangOutput.setRoles(roleNames);
//						danhSachChucNangOutputs.add(danhSachChucNangOutput);
//					}
//				}
//				System.out.println("Nhóm chức năng con: " + coreNhomChucNang.getTen() + " - Role : "
//						+ roleNhomChucNangs.stream().distinct().collect(Collectors.toList()));
//				danhSachNhomChucNangOutput.setRoles(roleNhomChucNangs.stream().distinct().collect(Collectors.toList()));
//				danhSachNhomChucNangOutput.setDanhSachChucNangs(danhSachChucNangOutputs);
//				danhSachNhomChucNangConOutputs.add(danhSachNhomChucNangOutput);
//
//				danhSachNhomChucNangOutput.setDanhSachNhomChucNangCons(setNhomChucNangCon(coreNhomChucNang.getId()));
//			}
//		}
//		return danhSachNhomChucNangConOutputs;
//	}
//
//	public DanhSachMenuOutput getRouters() {
//		TokenUserDetails tokenUserDetails = uaaUserService.getTokenUserDetails();
//		String email = "";
//		List<String> roles = new ArrayList<>();
//		if (Objects.nonNull(tokenUserDetails)) {
//			email = tokenUserDetails.getUsername();
//			roles = tokenUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//					.collect(Collectors.toList());
//			log.info("user: " + uaaUserService.getTokenUserDetails().getAuthorities());
//			log.info("roles: " + roles);
//		}
//		DanhSachMenuOutput danhSachMenuOutput = new DanhSachMenuOutput();
//		danhSachMenuOutput.setEmail(email);
//		danhSachMenuOutput.setRoles(roles);
//		List<DanhSachNhomChucNangOutput> danhSachNhomChucNangOutputs = new ArrayList<>();
//		List<DanhSachCoreChucNang> danhSachCoreChucNangs = danhSachCoreChucNangService.getRouters(roles);
//		log.info("danhSachCoreChucNangs: " + danhSachCoreChucNangs);
//		List<DanhSachCoreChucNang> danhSachNhomChucNang = danhSachCoreChucNangs.stream()
//				.filter(ds -> ds.getChucNangId() == null).collect(Collectors.toList());
//		List<Long> nhomChucNangChaIds = new ArrayList<Long>();
//		for (DanhSachCoreChucNang ds : danhSachNhomChucNang) {
//			log.info("nhomChucNangChaIds: " + ds.getTen());
//			nhomChucNangChaIds.add(ds.getId());
//		}
//		nhomChucNangChaIds = nhomChucNangChaIds.stream().distinct().collect(Collectors.toList());
//		if (Objects.nonNull(nhomChucNangChaIds) && !nhomChucNangChaIds.isEmpty()) {
//			for (Long nhomChucNangChaId : nhomChucNangChaIds) {
//				Long ncnChaId = nhomChucNangChaId;
//				List<DanhSachCoreChucNang> danhSachNhomChucNangCha = danhSachCoreChucNangs.stream()
//						.filter(ds -> ds.getNhomChucNangChaId() == null).filter(ds -> ds.getId().equals(ncnChaId))
//						.collect(Collectors.toList());
//				if (Objects.nonNull(danhSachNhomChucNangCha) && !danhSachNhomChucNangCha.isEmpty()) {
//					DanhSachCoreChucNang dsNhom = danhSachNhomChucNangCha.stream().findFirst().orElse(null);
//					if (Objects.nonNull(dsNhom)) {
//						DanhSachNhomChucNangOutput danhSachNhomChucNangOutput = new DanhSachNhomChucNangOutput();
//						danhSachNhomChucNangOutput.setId(dsNhom.getId());
//						danhSachNhomChucNangOutput.setNhomChucNangTen(dsNhom.getTen());
//						danhSachNhomChucNangOutput.setNhomChucNangMa(dsNhom.getMa());
//						danhSachNhomChucNangOutput.setIcon(dsNhom.getIcon());
//						danhSachNhomChucNangOutput.setPath(dsNhom.getPath());
//						List<DanhSachNhomChucNangOutput> danhSachNhomChucNangConOutputs = new ArrayList<>();
//						List<DanhSachCoreChucNang> dsNhomChucNangCons = danhSachCoreChucNangs.stream()
//								.filter(ds -> ds.getNhomChucNangChaId() != null)
//								.filter(ds -> ds.getNhomChucNangChaId().equals(dsNhom.getId()))
//								.collect(Collectors.toList());
//						if (Objects.nonNull(dsNhomChucNangCons) && !dsNhomChucNangCons.isEmpty()) {
//							List<Long> nhomChucNangIds = new ArrayList<Long>();
//							for (DanhSachCoreChucNang ds : dsNhomChucNangCons) {
//								nhomChucNangIds.add(ds.getId());
//							}
//							nhomChucNangIds = nhomChucNangIds.stream().distinct().collect(Collectors.toList());
//							if (Objects.nonNull(nhomChucNangIds) && !nhomChucNangIds.isEmpty()) {
//								danhSachNhomChucNangConOutputs = setRouterCon(danhSachCoreChucNangs, dsNhomChucNangCons,
//										nhomChucNangIds, roles);
//							}
//						}
//						if (Objects.nonNull(danhSachNhomChucNangConOutputs)
//								&& !danhSachNhomChucNangConOutputs.isEmpty()) {
//							danhSachNhomChucNangOutput.setDanhSachNhomChucNangCons(danhSachNhomChucNangConOutputs);
//						} else {
//							List<DanhSachCoreChucNang> dsChucNangs = danhSachCoreChucNangs.stream()
//									.filter(ds -> ds.getId().equals(dsNhom.getId())).collect(Collectors.toList());
//							List<DanhSachChucNangOutput> danhSachChucNangOutputs = new ArrayList<>();
//							if (Objects.nonNull(dsChucNangs) && !dsChucNangs.isEmpty()) {
//								for (DanhSachCoreChucNang coreChucNang : dsChucNangs) {
//									DanhSachChucNangOutput danhSachChucNangOutput = new DanhSachChucNangOutput();
//									danhSachChucNangOutput.setId(coreChucNang.getId());
//									danhSachChucNangOutput.setTitle(coreChucNang.getChucNangTen());
//									danhSachChucNangOutput.setChucNangTen(coreChucNang.getChucNangTen());
//									danhSachChucNangOutput.setChucNangMa(coreChucNang.getChucNangMa());
//									danhSachChucNangOutput.setPath(coreChucNang.getChucNangPath());
//									danhSachChucNangOutput.setComponent(coreChucNang.getComponent());
//									danhSachChucNangOutput.setHidden(coreChucNang.getHidden());
//									danhSachChucNangOutput.setAffix(coreChucNang.getAffix());
//									danhSachChucNangOutput.setActiveMenu(coreChucNang.getActiveMenu());
//									danhSachChucNangOutput.setAlwaysShow(coreChucNang.getAlwaysShow());
//									danhSachChucNangOutput.setIcon(coreChucNang.getIcon());
//									danhSachChucNangOutput.setNoCache(coreChucNang.getNoCache());
//									danhSachChucNangOutput.setBreadcrumb(coreChucNang.getBreadcrumb());
//									danhSachChucNangOutput.setSapXep(coreChucNang.getSapXep());
//									danhSachChucNangOutput.setRoles(roles);
//									danhSachChucNangOutputs.add(danhSachChucNangOutput);
//								}
//							}
//							danhSachNhomChucNangOutput.setDanhSachChucNangs(danhSachChucNangOutputs);
//						}
//
//						danhSachNhomChucNangOutput.setDanhSachNhomChucNangCons(danhSachNhomChucNangConOutputs);
//						danhSachNhomChucNangOutputs.add(danhSachNhomChucNangOutput);
//					}
//				}
//			}
//		}
//		danhSachMenuOutput.setDanhSachNhomChucNangOutputs(danhSachNhomChucNangOutputs);
//		return danhSachMenuOutput;
//	}
//
//	public List<DanhSachNhomChucNangOutput> setRouterCon(List<DanhSachCoreChucNang> danhSachCoreChucNangGocs,
//			List<DanhSachCoreChucNang> danhSachCoreChucNangs, List<Long> nhomChucNangIds, List<String> roles) {
//		List<DanhSachNhomChucNangOutput> danhSachNhomChucNangConOutputs = new ArrayList<>();
//		for (Long nhomChucNangId : nhomChucNangIds) {
//			Long lv = nhomChucNangId;
//			List<DanhSachCoreChucNang> dsNhomChucNangCons = danhSachCoreChucNangs.stream()
//					.filter(ds -> ds.getId().equals(lv)).collect(Collectors.toList());
//			if (Objects.nonNull(dsNhomChucNangCons) && !dsNhomChucNangCons.isEmpty()) {
//				DanhSachCoreChucNang dsNhom = dsNhomChucNangCons.stream().findFirst().orElse(null);
//				if (Objects.nonNull(dsNhom)) {
//					DanhSachNhomChucNangOutput danhSachNhomChucNangCon = new DanhSachNhomChucNangOutput();
//					danhSachNhomChucNangCon.setId(dsNhom.getId());
//					danhSachNhomChucNangCon.setNhomChucNangTen(dsNhom.getTen());
//					danhSachNhomChucNangCon.setNhomChucNangMa(dsNhom.getMa());
//					danhSachNhomChucNangCon.setIcon(dsNhom.getIcon());
//					danhSachNhomChucNangCon.setPath(dsNhom.getPath());
//
//					List<DanhSachNhomChucNangOutput> dsNhomChucNangCon = new ArrayList<>();
//					List<DanhSachCoreChucNang> dsCoreChucNangs = danhSachCoreChucNangGocs.stream()
//							.filter(ds -> ds.getNhomChucNangChaId() != null)
//							.filter(ds -> ds.getNhomChucNangChaId().equals(nhomChucNangId))
//							.collect(Collectors.toList());
//					if (Objects.nonNull(dsCoreChucNangs) && !dsCoreChucNangs.isEmpty()) {
//						List<Long> nhomChucNangConIds = new ArrayList<Long>();
//						for (DanhSachCoreChucNang ds : dsCoreChucNangs) {
//							nhomChucNangConIds.add(ds.getId());
//						}
//						nhomChucNangConIds = nhomChucNangConIds.stream().distinct().collect(Collectors.toList());
//						if (Objects.nonNull(nhomChucNangConIds) && !nhomChucNangConIds.isEmpty()) {
//							dsNhomChucNangCon = setRouterCon(danhSachCoreChucNangGocs, dsCoreChucNangs,
//									nhomChucNangConIds, roles);
//						}
//					} else {
//						List<DanhSachChucNangOutput> danhSachChucNangOutputs = new ArrayList<>();
//						for (DanhSachCoreChucNang dsChucNang : dsNhomChucNangCons) {
//							DanhSachChucNangOutput danhSachChucNangOutput = new DanhSachChucNangOutput();
//							danhSachChucNangOutput.setId(dsChucNang.getChucNangId());
//							danhSachChucNangOutput.setTitle(dsChucNang.getChucNangTen());
//							danhSachChucNangOutput.setChucNangTen(dsChucNang.getChucNangTen());
//							danhSachChucNangOutput.setChucNangMa(dsChucNang.getChucNangMa());
//							danhSachChucNangOutput.setPath(dsChucNang.getChucNangPath());
//							danhSachChucNangOutput.setComponent(dsChucNang.getComponent());
//							danhSachChucNangOutput.setHidden(dsChucNang.getHidden());
//							danhSachChucNangOutput.setAffix(dsChucNang.getAffix());
//							danhSachChucNangOutput.setActiveMenu(dsChucNang.getActiveMenu());
//							danhSachChucNangOutput.setAlwaysShow(dsChucNang.getAlwaysShow());
//							danhSachChucNangOutput.setIcon(dsChucNang.getIcon());
//							danhSachChucNangOutput.setNoCache(dsChucNang.getNoCache());
//							danhSachChucNangOutput.setBreadcrumb(dsChucNang.getBreadcrumb());
//							danhSachChucNangOutput.setSapXep(dsChucNang.getSapXep());
//							danhSachChucNangOutput.setRoles(roles);
//							danhSachChucNangOutputs.add(danhSachChucNangOutput);
//						}
//						danhSachNhomChucNangCon.setDanhSachChucNangs(danhSachChucNangOutputs);
//					}
//					danhSachNhomChucNangCon.setDanhSachNhomChucNangCons(dsNhomChucNangCon);
//					danhSachNhomChucNangConOutputs.add(danhSachNhomChucNangCon);
//				}
//			}
//		}
//
//		return danhSachNhomChucNangConOutputs;
//	}
//}
