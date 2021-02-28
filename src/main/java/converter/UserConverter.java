package converter;

import org.modelmapper.ModelMapper;

import DTO.UserDTO;
import entity.UserEntity;

public class UserConverter {
	public UserDTO convertToDTO(UserEntity userEntity) {
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(userEntity, UserDTO.class);
		return userDTO; 

	}
	public UserEntity convertoEntity(UserDTO areaDTO) {
		
		ModelMapper mapper = new ModelMapper();
		UserEntity userEntity= mapper.map(areaDTO,UserEntity.class);
		return userEntity;
	}
}
