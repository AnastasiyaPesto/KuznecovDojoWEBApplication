package dao;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import domain.Certificate;
import domain.Instructor;
import domain.SportClub;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InstructorDAOImpl implements InstructorDAO {
    private final EntityManager entityManager;

    public InstructorDAOImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Instructor create(String firstName, String secondName, int age) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(firstName);
        instructor.setSecondName(secondName);
        instructor.setAge(age);

        entityManager.getTransaction().begin();
        try {
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return instructor;
    }

    @Override
    public List<Instructor> getAll() {
        entityManager.getTransaction().begin();
        List<Instructor> instructors = null;
        try {
            instructors = entityManager
                    .createQuery("SELECT i FROM Instructor i", Instructor.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }

        return instructors;
    }

    @Override
    public List<Instructor> findByFirstName(String firstName) {
        entityManager.getTransaction().begin();
        List<Instructor> instructors = null;
        try {
            instructors = entityManager
                    .createQuery("SELECT i FROM Instructor i WHERE i.firstName = :firstName", Instructor.class)
                    .setParameter("firstName", firstName)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return instructors;
    }

    // TODO не работает join?
    @Override
    public List<Instructor> findWhereDegreeIsMore(int degree) {
        entityManager.getTransaction().begin();
        List<Instructor> instructors = null;
        try{
            // todo как искать в map
            instructors = entityManager
                    .createQuery("SELECT i FROM Instructor i LEFT JOIN i.certificateMap cert WHERE cert.degree >= :degree", Instructor.class)
                    .setParameter("degree", degree)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            // EntityExistsException
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return instructors;
    }

//    // todo нужно доделать
//    @Override
//    public List<Instructor> findBySportClub(SportClub sportClub) {
//        entityManager.getTransaction().begin();
//        List<Instructor> instructors = null;
//        try {
////            entityManager.createQuery("SELECT ");
//        } catch (PersistenceException pe){
//            entityManager.getTransaction().rollback();
//            throw pe;
//        }
//        return null;
//    }

    @Override
    public void update(Instructor instructor, String phone) {
        if (instructor == null) throw new IllegalArgumentException("Instructor shouldn't be null");
        entityManager.getTransaction().begin();
        try {
            instructor.setPhone(phone);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }

    @Override
    public Instructor delete(int id) {
        // при удалении тренера все сертификаты удалить
        entityManager.getTransaction().begin();
        Instructor instructor;
        try {
            instructor = entityManager.find(Instructor.class, id);
            Map<String, Certificate> certificates = instructor.getCertificateMap();
            for (String key : certificates.keySet()) {
                certificates.remove(key);
            }
            entityManager.remove(instructor);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe){
            entityManager.getTransaction().rollback();
            throw pe;
        }

        return null;
    }

    @Override
    public void addCertificate(Instructor instructor, String number, int degree, Date dateComplete) {
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor  shouldn't be null");
        }
        entityManager.getTransaction().begin();
        try {
            Certificate certificate = new Certificate(number, degree, dateComplete);
            instructor.addCertificate(certificate);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }

    @Override
    public void deleteSportClubFrom(Instructor instructor, SportClub sportClub) {
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor  shouldn't be null");
        }
        entityManager.getTransaction().begin();
        try {
            instructor.deleteSportClub(sportClub);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }
}
