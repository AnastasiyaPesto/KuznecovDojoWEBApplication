package test;

import dao.InstructorDAO;
import dao.InstructorDAOImpl;
import domain.Certificate;
import domain.Instructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQLKuznecovDojoPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            InstructorDAO instructorDAO = new InstructorDAOImpl(em);

//            List<Instructor> instructors = instructorDAO.getAll();
        List<Instructor> instructors = instructorDAO.findByFirstName("Кузнецов");

            for (Instructor instructor : instructors) {
                System.out.println(instructor.toString());
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
