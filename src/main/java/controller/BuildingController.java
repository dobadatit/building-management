package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.BuildingDTO;
import builder.BuilderSearchBuilder;
import paging.Pagable;
import paging.PageRequest;
import service.BuildingService;
import service.impl.BuildingServiceImpl;
import utils.FromUtil;

@WebServlet(urlPatterns = { "/admin-building" })
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 2686801510274002166L;
	private BuildingService buildingService = new BuildingServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		BuildingDTO buildingDTO = FromUtil.toModel(BuildingDTO.class, request);
		String url = "";
		if (action != null && action.equals("LIST")) {
			BuilderSearchBuilder builderSearchBuilder = new BuilderSearchBuilder.Builder()
					.setName(buildingDTO.getName()).setDistrict(buildingDTO.getDistrict())
					.setBuildingArea(buildingDTO.getBuildingArea()).setStreet(buildingDTO.getStreet())
					.setWard(buildingDTO.getWard()).setNumberOfBasement(buildingDTO.getNumberOfBasement())
					.setBuildingTypes(buildingDTO.getBuildingTypes()).setAreaRentFrom(buildingDTO.getAreaRentFrom())
					.setAreaRentTo(buildingDTO.getAreaRentTo()).setCostRentFrom(buildingDTO.getCostRentFrom())
					.setCostRentTo(buildingDTO.getCostRentTo()).setStaffId(buildingDTO.getStaffId())
					.setiDs(buildingDTO.getIds()).build();
			Pagable page = new PageRequest(buildingDTO.getPage(), buildingDTO.getLimit());
			List<BuildingDTO> buildings = buildingService.findAll(page, builderSearchBuilder);
			buildingDTO.setTotalItem(buildingService.getTotalItem());
			if (buildingDTO.getLimit() != null) {
				buildingDTO.setTotalPage((int) Math.ceil((double) buildingDTO.getTotalItem() / buildingDTO.getLimit()));
			}
			request.setAttribute("buildings", buildings);
			url = ("/views/admin/building/list.jsp");
		} else if (action != null && action.equals("EDIT")) {
			if (buildingDTO.getId() != null) {
				// find by id
				buildingDTO = buildingService.findById(buildingDTO.getId());

				// update
			}
			url = ("/views/admin/building/edit.jsp");
		} else {
			url = ("/views/admin/home.jsp");
		}
		request.setAttribute("districts", buildingService.getDistrict());
		request.setAttribute("buildingTypes", buildingService.getBuildingTypes());
		request.setAttribute("model", buildingDTO);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
