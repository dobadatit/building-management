package repository.impl;

import java.util.ArrayList;
import java.util.List;

import entity.RentAreaEntity;
import repository.RentAreaRepository;

public class RentAreaRepositoryImpl extends SimpleJpaRepository<RentAreaEntity> implements RentAreaRepository {
	@Override
	public Long saveRentArea(RentAreaEntity areaEntity) {
		return this.insert(areaEntity);
	}

	@Override
	public boolean deleteRentArea(Long id) {
		String sql = " DELETE FROM rentarea WHERE buildingid = ? ";
		return this.deleteIdForeignKey(id, sql);
	}

	@Override
	public List<RentAreaEntity> findByBuildingId(Long id) {
		String sql = "select * from rentarea where buildingid = ?";
		List<RentAreaEntity> list = new ArrayList<>();
		list = this.findById(sql, id);
		return list;
	}



}
