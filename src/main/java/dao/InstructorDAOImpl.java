package dao;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import domain.Certificate;
import domain.Instructor;
import domain.SportClub;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

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
    public int update(Instructor instructor, String phone) {
        if (instructor == null) throw new IllegalArgumentException("Instructor shouldn't be not null");
        entityManager.getTransaction().begin();
        int rowCountUpdate = 0;
        try {
            Instructor foundInstructor = entityManager.find(Instructor.class, instructor.getInstructorId());
            foundInstructor.setPhone(phone);
            entityManager.getTransaction().commit();
            rowCountUpdate = 1;
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return rowCountUpdate;
    }
}
