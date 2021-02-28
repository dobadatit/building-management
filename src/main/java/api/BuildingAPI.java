package api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTO.BuildingDTO;
import builder.BuilderSearchBuilder;
import paging.Pagable;
import paging.PageRequest;
import service.BuildingService;
import service.impl.BuildingServiceImpl;
import utils.FromUtil;
import utils.HttpUtil;


@WebServlet(urlPatterns = "/api-building")
public class BuildingAPI extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuildingService buildingService = new BuildingServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = FromUtil.toModel(BuildingDTO.class, request);
		BuilderSearchBuilder builderSearchBuilder = new BuilderSearchBuilder.Builder()
				.setName(buildingDTO.getName()).setDistrict(buildingDTO.getDistrict())
				.setNumberOfBasement(buildingDTO.getNumberOfBasement()).setBuildingArea(buildingDTO.getBuildingArea())
				.setAreaRentFrom(buildingDTO.getAreaRentFrom()).setAreaRentTo(buildingDTO.getAreaRentTo())
				.setCostRentFrom(buildingDTO.getCostRentFrom()).setCostRentTo(buildingDTO.getAreaRentTo())
				.setBuildingTypes(buildingDTO.getBuildingTypes())
				.setStaffId(buildingDTO.getStaffId())
				.setWard(buildingDTO.getWard()).setStreet(buildingDTO.getStreet())
				.build();
		Pagable page =  new PageRequest(buildingDTO.getPage(),buildingDTO.getLimit());
		List<BuildingDTO> buildings = buildingService.findAll(page, builderSearchBuilder);
		mapper.writeValue(response.getOutputStream(), buildings);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		BuilderSearchBuilder builderSearchBuilder = new BuilderSearchBuilder.Builder()
				.setName(buildingDTO.getName()).setDistrict(buildingDTO.getDistrict())
				.setNumberOfBasement(buildingDTO.getNumberOfBasement()).setBuildingArea(buildingDTO.getBuildingArea())
				.setAreaRentFrom(buildingDTO.getAreaRentFrom()).setAreaRentTo(buildingDTO.getAreaRentTo())
				.setCostRentFrom(buildingDTO.getCostRentFrom()).setCostRentTo(buildingDTO.getAreaRentTo())
				.setBuildingTypes(buildingDTO.getBuildingTypes())
				.setStaffId(buildingDTO.getStaffId())
				.setWard(buildingDTO.getWard()).setStreet(buildingDTO.getStreet()).setAreaRent(buildingDTO.getAreaRent())
				.build();
		BuildingDTO building = buildingService.save(buildingDTO,builderSearchBuilder);
		mapper.writeValue(response.getOutputStream(), building);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		BuildingDTO building = buildingService.updateBuilding(buildingDTO);
		mapper.writeValue(response.getOutputStream(), building);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingService.deleteBuilding(buildingDTO.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");

	}
}
