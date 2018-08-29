package test;

import dao.CertificateDAO;
import dao.CertificateDAOImpl;
import dao.InstructorDAO;
import dao.InstructorDAOImpl;
import domain.Certificate;
import domain.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("H2KuznecovDojoPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            InstructorDAO instructorDAO = new InstructorDAOImpl(em);
            CertificateDAO certificateDAO = new CertificateDAOImpl(em);
            List<Instructor> instructors = instructorDAO.findByFirstName("Кузнецов");

            Certificate certificate = new Certificate();
            certificate.setNumber("5555");
            certificate.setDegree("6 dan");

            certificateDAO.addTo(instructors.get(0), certificate);

            int i = 0;

//            List<Instructor> instructors = instructorDAO.getAll();
//        List<Instructor> instructors = instructorDAO.findByFirstName("Кузнецов");
//
//            for (Instructor instructor : instructors) {
//                System.out.println(instructor.toString());
//            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
