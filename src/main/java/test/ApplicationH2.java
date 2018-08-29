package test;

import dao.InstructorDAO;
import dao.InstructorDAOImpl;
import domain.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ApplicationH2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("H2KuznecovDojoPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            InstructorDAO instructorDAO = new InstructorDAOImpl(em);

            // CREATE
            Instructor instructor1 = instructorDAO.create("Иванов", "Иван", 25);
            Instructor instructor2 = instructorDAO.create("Иванов", "Костик", 31);
            Instructor instructor3 = instructorDAO.create("Петров", "Петр", 47);
            // Здесь объект в состоянии Managed, поэтому setter вызовет запрос на UPDATE
            instructor1.setPhone("89115508989");

            // GET ALL
            List<Instructor> instructors = instructorDAO.getAll();

            for (Instructor instructor : instructors) {
                System.out.println(instructor.toString());
            }

            //FIND BY FIRST NAME
            List<Instructor> instrsFirstName = instructorDAO.findByFirstName("Иванов");

            for (Instructor instructor : instrsFirstName) {
                System.out.println(instructor.toString());
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
