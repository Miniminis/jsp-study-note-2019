package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest req, 
			HttpServletResponse resp) 
					throws ServletException, IOException {
		//super.doGet(req, resp);
		
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter(); //resp �� ����  getwriter ��ü�� ������ �;� �̸� ���� ����ó���� �����ϴ�!
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello, Servelet!!!!!");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(new Date());
		out.println("</body>");
		out.println("</html>");
	}

}
