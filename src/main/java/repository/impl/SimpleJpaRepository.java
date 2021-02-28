package repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import annotation.Column;
import annotation.Entity;
import annotation.Table;
import mapper.ResultSetMapper;
import paging.Pagable;
import repository.JpaRepository;

public class SimpleJpaRepository<T> implements JpaRepository<T> {
	private EntityManagerImpl connect = new EntityManagerImpl();
	private Class<T> zClass;
	private ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();

	@SuppressWarnings("unchecked")
	public SimpleJpaRepository() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	@Override
	public List<T> findAll(Map<String, Object> params, Pagable page, Object... where) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		StringBuilder sql = new StringBuilder("select * from " + tableName + " A where 1=1 ");
		sql = this.createSqlFindAlḷ(params, sql);
		if (where != null && where.length > 0) {
			sql.append(where[0]);
		}
		if (page.getOffset() != null && page.getLimit() != null) {
			sql.append(" limit " + page.getOffset() + ", " + page.getLimit() + " ");
		}
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = connect.getConnection();
			statement = con.prepareStatement(sql.toString());
			resultSet = statement.executeQuery();
			return resultSetMapper.mapRow(resultSet, this.zClass);
		} catch (SQLException e) {
			return new ArrayList<>();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}

	}

	protected StringBuilder createSqlFindAlḷ(Map<String, Object> params, StringBuilder sql) {
		if (params != null && params.size() > 0) {
			String[] keys = new String[params.size()];
			Object[] values = new Object[params.size()];
			int i = 0;
			for (Map.Entry<String, Object> item : params.entrySet()) {
				keys[i] = item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for (int j = 0; j < keys.length; j++) {
				if (values[j] instanceof String && StringUtils.isNotBlank(values[j].toString())) {
					sql.append(" AND LOWER(A." + keys[j] + ") LIKE '%" + values[j].toString() + "%' ");
				} else if (values[j] instanceof Integer && values[j] != null) {
					sql.append(" AND LOWER(A." + keys[j] + ") = " + values[j].toString() + " ");
				} else if (values[j] instanceof Long && values[j] != null) {
					sql.append(" AND LOWER(A." + keys[j] + ") = " + values[j].toString() + " ");
				}
			}

		}
		return sql;
	}

	@Override
	public List<T> findAll(Map<String, Object> params, Object... where) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		StringBuilder sql = new StringBuilder("select * from " + tableName + " A where 1=1 ");
		sql = this.createSqlFindAlḷ(params, sql);
		if (where != null && where.length > 0) {
			sql.append(where[0]);
		}
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = connect.getConnection();
			statement = con.prepareStatement(sql.toString());
			resultSet = statement.executeQuery();
			return resultSetMapper.mapRow(resultSet, this.zClass);
		} catch (SQLException e) {
			return new ArrayList<>();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}
	}

	@Override
	public List<T> findAll(String result, Pagable page) {
		StringBuilder sql = new StringBuilder(result);
		if (page.getOffset() != null && page.getLimit() != null) {
			sql.append(" limit " + page.getOffset() + ", " + page.getLimit() + " ");
		}
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = connect.getConnection();
			statement = con.prepareStatement(sql.toString());
			resultSet = statement.executeQuery();
			return resultSetMapper.mapRow(resultSet, this.zClass);
		} catch (SQLException e) {
			return new ArrayList<>();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}

	}

	@Override
	public Long insert(Object object) {
		String sql = createInsertSQL();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			Long id = null;
			connection = connect.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			Class<?> aClass = object.getClass();
			Field[] fields = aClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				int index = i + 1;
				Field field = fields[i];
				field.setAccessible(true);
				Object h = field.get(object);
				preparedStatement.setObject(index, field.get(object));
			}
			
			Class<?> aClassParent = aClass.getSuperclass();
			int indexParent = fields.length + 1;
			while (aClassParent != null) {
				Field[] fieldParent = aClassParent.getDeclaredFields();
				for (int i = 0; i < fieldParent.length; i++) {
					Field field = fieldParent[i];
					field.setAccessible(true);
					preparedStatement.setObject(indexParent, field.get(object));
					indexParent++;
				}
				aClassParent = aClassParent.getSuperclass();
			}
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;

		} catch (SQLException | IllegalAccessException e) {
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
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;

	}

	public String createInsertSQL() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		StringBuilder fields = new StringBuilder();
		StringBuilder values = new StringBuilder();
		for (Field item : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				values.append(",");
			}
			if (item.isAnnotationPresent(Column.class)) {
				Column column = item.getAnnotation(Column.class);
				fields.append(column.name());
				values.append("?");
			}

		}
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field itemParent : parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					values.append(",");
				}
				if (itemParent.isAnnotationPresent(Column.class)) {
					Column column = itemParent.getAnnotation(Column.class);
					fields.append(column.name());
					values.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		String sql = "INSERT INTO " + tableName + "(" + fields + ") VALUES(" + values + ")";
		return sql;
	}

	@Override
	public T findById(Long id) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		String sql = "select * from " + tableName + " where id = ? ";
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = connect.getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setObject(1, id);
			resultSet = statement.executeQuery();
			try {
				return resultSetMapper.mapOneRow(resultSet, zClass);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
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
		return null;
	}

	@Override
	public Long update(Object object) {
		String sql = createUpdateSQL();
		Long id = null;
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			Object idValue = null;
			connection = connect.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			Class<?> aClass = object.getClass();
			Field[] fields = aClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				int index = i + 1;
				Field field = fields[i];
				field.setAccessible(true);
				statement.setObject(index, field.get(object));
			}
			Class<?> classParent = aClass.getSuperclass();
			int indexParent = fields.length + 1;
			while (classParent != null) {
				Field[] fieldParents = classParent.getDeclaredFields();
				for (int i = 0; i < fieldParents.length; i++) {
					Field field = fieldParents[i];
					field.setAccessible(true);
					statement.setObject(indexParent, field.get(object));
					if (field.isAnnotationPresent(Column.class)) {
						Column column = field.getAnnotation(Column.class);
						if (column.name().equals("id")) {
							idValue = field.get(object);
							id = (Long) idValue;
						}
					}
					indexParent++;
				} 
				statement.setObject(indexParent, idValue);
				classParent = classParent.getSuperclass();
			}
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
		
			connection.commit();
			return id;
		} catch (SQLException | IllegalAccessException e) {
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
		return null;
	}

	private String createUpdateSQL() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		Field[] aClass = zClass.getDeclaredFields();
		StringBuilder fields = new StringBuilder();
		for (Field field : aClass) {
//			if (fields.length() > 0) {
//				fields.append(",");
//			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name() + " = ? ,");
			}
		}
		Class<?> aClassParent = zClass.getSuperclass();
		while (aClassParent != null) {
			Field[] fieldParent = aClassParent.getDeclaredFields();
			for (Field field : fieldParent) {
//				if (fields.length() > 0) {
//					fields.append(",");
//				}
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
//					if (!column.name().equals("id")) {
					fields.append(column.name() + " = ? ,");
//					}

				}
			}
			aClassParent = aClassParent.getSuperclass();
		}
		fields.deleteCharAt(fields.length() - 1);

		String sql = "UPDATE " + tableName + " SET " + fields + " where id = ? ";

		return sql;
	}

	@Override
	public boolean delete(Long id) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table tableClass = zClass.getAnnotation(Table.class);
			tableName = tableClass.name();
		}
		String sql = " DELETE FROM " + tableName +"  WHERE id = ? ";
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = connect.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteIdForeignKey(Long id ,String sql) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = connect.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<T> findById(String sql, Long id) {
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = connect.getConnection();
			statement = connection.prepareStatement(sql.toString());
			statement.setObject(1, id);
			resultSet = statement.executeQuery();
			return resultSetMapper.mapRow(resultSet, this.zClass);
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
		return null;
	}

	@Override
	public T findById(String sql) {
		Statement statement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = connect.getConnection();
			statement =connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			try {
				return resultSetMapper.mapOneRow(resultSet, this.zClass);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			int count = 0;
			connection = connect.getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return 0;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return 0;
			}
		}
	}

}
