package DTO;

public class RentAreaDTO extends AbstractDTO{
    private int value;
    private Long buildingId;
    
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
    
}
