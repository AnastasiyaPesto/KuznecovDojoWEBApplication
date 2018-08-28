package test;

import dao.InstructorDAO;
import dao.InstructorDAOImpl;
import domain.Certificate;
import domain.Instructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        InstructorDAO instructorDAO = new InstructorDAOImpl(sessionFactory);

        List<Instructor> instructors = instructorDAO.getAll();
//        List<Instructor> instructors = instructorDAO.findByFirstName("Кузнецов");

        for (Instructor instructor : instructors) {
            System.out.println(instructor.toString());
        }

        sessionFactory.close();
    }
}
