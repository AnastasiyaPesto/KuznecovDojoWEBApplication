package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(urlPatterns = "/instructors/*")
public class TomCatExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getQueryString();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

//        PrintWriter writer = resp.getWriter();
//        writer.println("<DOCTYPE html>");
//        writer.print("<html><head><title>webapp</title></head>");
//        writer.print("<body>");
//        writer.print("<h1>Привет, ");
//        writer.print("что здесь происходит?");
//        writer.print("</h1> \n");
//        writer.print("</body></html>");

        PrintWriter out = resp.getWriter();

        out.println("<h3>Hello from servlet!</h3></br>");
        out.println(req.getMethod());

        out.close();

    }
}
