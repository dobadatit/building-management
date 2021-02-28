package repository;

import java.util.List;

import entity.AssigmnetStaffEntity;

public interface AssignmentStaffReposotory extends JpaRepository<AssigmnetStaffEntity> {
	public boolean deleteAssignmentStaff(Long id);

	public AssigmnetStaffEntity insertAssignment(AssigmnetStaffEntity assigmnetStaffEntity);
	
	public List<AssigmnetStaffEntity> findByBuildingId(Long id);
	
	

}
