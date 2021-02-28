package service;

import java.util.List;

import DTO.AssignmentStaffDTO;

public interface AssignmentStaffService {
	public AssignmentStaffDTO insert(AssignmentStaffDTO assignmentStaffDTO);
	public List<AssignmentStaffDTO> findByBuildingId(Long id);
}
