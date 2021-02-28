package repository;

import java.util.List;

import entity.RentAreaEntity;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity> {
	public Long saveRentArea(RentAreaEntity areaEntity);
	public boolean deleteRentArea(Long id);
	public List<RentAreaEntity> findByBuildingId(Long id);

}
