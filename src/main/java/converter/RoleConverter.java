package converter;

import org.modelmapper.ModelMapper;

import DTO.RoleDTO;
import entity.RoleEntity;

public class RoleConverter {
	public RoleDTO convertToDTO(RoleEntity userEntity) {
		ModelMapper mapper = new ModelMapper();
		RoleDTO userDTO = mapper.map(userEntity, RoleDTO.class);
		return userDTO;

	}

	public RoleEntity convertoEntity(RoleDTO areaDTO) {

		ModelMapper mapper = new ModelMapper();
		RoleEntity userEntity = mapper.map(areaDTO, RoleEntity.class);
		return userEntity;
	}
}
