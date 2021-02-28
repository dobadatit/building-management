package repository.impl;

import java.util.ArrayList;
import java.util.List;

import entity.AssigmnetStaffEntity;
import repository.AssignmentStaffReposotory;

public class AssignmentStaffRepositoryImpl extends SimpleJpaRepository<AssigmnetStaffEntity> implements AssignmentStaffReposotory{

	@Override
	public boolean deleteAssignmentStaff(Long id) {
		String sql = " DELETE FROM assignmentstaff WHERE buildingid = ? ";
		return this.deleteIdForeignKey(id, sql);
	}

	@Override
	public AssigmnetStaffEntity insertAssignment(AssigmnetStaffEntity assigmnetStaffEntity) {
		Long id = this.insert(assigmnetStaffEntity);
		AssigmnetStaffEntity entity = this.findById(id);
		return entity;
	}

	@Override
	public List<AssigmnetStaffEntity> findByBuildingId(Long id) {
		String sql ="select * from assignmentstaff where buildingid= ?";
		List<AssigmnetStaffEntity> list = new ArrayList<>();
		list = this.findById(sql, id);
		return list;
	
	}
	


}
