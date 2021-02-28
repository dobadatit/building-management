package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTO.AssignmentStaffDTO;
import service.AssignmentStaffService;
import service.impl.AssignmentStaffServiceImpl;
import utils.HttpUtil;

@WebServlet(urlPatterns = "/api-user-assignment")
public class AssignmentStaffAPI extends HttpServlet {

	private static final long serialVersionUID = 8866999819552973783L;

	private AssignmentStaffService service = new AssignmentStaffServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		AssignmentStaffDTO dto = HttpUtil.of(request.getReader()).toModel(AssignmentStaffDTO.class);
		AssignmentStaffDTO staffDTO = service.insert(dto);
		mapper.writeValue(response.getOutputStream(), staffDTO);
	}
}
