package converter;

import org.modelmapper.ModelMapper;

import DTO.BuildingDTO;
import entity.BuildingEntity;

public class BuildingConverter {

	
	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper mapper = new ModelMapper();
		BuildingDTO buildingDTO = mapper.map(buildingEntity, BuildingDTO.class);
		if (buildingEntity.getType() != null) {
			String str = buildingEntity.getType();
			String[] namesArray = null;
			namesArray = str.split(",");
			buildingDTO.setBuildingTypes(namesArray);
		}
//		if (buildingEntity.getNumberOfBasement() != null) {
//			buildingDTO.setNumberOfBasement(String.valueOf(buildingEntity.getNumberOfBasement()));
//		}
//		if (buildingEntity.getBuildingArea() != null) {
//			buildingDTO.setBuildingArea(buildingEntity.getBuildingArea());
//		}
		return buildingDTO; 

	}
	public BuildingEntity convertoEntity(BuildingDTO buildingDTO) {
		
		ModelMapper mapper = new ModelMapper();
		BuildingEntity buildingEntity = mapper.map(buildingDTO,BuildingEntity.class);
		if (buildingDTO.getBuildingTypes() != null) {
			String type = String.join(" ", buildingDTO.getBuildingTypes());
			buildingEntity.setType(type);
		}
		return buildingEntity;
	}
}
