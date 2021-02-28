package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.UserDTO;
import builder.BuilderSearchUser;
import paging.Pagable;
import paging.PageRequest;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.FromUtil;

@WebServlet(urlPatterns = { "/admin-user" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 2686801510274002166L;
	private UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "";
		UserDTO userDTO = FromUtil.toModel(UserDTO.class, request);
		if (action != null && action.equals("LIST")) {
			BuilderSearchUser builderSearchUser = new BuilderSearchUser.Builder().setBuildingId(userDTO.getBuildingId())
					.setDemand(userDTO.getDemand()).setEmail(userDTO.getEmail()).setFullName(userDTO.getFullName())
					.setPhoneNumber(userDTO.getPhoneNumber()).setUserName(userDTO.getUserName()).build();
			Pagable page = new PageRequest(userDTO.getPage(), userDTO.getLimit());
			List<UserDTO> list = userService.findAllCustomer(page, builderSearchUser);
			request.setAttribute("users", list);
			url = ("/views/admin/user/list.jsp");
		}else if (action != null && action.equals("EDIT")) {
			if (userDTO.getId() != null) {
				userDTO = userService.findById(userDTO.getId());
			}
			url = ("/views/admin/user/edit.jsp");
		} else {
			url = ("/views/admin/home.jsp");
		}
		request.setAttribute("model", userDTO);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
