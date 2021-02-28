package service.impl;

import java.util.ArrayList;
import java.util.List;

import DTO.AssignmentStaffDTO;
import converter.AssignmentStaffConverter;
import entity.AssigmnetStaffEntity;
import repository.AssignmentStaffReposotory;
import repository.impl.AssignmentStaffRepositoryImpl;
import service.AssignmentStaffService;

public class AssignmentStaffServiceImpl implements AssignmentStaffService {
	private AssignmentStaffReposotory repository = new AssignmentStaffRepositoryImpl();
	private AssignmentStaffConverter converter = new AssignmentStaffConverter();

	@Override
	public AssignmentStaffDTO insert(AssignmentStaffDTO assignmentStaffDTO) {
		repository.deleteAssignmentStaff(assignmentStaffDTO.getBuildingId());
		for (long item : assignmentStaffDTO.getStaffs()) {
			AssigmnetStaffEntity entity = converter.convertoEntity(assignmentStaffDTO);
			entity.setStaffId(item);
			AssigmnetStaffEntity assigmnetStaffEntity = repository.insertAssignment(entity);
			AssignmentStaffDTO dto = new AssignmentStaffDTO();
//			dto = converter.convertToDTO(assigmnetStaffEntity);
		}
		return assignmentStaffDTO;
	}

	@Override
	public List<AssignmentStaffDTO> findByBuildingId(Long id) {
		List<AssigmnetStaffEntity> listEntity = repository.findByBuildingId(id);
		List<AssignmentStaffDTO> listDTO = new ArrayList<>();
		AssignmentStaffDTO assignmentStaffDTO = new AssignmentStaffDTO();
		for (AssigmnetStaffEntity assigmnetStaffEntity : listEntity) {
			 assignmentStaffDTO = converter.convertToDTO(assigmnetStaffEntity);
			listDTO.add(assignmentStaffDTO);
		}
		return listDTO;
	}



}
