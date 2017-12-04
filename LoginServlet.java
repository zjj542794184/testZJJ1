package servlset;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import dao.factory.UserFactory;

@WebServlet(name="roginServlet",urlPatterns="/roginServlet")
public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(111);
		req.setCharacterEncoding("utf-8");
		UserBean user = new UserBean();
		UserFactory userFactroy = new UserFactory();
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		List<UserBean> re =null;
		try {
			re = userFactroy.getUserInformation().findInformation(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int a = 0;
		int b = 0;
		for(UserBean u : re) {
			String name = u.getUserName();
			String password = u.getUserPassword();
			if(name.equals(userName)) {
				a++;
			}
			if(name.equals(userName) && !password.equals(userPassword)) {
				b++;
			}
		}
		if(a==0) {
			req.setAttribute("exist", "该用户不存在！！");
			req.getRequestDispatcher("user/login.jsp").forward(req, resp);
		}else if(b>0) {
			req.setAttribute("error", "你输入的密码有误，请输入正确的密码");
			req.getRequestDispatcher("user/login.jsp").forward(req, resp);
		}else {
			req.setAttribute("userName1", userName);
			req.getRequestDispatcher("mainPage.jsp").forward(req, resp);
		}
	}
}
