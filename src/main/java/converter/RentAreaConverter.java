package converter;

import org.modelmapper.ModelMapper;

import DTO.RentAreaDTO;
import entity.RentAreaEntity;

public class RentAreaConverter {

	public RentAreaDTO convertToDTO(RentAreaEntity areaEntity) {
		ModelMapper mapper = new ModelMapper();
		RentAreaDTO areaDTO = mapper.map(areaEntity, RentAreaDTO.class);
		return areaDTO; 

	}
	public RentAreaEntity convertoEntity(RentAreaDTO areaDTO) {
		
		ModelMapper mapper = new ModelMapper();
		RentAreaEntity areaEntity = mapper.map(areaDTO,RentAreaEntity.class);
		return areaEntity;
	}
}
