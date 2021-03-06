package test;

import dao.InstructorDAO;
import domain.Instructor;
import web.instructors.InstructorListBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(urlPatterns = "/instructors/all")
public class InstructorListServlet extends HttpServlet {
    private InstructorDAO instructorDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAgent = req.getHeader("User-Agent");

        // Если здесь возникнет ошибка (сломалось что-то/нет в базе),
        // то из doGet вылетит ошибка и попадет в Tomcat.
        // Если эта ошибка нами не обработана, то Tomcat сам сгенерирует ошибку 500 (с stacktrace)
        // Значит нам не нужно тут делать огромный try/catch
        List<Instructor> instructors = instructorDAO.getAll();

        InstructorListBean instructorListBean = new InstructorListBean(instructors);

        req.setAttribute("instructorListBean", instructorListBean);
        req.getRequestDispatcher("/pages/instructor-list.jsp").forward(req, resp);
    }
}
