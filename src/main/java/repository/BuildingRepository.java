package repository;

import java.util.List;
import java.util.Map;

import builder.BuilderSearchBuilder;
import entity.BuildingEntity;
import paging.Pagable;

public interface BuildingRepository extends JpaRepository<BuildingEntity> {
	
	public List<BuildingEntity> findAll(Map<String, Object> params,BuilderSearchBuilder builder,Pagable page);
	public BuildingEntity save(BuildingEntity entity,BuilderSearchBuilder builder);
	public BuildingEntity updateBuilding(BuildingEntity entity);
	public boolean deleteBuilding(Long id);
	int getTotalItem();
}
