
package converter;

import org.modelmapper.ModelMapper;

import DTO.AssignmentStaffDTO;
import entity.AssigmnetStaffEntity;




public class AssignmentStaffConverter {

	public AssignmentStaffDTO convertToDTO(AssigmnetStaffEntity areaEntity) {
		ModelMapper mapper = new ModelMapper();
		 AssignmentStaffDTO assignmentStaffDTO = mapper.map(areaEntity, AssignmentStaffDTO.class);
		 return assignmentStaffDTO;

	}
	public AssigmnetStaffEntity convertoEntity(AssignmentStaffDTO areaDTO) {
		ModelMapper mapper = new ModelMapper();
		AssigmnetStaffEntity areaEntity = mapper.map(areaDTO,AssigmnetStaffEntity.class);
		return areaEntity;
	}
}
