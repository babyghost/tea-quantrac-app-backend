package vn.tea.app.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;

import vn.tea.app.core.dto.CoreUserData;
import vn.tea.app.core.dto.validator.CoreUserValidator;
import vn.tea.app.core.entity.CoreRole;
import vn.tea.app.core.entity.CoreUser;
import vn.tea.app.core.entity.CoreUser2Role;
import vn.tea.app.core.service.CoreRoleService;
import vn.tea.app.core.service.CoreUser2RoleService;
import vn.tea.app.core.service.CoreUserService;
import vn.tea.app.exceptions.EntityNotFoundException;

@Service
public class CoreUserBusiness {
	
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private CoreUserService coreUserService;
	@Autowired
	private CoreUserValidator coreUserValidator;
	@Autowired
	private CoreRoleService coreRoleService;
	@Autowired
	private CoreUser2RoleService coreUser2RoleService;

	public Page<CoreUserData> findAll(int page, int size, String sortBy, String sortDir, String email, String hoTen,
			List<Long> roleIds) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoreUser> pageCoreUser = coreUserService.findAll(email, hoTen, roleIds,
				PageRequest.of(page, size, direction, sortBy));
		Page<CoreUserData> pageCoreUserData = pageCoreUser.map(this::convertUserData);
		return pageCoreUserData;
	}
	public CoreUserData convertUserData(CoreUser core) {
		CoreUserData result = new CoreUserData(core);
		List<CoreUser2Role> list = coreUser2RoleService.findByUserIdAndDaXoa(core.getId(), 0);
		if(list.size() > 0) {
			List<Long> listRole = new ArrayList<Long>();
			List<String> listNameRole = new ArrayList<String>();
			List<CoreRole> listObjectCoreRoles = new ArrayList<CoreRole>();
			list.stream().forEach(action -> {
				Optional<CoreRole> role = coreRoleService.findById(action.getRoleId());
				if(role.isPresent()) {
					listRole.add(role.get().getId());
					listNameRole.add(role.get().getMa());
					listObjectCoreRoles.add(role.get());
				}
			});
			result.setRoles(listRole);
			result.setRolesName(listNameRole);
			result.setObjectCoreRoles(listObjectCoreRoles);
		}
		return result;
	}
	public CoreUserData findById(Long id) throws EntityNotFoundException {
		Optional<CoreUser> optional = coreUserService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreUser.class, "id", String.valueOf(id));
		}
		CoreUser coreUser = optional.get();
		CoreUserData info = new CoreUserData(coreUser);
		List<CoreUser2Role> list = coreUser2RoleService.findByUserIdAndDaXoa(coreUser.getId(), 0);
		if(list.size() > 0) {
			List<Long> listRole = new ArrayList<Long>();
			List<String> listNameRole = new ArrayList<String>();
			List<CoreRole> listObjectCoreRoles = new ArrayList<CoreRole>();
			list.stream().forEach(action -> {
				Optional<CoreRole> role = coreRoleService.findById(action.getRoleId());
				if(role.isPresent()) {
					listRole.add(role.get().getId());
					listNameRole.add(role.get().getMa());
					listObjectCoreRoles.add(role.get());
				}
			});
			info.setRoles(listRole);
			info.setRolesName(listNameRole);
			info.setObjectCoreRoles(listObjectCoreRoles);
		}
		return info;
	}

	public CoreUser findByEmail(String email) {
		List<CoreUser> coreUsers = coreUserService.findByEmailIgnoreCaseAndDaXoa(email, 0);
		CoreUser coreUser = new CoreUser();
		if (Objects.nonNull(coreUsers) && !coreUsers.isEmpty()) {
			coreUser = coreUsers.get(0);
		}
		return coreUser;
	}
	public CoreUserData findUserById(String email) throws EntityNotFoundException {
		List<CoreUser> coreUsers = coreUserService.findByEmailIgnoreCaseAndDaXoa(email, 0);
		CoreUser coreUser = new CoreUser();
		if (Objects.nonNull(coreUsers) && !coreUsers.isEmpty()) {
			coreUser = coreUsers.get(0);
		}
		CoreUserData info = new CoreUserData(coreUser);
		List<CoreUser2Role> list = coreUser2RoleService.findByUserIdAndDaXoa(coreUser.getId(), 0);
		if(list.size() > 0) {
			List<Long> listRole = new ArrayList<Long>();
			List<String> listNameRole = new ArrayList<String>();
			List<CoreRole> listObjectCoreRoles = new ArrayList<CoreRole>();
			list.stream().forEach(action -> {
				Optional<CoreRole> role = coreRoleService.findById(action.getRoleId());
				if(role.isPresent()) {
					listRole.add(role.get().getId());
					listNameRole.add(role.get().getMa());
					listObjectCoreRoles.add(role.get());
				}
			});
			info.setRoles(listRole);
			info.setRolesName(listNameRole);
			info.setObjectCoreRoles(listObjectCoreRoles);
		}
		return info;
	}
	
	public CoreUser create(CoreUserData coreUserData, BindingResult result) throws MethodArgumentNotValidException {
		coreUserValidator.validate(coreUserData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		CoreUser coreUser = new CoreUser();

		coreUser.setDaXoa(0);
		coreUser.setUserName(coreUserData.getUserName());
		coreUser.setEmail(coreUserData.getEmail());
		coreUser.setHoTen(coreUserData.getHoTen());
		coreUser.setPhone(coreUserData.getPhone());
		coreUser.setCanBoId(coreUserData.getCanBoId());
		if(coreUserData.getPassword() != null) {
			coreUser.setPassword(passwordEncoder.encode(coreUserData.getPassword()));
		}
		CoreUser data = coreUserService.save(coreUser);
		List<Long> roles = coreUserData.getRoles();
		if(roles != null) {
			List<CoreUser2Role> coreUser2Roles = coreUser2RoleService.findByUserIdAndDaXoa(data.getId(), 0);
			if(coreUser2Roles.size() > 0) {
				coreUser2Roles.stream().forEach(action -> {
					action.setDaXoa(1);
					coreUser2RoleService.save(action);
				});
			}
			roles.stream().forEach(item -> {
				CoreUser2Role coreUser2Role = new CoreUser2Role();
				coreUser2Role.setDaXoa(0);
				coreUser2Role.setRoleId(item);
				coreUser2Role.setUserId(data.getId());
				coreUser2RoleService.save(coreUser2Role);
			});
		}
//		List<CoreRole> cRoles = new ArrayList<>();
//		if (Objects.nonNull(roles) && !roles.isEmpty()) {
//			for (String role : roles) {
//				List<CoreRole> coreRoles = coreRoleService.findByMaIgnoreCaseAndDaXoa(role, 0);
//				if (Objects.nonNull(coreRoles) && !coreRoles.isEmpty()) {
//					cRoles.add(coreRoles.get(0));
//				}
//			}
//		}
//		coreUser2RoleService.setFixedDaXoaForUserId(1, coreUser.getId());
//		if (Objects.nonNull(cRoles) && !cRoles.isEmpty()) {
//			for (CoreRole coreRole : cRoles) {
//				CoreUser2Role coreUser2Role = new CoreUser2Role();
//				List<CoreUser2Role> coreUser2Roles = coreUser2RoleService.findByRoleIdAndUserId(coreRole.getId(),
//						coreUser.getId());
//				if (Objects.nonNull(coreUser2Roles) && !coreUser2Roles.isEmpty()) {
//					coreUser2Role = coreUser2Roles.get(0);
//				}
//				coreUser2Role.setDaXoa(0);
//				coreUser2Role.setRoleId(coreRole.getId());
//				coreUser2Role.setUserId(coreUser.getId());
//				coreUser2RoleService.save(coreUser2Role);
//			}
//		}

		return coreUser;
	}

	public CoreUser update(Long id, CoreUserData coreUserData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		coreUserValidator.validate(coreUserData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		Optional<CoreUser> optional = coreUserService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreUser.class, "id", String.valueOf(id));
		}
		CoreUser coreUser = optional.get();

		coreUser.setDaXoa(0);
		coreUser.setUserName(coreUserData.getUserName());
		coreUser.setEmail(coreUserData.getEmail());
		coreUser.setHoTen(coreUserData.getHoTen());
		coreUser.setPhone(coreUserData.getPhone());
		if(coreUserData.getPassword() != null) {
			coreUser.setPassword(passwordEncoder.encode(coreUserData.getPassword()));
		}
		coreUser.setCanBoId(coreUserData.getCanBoId());
		CoreUser data = coreUserService.save(coreUser);
		List<Long> roles = coreUserData.getRoles();
		if(roles != null) {
			List<CoreUser2Role> coreUser2Roles = coreUser2RoleService.findByUserIdAndDaXoa(data.getId(), 0);
			if(coreUser2Roles.size() > 0) {
				coreUser2Roles.stream().forEach(action -> {
					action.setDaXoa(1);
					coreUser2RoleService.save(action);
				});
			}
			roles.stream().forEach(item -> {
				CoreUser2Role coreUser2Role = new CoreUser2Role();
				coreUser2Role.setDaXoa(0);
				coreUser2Role.setRoleId(item);
				coreUser2Role.setUserId(data.getId());
				coreUser2RoleService.save(coreUser2Role);
			});
		}
//		List<String> roles = coreUserData.getRoles();
//		List<CoreRole> cRoles = new ArrayList<>();
//		if (Objects.nonNull(roles) && !roles.isEmpty()) {
//			for (String role : roles) {
//				List<CoreRole> coreRoles = coreRoleService.findByMaIgnoreCaseAndDaXoa(role, 0);
//				if (Objects.nonNull(coreRoles) && !coreRoles.isEmpty()) {
//					cRoles.add(coreRoles.get(0));
//				}
//			}
//		}
//		coreUser2RoleService.setFixedDaXoaForUserId(1, coreUser.getId());
//		if (Objects.nonNull(cRoles) && !cRoles.isEmpty()) {
//			for (CoreRole coreRole : cRoles) {
//				CoreUser2Role coreUser2Role = new CoreUser2Role();
//				List<CoreUser2Role> coreUser2Roles = coreUser2RoleService.findByRoleIdAndUserId(coreRole.getId(),
//						coreUser.getId());
//				if (Objects.nonNull(coreUser2Roles) && !coreUser2Roles.isEmpty()) {
//					coreUser2Role = coreUser2Roles.get(0);
//				}
//				coreUser2Role.setDaXoa(0);
//				coreUser2Role.setRoleId(coreRole.getId());
//				coreUser2Role.setUserId(coreUser.getId());
//				coreUser2RoleService.save(coreUser2Role);
//			}
//		}
		return coreUser;
	}

	public CoreUser delete(@PathVariable("id") Long id) throws EntityNotFoundException {

		Optional<CoreUser> optional = coreUserService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreUser.class, "id", String.valueOf(id));
		}
		CoreUser coreUser = optional.get();
		coreUser.setDaXoa(1);
		coreUserService.save(coreUser);
		return coreUser;
	}

}
