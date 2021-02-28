package repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import builder.BuilderSearchUser;
import entity.RoleEntity;
import entity.UserEntity;
import paging.Pagable;
import repository.UserRepository;

public class UserRepositoryImpl extends SimpleJpaRepository<UserEntity> implements UserRepository {
	private EntityManagerImpl connect = new EntityManagerImpl();

	@Override
	public List<UserEntity> listUser(BuilderSearchUser builder, Pagable pagable) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from user where status = 1 ");
//		if (StringUtils.isNotBlank(builder.getBuildingId())) {
//			sql.append("   LEFT JOIN  assignmentstaff a on u.id = a.id  WHERE u.status =1);
//		}
		return this.findAll(sql.toString(), pagable);
	}
	
	@Override
	public List<UserEntity> findByBuildingId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select * from user left  JOIN  assignmentstaff  on user.id = assignmentstaff.staffid where assignmentstaff.buildingid= ?");
		return this.findById(sql.toString(), id);
	}

	@Override
	public List<Object> findByAccount(String userName, String password) {
		StringBuilder sql = new StringBuilder("select  u.username,u.fullname,ro.code   from user u ");
		sql.append(" INNER JOIN user_role  r on u.id= r.roleid inner join role ro on ro.id = u.id ");
		sql.append(" where username = '" + userName + "' and password = " + password + " and status = 1 ");
		Statement statement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = connect.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			List<Object> listEntity = new ArrayList<>();
			while (resultSet.next()) {
				UserEntity entity = new UserEntity();
				entity.setFullName(resultSet.getString("fullname"));
				entity.setUserName(resultSet.getString("username"));
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setCode(resultSet.getString("code"));
				listEntity.add(entity);
				listEntity.add(roleEntity);
				return listEntity;
			}
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return new ArrayList<>();
	}

	@Override
	public List<UserEntity> findAllCustomer(Map<String, Object> params, BuilderSearchUser builderSearchUser,
			Pagable page) {
		StringBuilder sql = new StringBuilder("select * from user A ");
		sql.append(" where 1=1 and status = 0");
		sql = createSqlFindAlḷ(params, sql);
		List<UserEntity> list = this.findAll(sql.toString(), page);
		return list;
	}

	@Override
	public boolean deleteCustomer(Long id) {
		this.delete(id);
		return true;
	}

	@Override
	public UserEntity saveUser(UserEntity userEntity) {
		userEntity.setStatus(2);
		Long id = this.insert(userEntity);
		userEntity = this.findById(id);
		return userEntity;
	}

	@Override
	public UserEntity updateUser(UserEntity entity) {
		Long id = this.update(entity);
		entity = this.findById(id);
		return entity;
	}

}
