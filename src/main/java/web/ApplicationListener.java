package web;

import dao.InstructorDAO;
import dao.InstructorDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.ParseException;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("ProdKuznecovDojoPersistenceUnit");
        em = emf.createEntityManager();

        InstructorDAO dao = new InstructorDAOImpl(em);

        if (dao.getAll().isEmpty()) {
            dao.create("Пестовникова", "Анастасия", 26);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (em != null) em.close();
        em = null;
        if (emf != null) emf.close();
        emf = null;
    }
}


