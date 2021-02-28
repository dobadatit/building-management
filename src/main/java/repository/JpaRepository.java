package repository;

import java.util.List;
import java.util.Map;

import paging.Pagable;

public interface JpaRepository<T> {
	public List<T> findAll(Map<String, Object> params, Pagable page, Object... where);

	public List<T> findAll(Map<String, Object> params, Object... where);

	public List<T> findAll(String sql, Pagable page);

	public Long insert(Object object);

	public Long update(Object object);

	public T findById(Long id);

	public T findById(String sql);
	

	public List<T> findById(String sql, Long id);

	public boolean delete(Long id);

	public boolean deleteIdForeignKey(Long id, String sql);

	int count(String sql, Object... parameters);
	

}
