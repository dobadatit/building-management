package service;

import java.util.List;

import DTO.RentAreaDTO;

public interface RentAreaService {
	
	public List<RentAreaDTO> findByBuildingId(Long id);
}
