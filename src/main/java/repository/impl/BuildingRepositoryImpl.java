package repository.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import builder.BuilderSearchBuilder;
import entity.BuildingEntity;
import entity.RentAreaEntity;
import paging.Pagable;
import repository.AssignmentStaffReposotory;
import repository.BuildingRepository;
import repository.RentAreaRepository;

public class BuildingRepositoryImpl extends SimpleJpaRepository<BuildingEntity> implements BuildingRepository {
	private EntityManagerImpl connect = new EntityManagerImpl();
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();
	private AssignmentStaffReposotory assignment = new AssignmentStaffRepositoryImpl();
    private RentAreaRepository rentArea = new RentAreaRepositoryImpl();
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, BuilderSearchBuilder builder, Pagable page) {
		StringBuilder sql = new StringBuilder("Select * from building A ");
		if (StringUtils.isNotBlank(builder.getStaffId())) {
			sql.append(" INNER JOIN assignmentstaff s on s.buildingid = A.id ");
		}
		sql.append(" WHERE 1=1 ");
		String result = "";
		sql = createSqlFindAlḷ(params, sql);
		result = buildSpecialSQL(builder);
		sql.append(result);
		return this.findAll(sql.toString(), page);

	}

	public String buildSpecialSQL(BuilderSearchBuilder builder) {
		StringBuilder sql = new StringBuilder("");
		if (StringUtils.isNotBlank(builder.getCostRentFrom())) {
			sql.append(" AND costrent >= " + builder.getCostRentFrom() + " ");
		}
		if (StringUtils.isNotBlank(builder.getCostRentTo())) {
			sql.append(" AND costrent <= " + builder.getCostRentTo() + " ");
		}
		if (builder.getBuildingTypes().length > 0 && StringUtils.isNotBlank(builder.getBuildingTypes()[0])) {

			/*
			 * Java 7
			 * 
			 * int i =0; sql.append("and ("); for (String item : builder.getBuildingTypes())
			 * { if (i ==0) { sql.append("type like '%"+item +"%'"); }else {
			 * sql.append(" OR type like '%"+item +"%'"); } i++; } sql.append(")");
			 */
			// java 8
			sql.append(" AND (type like '%" + builder.getBuildingTypes()[0] + "%'");
			Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
					.forEach(item -> sql.append(" OR type like '%" + item + "%'"));
			sql.append(") ");
		}
		if (StringUtils.isNotBlank(builder.getAreaRentFrom()) || StringUtils.isNotBlank(builder.getAreaRentTo())) {
			sql.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id ");
			if (builder.getAreaRentFrom() != null) {
				sql.append(" AND ra.value >= 300 ");
			}
			if (builder.getAreaRentTo() != null) {
				sql.append("AND ra.value <= 500");
			}
			sql.append("));");

		}

		if (StringUtils.isNotBlank(builder.getStaffId())) {
			sql.append(" AND s.staffid =  " + builder.getStaffId());
		}
		return sql.toString();
	}

	@Override
	public BuildingEntity save(BuildingEntity entity,BuilderSearchBuilder builder) {
		String type = null;
		if (builder.getBuildingTypes() != null) {
			type = String.join(",", builder.getBuildingTypes());
		}
		entity.setType(type);
		Long id = this.insert(entity);
		if (builder.getAreaRent() != null) {
			String[] rentAreas = builder.getAreaRent().split(",");
			for (String string : rentAreas) {
				if (StringUtils.isNotBlank(string)) {
			    RentAreaEntity rent = new RentAreaEntity();
			    rent.setValue(Integer.parseInt(string));
			    rent.setBuildingId(id);
				rentAreaRepository.saveRentArea(rent);
			}
			}
		}
		return this.findById(id);

	}

	@Override
	public BuildingEntity updateBuilding(BuildingEntity entity) {
		Long id = this.update(entity);
		return this.findById(id);
	}

	@Override
	public boolean deleteBuilding(Long id) {
		if (assignment.deleteAssignmentStaff(id) == true && rentArea.deleteRentArea(id) == true) {
			return this.delete(id);
		}
		return false;
	}
	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM building";
		return count(sql);
	}
}
