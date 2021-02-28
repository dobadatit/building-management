package DTO;

public class UserDTO extends AbstractDTO{
private String userName;
private String fullName;
private String status;
private String checked;
private String buildingId;
private String staffId;
private String password;
private RoleDTO roleDTO = new RoleDTO();
private String email;
private String demand;
private String phoneNumber;



public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getDemand() {
	return demand;
}
public void setDemand(String demand) {
	this.demand = demand;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public RoleDTO getRoleDTO() {
	return roleDTO;
}
public void setRoleDTO(RoleDTO roleDTO) {
	this.roleDTO = roleDTO;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getStaffId() {
	return staffId;
}
public void setStaffId(String staffId) {
	this.staffId = staffId;
}
public String getBuildingId() {
	return buildingId;
}
public void setBuildingId(String buildingId) {
	this.buildingId = buildingId;
}
public String getChecked() {
	return checked;
}
public void setChecked(String checked) {
	this.checked = checked;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}

public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}



}
