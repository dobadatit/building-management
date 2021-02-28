package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTO.AssignmentStaffDTO;
import service.AssignmentStaffService;
import service.impl.AssignmentStaffServiceImpl;
import utils.FromUtil;

@WebServlet(urlPatterns = { "/admin-assignmentStaff" })
public class AssignmentStaffController extends HttpServlet{

	private AssignmentStaffService service = new AssignmentStaffServiceImpl();
	private static final long serialVersionUID = -6474938295323175158L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		AssignmentStaffDTO assignmentStaffDTO = FromUtil.toModel(AssignmentStaffDTO.class, request);
		List<AssignmentStaffDTO> list = service.findByBuildingId(assignmentStaffDTO.getBuildingId());
		mapper.writeValue(response.getOutputStream(), list);
	}
}
