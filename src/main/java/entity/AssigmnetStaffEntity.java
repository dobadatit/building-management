package entity;

import annotation.Column;
import annotation.Entity;
import annotation.Table;

@Entity
@Table(name = "assignmentstaff")
public class AssigmnetStaffEntity  {
	@Column(name = "buildingid")
	private Long buildingId;
	
	@Column(name = "staffid")
	private Long staffId;
	@Column(name = "id")
	private Long id;
	
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

}
