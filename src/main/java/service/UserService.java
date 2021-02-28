package service;

import java.util.List;
import java.util.Map;

import DTO.UserDTO;
import builder.BuilderSearchUser;
import paging.Pagable;

public interface UserService {
	public List<UserDTO> findAllStaff(Pagable pageable, BuilderSearchUser builderSearchUser);

	public List<UserDTO> findAllCustomer(Pagable pageable, BuilderSearchUser builderSearchUser);

	public UserDTO findById(Long id);

	public UserDTO findByAccount(String userName, String password);

	public boolean deleteCustomer(Long[] ids);

	public UserDTO saveUser(UserDTO userDTO);
	
	public UserDTO updateUser(UserDTO userDTO);
}
