package enums;

public enum BuildingTypesEnum {
	TANG_TRET("Tầng Trệt"),
	NGUYEN_CAN("Nguyên Căn"),
	NOI_THAT("Nội Thất");


	private final String buildingTypesValue;
	BuildingTypesEnum(String buildingTypesValue) {
		this.buildingTypesValue = buildingTypesValue;
	}
	public String getBuildingTypesValue() {
		return buildingTypesValue;
	}
	public static void main(String[] args) {
		for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
			System.out.println(item.toString());
			System.out.println("AAA:" +item.getBuildingTypesValue());
		}
	}
	
}
