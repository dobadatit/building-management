package service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import DTO.AssignmentStaffDTO;
import DTO.RoleDTO;
import DTO.UserDTO;
import builder.BuilderSearchUser;
import converter.RoleConverter;
import converter.UserConverter;
import entity.RoleEntity;
import entity.UserEntity;
import paging.Pagable;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.AssignmentStaffService;
import service.UserService;

public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private UserConverter userConverter;
	private RoleConverter roleConverter;
	private AssignmentStaffService assignmentStaffService;

	public UserServiceImpl() {
		userRepository = new UserRepositoryImpl();
		userConverter = new UserConverter();
		roleConverter = new RoleConverter();
		assignmentStaffService = new AssignmentStaffServiceImpl();
	}

	@Override
	public List<UserDTO> findAllStaff(Pagable pageable, BuilderSearchUser builderSearchUser) {
		List<UserEntity> listEntity = userRepository.listUser(builderSearchUser, pageable);
		List<UserDTO> listDTO = new ArrayList<>();
		for (UserEntity userEntity : listEntity) {
			UserDTO dto = new UserDTO();
			dto = userConverter.convertToDTO(userEntity);
			if (builderSearchUser.getBuildingId() != null) {
				Long id = Long.valueOf(builderSearchUser.getBuildingId());
				List<AssignmentStaffDTO> listAssign = assignmentStaffService.findByBuildingId(id);
				for (AssignmentStaffDTO assignmentStaffDTO : listAssign) {
					if (assignmentStaffDTO.getStaffid() == userEntity.getId()) {
						dto.setChecked("checked");
					}
				}
			}
			listDTO.add(dto);
		}
		return listDTO;
	}
	/*
	 * private Map<String, Object> convertToMapProperties(BuilderSearchUser builder)
	 * { Map<String, Object> property = new HashMap<>(); try { Field[] files =
	 * BuilderSearchUser.class.getDeclaredFields(); for (Field field : files) {
	 * field.setAccessible(true); // cho truy cap private if (field.get(builder)
	 * instanceof String) { if (field.get(builder) != null &&
	 * StringUtils.isNotEmpty((String) field.get(builder))) {
	 * property.put(field.getName().toLowerCase(), Integer.parseInt((String)
	 * field.get(builder))); } } else { property.put(field.getName().toLowerCase(),
	 * field.get(builder)); } } } catch (Exception e) {
	 * System.out.println(e.getMessage()); }
	 * 
	 * return property;
	 * 
	 * }
	 */

	@Override
	public UserDTO findById(Long id) {
		return userConverter.convertToDTO(userRepository.findById(id));
	}

	@Override
	public UserDTO findByAccount(String userName, String password) {
		List<Object> list = userRepository.findByAccount(userName, password);
		UserDTO dto = new UserDTO();
		RoleDTO roleDTO = new RoleDTO();
		for (Object object : list) {
			if (object instanceof UserEntity) {
				UserEntity entity = (UserEntity) object;
				dto = userConverter.convertToDTO(entity);
			} else {
				RoleEntity entity = (RoleEntity) object;
				roleDTO = roleConverter.convertToDTO(entity);
			}
		}
		dto.setRoleDTO(roleDTO);
		return dto;
	}

	@Override
	public List<UserDTO> findAllCustomer(Pagable pageable, BuilderSearchUser builderSearchUser) {
		Map<String, Object> params = convertToMapProperties(builderSearchUser);
		List<UserEntity> listEntities = userRepository.findAllCustomer(params, builderSearchUser, pageable);
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		for (UserEntity userEntity : listEntities) {
			UserDTO dto = new UserDTO();
			dto = userConverter.convertToDTO(userEntity);
			listDTO.add(dto);
		}
		return listDTO;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> convertToMapProperties(BuilderSearchUser builderSearchUser) {
		Map<String, Object> params = new HashedMap();
		try {
			Field[] fields = BuilderSearchUser.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.get(builderSearchUser) instanceof String) {
					if (field.get(builderSearchUser) != null
							&& StringUtils.isNotEmpty((String) field.get(builderSearchUser))) {
						params.put(field.getName().toLowerCase(), field.get(builderSearchUser));
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return params;

	}

	@Override
	public boolean deleteCustomer(Long[] ids) {
		for (Long item : ids) {
			if (userRepository.deleteCustomer(item) == true) {
				return true;
			}
		}
		return false;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		UserEntity userEntity = userConverter.convertoEntity(userDTO);
		UserEntity entity = userRepository.saveUser(userEntity);
		return userConverter.convertToDTO(entity);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		UserEntity userEntity = userConverter.convertoEntity(userDTO);
		UserEntity entity = userRepository.updateUser(userEntity);
		return userConverter.convertToDTO(entity);
	}
}
