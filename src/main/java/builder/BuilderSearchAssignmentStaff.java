package builder;

public class BuilderSearchAssignmentStaff {
	private Long[] staffs;

	public Long[] getStaffs() {
		return staffs;
	}

	public BuilderSearchAssignmentStaff(Builder builder) {
		this.staffs = builder.staffs;
	}

	public static class Builder {
		private Long[] staffs;

		public Builder setStaffs(Long[] staffs) {
			this.staffs = staffs;
			return this;
		}

		public BuilderSearchAssignmentStaff build() {
			return new BuilderSearchAssignmentStaff(this);
		}
	}

}
