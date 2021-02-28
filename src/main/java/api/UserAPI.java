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
import DTO.UserDTO;
import builder.BuilderSearchUser;
import paging.Pagable;
import paging.PageRequest;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.FromUtil;
import utils.HttpUtil;

@WebServlet(urlPatterns = "/api-user")
public class UserAPI extends HttpServlet {
	private UserService userService = new UserServiceImpl();
	private static final long serialVersionUID = -3530631839695245976L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserDTO userDTO = FromUtil.toModel(UserDTO.class, request);
		BuilderSearchUser builderSearchUser = new BuilderSearchUser.Builder().setBuildingId(userDTO.getBuildingId())
				.setDemand(userDTO.getDemand()).setEmail(userDTO.getEmail()).setFullName(userDTO.getFullName())
				.setPhoneNumber(userDTO.getPhoneNumber()).setUserName(userDTO.getUserName()).build();
		Pagable page = new PageRequest(userDTO.getPage(), userDTO.getLimit());
//		List<UserDTO> list = userService.findAllCustomer(page, builderSearchUser);
		List<UserDTO> list = userService.findAllStaff(page, builderSearchUser);
		mapper.writeValue(response.getOutputStream(), list);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		userService.deleteCustomer(userDTO.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		UserDTO dto = userService.saveUser(userDTO);
		mapper.writeValue(response.getOutputStream(), dto);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		UserDTO dto = userService.updateUser(userDTO);
		mapper.writeValue(response.getOutputStream(), dto);
	}
}
