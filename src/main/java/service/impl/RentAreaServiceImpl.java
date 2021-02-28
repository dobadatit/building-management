package service.impl;

import java.util.ArrayList;
import java.util.List;

import DTO.RentAreaDTO;
import converter.RentAreaConverter;
import entity.BuildingEntity;
import entity.RentAreaEntity;
import repository.RentAreaRepository;
import repository.impl.RentAreaRepositoryImpl;
import service.RentAreaService;

public class RentAreaServiceImpl implements RentAreaService {
	private RentAreaRepository rentArea = new RentAreaRepositoryImpl();
	private RentAreaConverter rentAreaConverter = new RentAreaConverter();

	@Override
	public List<RentAreaDTO> findByBuildingId(Long id) {
		List<RentAreaEntity> listEntity = rentArea.findByBuildingId(id);
		List<RentAreaDTO> results = new ArrayList<>();
		for (RentAreaEntity rentAreaEntity : listEntity) {
			RentAreaDTO areaDTO = rentAreaConverter.convertToDTO(rentAreaEntity);
			results.add(areaDTO);
		}
		return results;
	}

}
