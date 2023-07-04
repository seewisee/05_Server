package edu.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("inputId");
		String pw1 = req.getParameter("inputPw1");
		String pw2 = req.getParameter("inputPw2");
		String name = req.getParameter("inputName");
		String email = req.getParameter("inputEmail");
		String[] colors = req.getParameterValues("color");

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		if (!pw1.equals(pw2)) { out.println("<h1>비밀번호가 일치하지 않습니다</h1>"); return; }

		out.println("<ul>");
		out.println("<li>아이디 : " + id + "</li>");
		out.println("<li>이름 : " + name + "</li>");
		out.println("<li>이메일 : " + email + "</li>");
		out.println("<li>좋아하는 색 : ");

		if (colors == null) out.print("없습니다");
		else for (String c : colors) out.println(c + " ");

		out.println("</li>");
		out.println("</ul>");

	}

}
