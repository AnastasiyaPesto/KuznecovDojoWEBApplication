package test;

import dao.*;
import domain.Certificate;
import domain.Instructor;
import domain.SportClub;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
            // Здесь объект в состоянии Managed
            // entity manager при коммите обнаружит изменение и сгенерирует запрос на UPDATE
            instructor1.setPhone("89115508989");

            instructorDAO.update(instructor2, "+79117508272");

            Instructor instructor4 = new Instructor();
            boolean isManaged1 = em.contains(instructor1);
            boolean isManaged2 = em.contains(instructor2);
            boolean isManaged3 = em.contains(instructor3);
            boolean isManaged4 = em.contains(instructor4);

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

            CertificateDAO certificateDAO  = new CertificateDAOImpl(em);

            // добавить сертификат к инструктору
            certificateDAO.addTo(instructor1, "0157_qw", -5, new Date());
            Map<String, Certificate> map = instructor1.getCertificateMap();
            for (String key : map.keySet()) {
                System.out.println(map.get(key).toString());
            }
            // todo не работает
            certificateDAO.delete(instructor1, "0157_qw");

            // поиск инструкторов, у которых степень выше чем
            // todo не работает findWhereDegreeIsMore()
            List<Instructor> instrWhereDegreeIsMore = instructorDAO.findWhereDegreeIsMore(-6);
            for (Instructor instructor : instrWhereDegreeIsMore) {
                System.out.println(instructor.toString());
            }

            SportClubDAO sportClubDAO = new SportClubDAOImpl(em);
            SportClub sportClub = sportClubDAO.create("Академическая", "пр. Науки, 41", "123456");

            sportClubDAO.addInstructor(sportClub, instructor1);
            sportClubDAO.addInstructor(sportClub, instructor2);
            sportClubDAO.addInstructor(sportClub, instructor3);

            sportClubDAO.deleteInstructor(sportClub, instructor1);

        } finally {
            em.close();
            emf.close();
        }
    }
}
