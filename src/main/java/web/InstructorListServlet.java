package web;

import dao.InstructorDAO;
import dao.InstructorDAOImpl;
import domain.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/instructors/all")
public class InstructorListServlet extends HttpServlet {
    private InstructorDAO dao;
    private EntityManagerFactory emf;
    private EntityManager em;
    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("ProdKuznecovDojoPersistenceUnit");
        em = emf.createEntityManager();
        dao = new InstructorDAOImpl(em);

        if (dao.getAll().isEmpty()) {
            dao.create("Пестовникова", "Анастасия", 26);
        }
    }

    @Override
    public void destroy() {
        if (em != null) em.close();;
        em = null;

        if (emf != null) emf.close();;
        emf = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Если здесь возникнет ошибка (сломалось что-то/нет в базе),
        // то из doGet вылетит ошибка и попадет в Tomcat.
        // Если эта ошибка нами не обработана, то Tomcat сам сгенерирует ошибку 500 (с stacktrace)
        // Значит нам не нужно тут делать огромный try/catch
        List<Instructor> instructors = dao.getAll();

        req.setAttribute("instructors", instructors);
        req.getRequestDispatcher("/pages/instructor-all.jsp").forward(req, resp);
    }
}
