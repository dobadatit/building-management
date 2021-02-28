package builder;

public class BuilderSearchUser {
	private String buildingId;
	private String email;
	private String demand;
	private String phoneNumber;
	private String userName;
	private String fullName;
	
	

	public String getEmail() {
		return email;
	}
	public String getDemand() {
		return demand;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public String getFullName() {
		return fullName;
	}
	public String getBuildingId() {
		return buildingId;
	}
	private BuilderSearchUser(Builder builder) {
		this.buildingId = builder.buildingId;
		this.demand = builder.demand;
		this.email = builder.email;
		this.fullName = builder.fullName;
		this.userName = builder.userName;
		this.phoneNumber = builder.phoneNumber;
	}
	public static class Builder {
		private String buildingId;
		private String email;
		private String demand;
		private String phoneNumber;
		private String userName;
		private String fullName;
		
		

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder setDemand(String demand) {
			this.demand = demand;
			return this;
		}
		public Builder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		public Builder setUserName(String userName) {
			this.userName = userName;
			return this;
		}
		public Builder setFullName(String fullName) {
			this.fullName = fullName;
			return this;
		}
		public Builder setBuildingId(String buildingId) {
			this.buildingId = buildingId;
			return this;
		}
		public BuilderSearchUser build() {
			return new BuilderSearchUser(this);
		}
		
	}
	

}
