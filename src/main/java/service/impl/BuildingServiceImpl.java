package service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import DTO.BuildingDTO;
import DTO.RentAreaDTO;
import builder.BuilderSearchBuilder;
import converter.BuildingConverter;
import entity.BuildingEntity;
import enums.BuildingTypesEnum;
import enums.DistrictsEnum;
import paging.Pagable;
import repository.BuildingRepository;
import repository.impl.BuildingRepositoryImpl;
import service.BuildingService;
import service.RentAreaService;

public class BuildingServiceImpl implements BuildingService {
	private BuildingRepository repository = new BuildingRepositoryImpl();
	private BuildingConverter buildingConverter = new BuildingConverter();
	private RentAreaService rentAreaService = new RentAreaServiceImpl();

	@Override
	public List<BuildingDTO> findAll(Pagable page, BuilderSearchBuilder builder) {
		Map<String, Object> properties = new HashMap<>();

		properties = convertToMapProperties(builder);
		List<BuildingEntity> resultEntity = repository.findAll(properties, builder, page);
		List<BuildingDTO> results = new ArrayList<>();
		for (BuildingEntity buildingEntity : resultEntity) {
			List<RentAreaDTO> listRent = rentAreaService.findByBuildingId(buildingEntity.getId());
			StringBuilder str = new StringBuilder();
			for (RentAreaDTO item : listRent) {
				if (str.length() > 1) {
					str.append(",");
				}
				str.append(item.getValue());
			}
			BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
			buildingDTO.setAreaRent(str.toString());
			results.add(buildingDTO);
		}
		return results;
		// return resultEntity.stream().map(item ->
		// buildingConverter.convertToDTO(item)).collect(Collectors.toList());

	}

	private Map<String, Object> convertToMapProperties(BuilderSearchBuilder builder) {
		Map<String, Object> property = new HashMap<>();
		try {
			Field[] files = BuilderSearchBuilder.class.getDeclaredFields();
			for (Field field : files) {
				if (!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
						&& !field.getName().startsWith("areaRent") && !field.getName().equals("staffId")) {
					field.setAccessible(true); // cho truy cap private
					if (field.get(builder) instanceof String) {
						if (field.getName().equals("buildingArea") || field.getName().equals("numberOfBasement")) {
							if (field.get(builder) != null && StringUtils.isNotEmpty((String) field.get(builder))) {
								property.put(field.getName().toLowerCase(),
										Integer.parseInt((String) field.get(builder)));
							}
						} else {
							property.put(field.getName().toLowerCase(), field.get(builder));
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return property;

	}

	@Override
	public List<BuildingDTO> findAll(BuilderSearchBuilder builder) {
		Map<String, Object> properties = new HashMap<>();
		properties = convertToMapProperties(builder);
		List<BuildingEntity> resultEntity = repository.findAll(properties, builder);
		return resultEntity.stream().map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
	}

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO, BuilderSearchBuilder builder) {
		BuildingEntity buildingEntity = buildingConverter.convertoEntity(buildingDTO);
		BuildingEntity entity = repository.save(buildingEntity, builder);
		buildingDTO = buildingConverter.convertToDTO(entity);
		return buildingDTO;
	}

	@Override
	public BuildingDTO updateBuilding(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertoEntity(buildingDTO);
		BuildingEntity entity = repository.updateBuilding(buildingEntity);
		buildingDTO = buildingConverter.convertToDTO(entity);
		return buildingDTO;
	}

	@Override
	public boolean deleteBuilding(Long[] ids) {
		for (Long item : ids) {
			repository.deleteBuilding(item);
		}
		return true;
	}

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity entity = repository.findById(id);
		BuildingDTO dto = buildingConverter.convertToDTO(entity);
		return dto;
	}

	@Override
	public Map<String, String> getDistrict() {
		Map<String, String> districts = new HashMap<>();
		for (DistrictsEnum item : DistrictsEnum.values()) {
			districts.put(item.toString(), item.getDistrictValue());
		}
		return districts;
	}

	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
			buildingTypes.put(item.toString(), item.getBuildingTypesValue());
		}
		return buildingTypes;
	}

	@Override
	public Integer getTotalItem() {
		return repository.getTotalItem();
	}

}
