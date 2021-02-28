package repository;

import java.util.List;
import java.util.Map;

import builder.BuilderSearchUser;
import entity.BuildingEntity;
import entity.UserEntity;
import paging.Pagable;

public interface UserRepository  extends JpaRepository<UserEntity> {
 
	public List<UserEntity> listUser(BuilderSearchUser  builder,Pagable pagable);
	
	public List<UserEntity> findByBuildingId(Long id);
	
	public List<Object> findByAccount(String userName,String password);
	
	public List<UserEntity> findAllCustomer(Map<String, Object> params, BuilderSearchUser builderSearchUser,Pagable page);
	
	public boolean deleteCustomer(Long id);
	
	public UserEntity updateUser(UserEntity entity);
	
	public UserEntity saveUser(UserEntity userEntity);
	

}
