package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.UserDTO;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.FromUtil;
import utils.SessionUtil;

@WebServlet(urlPatterns = { "/admin-home", "/dang-nhap", "/trang-chu" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 2686801510274002166L;
	private UserService service = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/login/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERDTO");
			response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login");
		} else {
			response.sendRedirect(request.getContextPath() + "/views/web/trangchu.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			UserDTO user = FromUtil.toModel(UserDTO.class, request);
			String username = user.getUserName();
			user = service.findByAccount(user.getUserName(), user.getPassword());
			if (user != null) {
				if (username.equals(user.getUserName())) {
					SessionUtil.getInstance().putValue(request, "USERDTO", user);
					if (user.getRoleDTO().getCode().equals("STAFF") || user.getRoleDTO().getCode().equals("MANAGER")) {
						response.sendRedirect(request.getContextPath() + "/admin-building");
					} else {
						response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login");
					}
				} else {
					response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login");

				}
			} else {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login");
			}

		}
	}
}
