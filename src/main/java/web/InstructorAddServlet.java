package web;


import dao.InstructorDAO;
import dao.InstructorDAOImpl;
import domain.Instructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/instructors/add")
public class InstructorAddServlet extends HttpServlet {
    private InstructorDAO instructorDAO = new InstructorDAOImpl(ApplicationListener.getEntityManager());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/instructor-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        int age = Integer.parseInt(req.getParameter("age"));
        String phone = req.getParameter("phone");

        if (firstName == null) {
            throw new IllegalArgumentException("firstName is missing");
        }

        if (secondName == null) {
            throw new IllegalArgumentException("secondName is missing");
        }

        if (age <= 0) {
            throw new IllegalArgumentException("age is missing");
        }

        if (phone == null) {
            throw new IllegalArgumentException("phone is missing");
        }

        Instructor instructor = instructorDAO.create(firstName, secondName, age);
        instructorDAO.update(instructor, phone);

        resp.sendRedirect("/instructors/all");
    }
}

