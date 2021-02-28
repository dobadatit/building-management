package mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import annotation.Column;
import annotation.Entity;
import annotation.Table;

public class ResultSetMapper<T> {


	public List<T> mapRow(ResultSet resultSet, Class<T> zClass) {
		List<T> list = new ArrayList<>();
		try {
			if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
				ResultSetMetaData metaData = resultSet.getMetaData();
				Field[] field = zClass.getDeclaredFields();
				while (resultSet.next()) {
					T object = zClass.newInstance();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						String columnName = metaData.getColumnName(i+1);
						Object columnValue = resultSet.getObject(i+1);
						ColumnModel columnModel = new ColumnModel();
						columnModel.setColumnName(columnName);
						columnModel.setColumValue(columnValue);
						map(field, columnModel, object);
						Class<?> parentClass = zClass.getSuperclass();
						while(parentClass != null) {
							Field[] fileParents = parentClass.getDeclaredFields();
							map(fileParents, columnModel, object);
							parentClass = parentClass.getSuperclass();
						}
						
				}
					list.add(object);
				}
			}
		} catch (SQLException | InstantiationException |IllegalAccessException  e) {
			System.out.println(e.getMessage());
		} 
		return list;
	}
	@SuppressWarnings("unchecked")
	public void map(Field[] field,ColumnModel columnModel,Object... objects) {
		T object =(T) objects[0];
		for (Field item : field) {
			if (item.isAnnotationPresent(Column.class)) {
				Column column = item.getAnnotation(Column.class);
				if (column.name().equals(columnModel.columnName) && columnModel.columnValue != null) {
					try {
						BeanUtils.setProperty(object, item.getName(), columnModel.columnValue);
					} catch (IllegalAccessException|InvocationTargetException e) {
						e.printStackTrace();
					} 
				}
			}
		}
	}
	public T mapOneRow(ResultSet resultSet, Class<T> zClass) throws InstantiationException, IllegalAccessException {
		try {
			if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
				ResultSetMetaData metaData = resultSet.getMetaData();
				Field[] field = zClass.getDeclaredFields();
				while (resultSet.next()) {
					T object = zClass.newInstance();
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						String columnName = metaData.getColumnName(i+1);
						Object columnValue = resultSet.getObject(i+1);
						ColumnModel columnModel = new ColumnModel();
						columnModel.setColumnName(columnName);
						columnModel.setColumValue(columnValue);
						map(field, columnModel, object);
						Class<?> parentClass = zClass.getSuperclass();
						while(parentClass != null) {
							Field[] fileParents = parentClass.getDeclaredFields();
							map(fileParents, columnModel, object);
							parentClass = parentClass.getSuperclass();
						}
						
				}
					return object;
				}
			}
		} catch (SQLException | InstantiationException |IllegalAccessException  e) {
			System.out.println(e.getMessage());
		} 
		return (T) zClass.newInstance();
	}
	static class ColumnModel {
		private String columnName;
		private Object columnValue;
		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public Object getColumValue() {
			return columnValue;
		}

		public void setColumValue(Object columnValue) {
			this.columnValue = columnValue;
		}

	}
}
