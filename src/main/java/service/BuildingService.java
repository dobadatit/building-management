package service;

import java.util.List;
import java.util.Map;

import DTO.BuildingDTO;
import builder.BuilderSearchBuilder;
import paging.Pagable;

public interface BuildingService {
	public List<BuildingDTO> findAll(Pagable page, BuilderSearchBuilder builder);

	public List<BuildingDTO> findAll(BuilderSearchBuilder builder);

	public BuildingDTO save(BuildingDTO buildingDTO, BuilderSearchBuilder builder);

	public BuildingDTO updateBuilding(BuildingDTO buildingDTO);

	public boolean deleteBuilding(Long[] ids);

	public BuildingDTO findById(Long id);

	Map<String, String> getDistrict();

	Map<String, String> getBuildingTypes();

	public Integer getTotalItem();

}
